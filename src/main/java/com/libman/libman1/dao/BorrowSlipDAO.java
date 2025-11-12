package com.libman.libman1.dao;

import com.libman.libman1.model.BorrowSlip;
import com.libman.libman1.model.SlipItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowSlipDAO {

    public BorrowSlip getFullBorrowSlip(int borrowSlipId) {
        BorrowSlip slip = null;
        String sqlSlip = "SELECT bs.id, r.fullname AS readerName, s.fullname AS staffName, bs.note " +
                "FROM BorrowSlips bs " +
                "JOIN Users r ON bs.reader_id = r.id " +
                "JOIN Users s ON bs.staff_id = s.id " +
                "WHERE bs.id = ?";

        String sqlItems = "SELECT d.title, bd.status, bd.borrow_date, bd.due_date " +
                "FROM BorrowDetails bd " +
                "JOIN Documents d ON bd.document_id = d.id " +
                "WHERE bd.borrow_slip_id = ?";

        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement psSlip = null;
        ResultSet rsSlip = null;
        PreparedStatement psItems = null;
        ResultSet rsItems = null;

        try {
            conn = db.getConnection();

            psSlip = conn.prepareStatement(sqlSlip);
            psSlip.setInt(1, borrowSlipId);
            rsSlip = psSlip.executeQuery();

            if (rsSlip.next()) {
                slip = new BorrowSlip();
                slip.setSlipId(rsSlip.getInt("id"));
                slip.setReaderName(rsSlip.getString("readerName"));
                slip.setStaffName(rsSlip.getString("staffName"));
                slip.setNote(rsSlip.getString("note"));

                psItems = conn.prepareStatement(sqlItems);
                psItems.setInt(1, borrowSlipId);
                rsItems = psItems.executeQuery();

                List<SlipItemDTO> items = new ArrayList<>();
                while (rsItems.next()) {
                    SlipItemDTO item = new SlipItemDTO();
                    item.setDocumentTitle(rsItems.getString("title"));
                    item.setStatus(rsItems.getString("status"));
                    item.setBorrowDate(rsItems.getDate("borrow_date").toLocalDate());
                    item.setDueDate(rsItems.getDate("due_date").toLocalDate());
                    items.add(item);
                }
                slip.setItems(items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rsItems != null) rsItems.close(); } catch (Exception e) {}
            try { if (psItems != null) psItems.close(); } catch (Exception e) {}
            db.close(conn, psSlip, rsSlip);
        }
        return slip;
    }
}