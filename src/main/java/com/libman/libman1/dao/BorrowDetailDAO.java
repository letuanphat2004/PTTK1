package com.libman.libman1.dao;

import com.libman.libman1.entity.BorrowDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowDetailDAO {


    private BorrowDetail createDTO(ResultSet rs) throws SQLException {
        BorrowDetail dto = new BorrowDetail();
        dto.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
        dto.setDueDate(rs.getDate("due_date").toLocalDate());
        dto.setStatus(rs.getString("status"));
        dto.setReaderName(rs.getString("fullname"));
        dto.setBorrowSlipId(rs.getInt("borrowSlipId"));
        return dto;
    }

    public List<BorrowDetail> getBorrowDetailListByDate(int documentId, LocalDate startDate, LocalDate endDate) {
        List<BorrowDetail> list = new ArrayList<>();
        String sql = "SELECT bd.borrow_date, bd.due_date, bd.status, u.fullname, bs.id AS borrowSlipId " +
                "FROM BorrowDetails bd " +
                "JOIN BorrowSlips bs ON bd.borrow_slip_id = bs.id " +
                "JOIN Users u ON bs.reader_id = u.id " +
                "WHERE bd.document_id = ? AND bd.borrow_date BETWEEN ? AND ? " +
                "ORDER BY bd.borrow_date DESC";

        DBContext db = new DBContext();
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, documentId);
            ps.setDate(2, java.sql.Date.valueOf(startDate));
            ps.setDate(3, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(createDTO(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<BorrowDetail> getAllBorrowDetailList(int documentId) {
        List<BorrowDetail> list = new ArrayList<>();
        String sql = "SELECT bd.borrow_date, bd.due_date, bd.status, u.fullname, bs.id AS borrowSlipId " +
                "FROM BorrowDetails bd " +
                "JOIN BorrowSlips bs ON bd.borrow_slip_id = bs.id " +
                "JOIN Users u ON bs.reader_id = u.id " +
                "WHERE bd.document_id = ? " +
                "ORDER BY bd.borrow_date DESC";

        DBContext db = new DBContext();
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, documentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(createDTO(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}