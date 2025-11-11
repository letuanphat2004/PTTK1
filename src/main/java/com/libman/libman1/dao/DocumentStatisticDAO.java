package com.libman.libman1.dao;

import com.libman.libman1.entity.DocumentStatistic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentStatisticDAO {

    private DocumentStatistic createDTO(ResultSet rs) throws SQLException {
        DocumentStatistic dto = new DocumentStatistic();
        dto.setDocumentId(rs.getInt("id"));
        dto.setDocumentTitle(rs.getString("title"));
        dto.setDocumentAuthor(rs.getString("author"));
        dto.setBorrowCount(rs.getInt("borrowCount"));
        return dto;
    }

    public List<DocumentStatistic> getDocumentStatisticByDate(LocalDate startDate, LocalDate endDate) {
        List<DocumentStatistic> list = new ArrayList<>();

        String sql = "SELECT " +
                "  d.id, d.title, d.author, COUNT(bd.id) AS borrowCount " +
                "FROM " +
                "  borrowdetails bd " +
                "JOIN " +
                "  documents d ON bd.document_id = d.id " + // Sửa 2: "Documents" -> "documents"
                "WHERE " +
                "  bd.borrow_date BETWEEN ? AND ? " +
                "GROUP BY " +
                "  d.id, d.title, d.author " +
                "ORDER BY " +
                "  borrowCount DESC";


        DBContext db = new DBContext();
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(createDTO(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Lỗi sẽ in ra console của Tomcat
        }
        return list;
    }

    // Phương thức 2: Lấy tất cả thống kê (cho lần tải trang đầu tiên)
    public List<DocumentStatistic> getAllDocumentStatistic() {
        List<DocumentStatistic> list = new ArrayList<>();

        // === SỬA TÊN BẢNG Ở ĐÂY ===
        String sql = "SELECT " +
                "  d.id, d.title, d.author, COUNT(bd.id) AS borrowCount " +
                "FROM " +
                "  borrowdetails bd " + // Sửa 1: "BorrowDetails" -> "borrowdetails"
                "JOIN " +
                "  documents d ON bd.document_id = d.id " + // Sửa 2: "Documents" -> "documents"
                "GROUP BY " +
                "  d.id, d.title, d.author " +
                "ORDER BY " +
                "  borrowCount DESC";


        DBContext db = new DBContext();
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(createDTO(rs));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Lỗi sẽ in ra console của Tomcat
        }
        return list;
    }
}