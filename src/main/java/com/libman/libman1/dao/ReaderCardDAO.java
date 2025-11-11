package com.libman.libman1.dao;

import com.libman.libman1.entity.ReaderCard; // Import entity

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ReaderCardDAO {

    /**
     * Kiểm tra xem một User ID đã có thẻ BẠN ĐỌC ĐANG HOẠT ĐỘNG hay chưa
     */
    public boolean hasActiveReaderCard(int userId) {
        String query = "SELECT 1 FROM ReaderCards WHERE reader_id = ? AND status = 'Active'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext(); // Tạo DBContext để dùng hàm close

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            return rs.next(); // true nếu tìm thấy thẻ đang 'Active'
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close(conn, ps, rs); // Luôn đóng tài nguyên
        }
    }

    /**
     * Kích hoạt hoặc Tạo mới thẻ cho bạn đọc.
     * Nếu thẻ đã tồn tại (hết hạn), nó sẽ UPDATE.
     * Nếu chưa, nó sẽ INSERT.
     */
    public boolean activateOrCreateCard(int readerId, String cardType, LocalDate issueDate, LocalDate expirationDate) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        String queryCheck = "SELECT id FROM ReaderCards WHERE reader_id = ?";
        String queryUpdate = "UPDATE ReaderCards SET issue_date = ?, expiration_date = ?, status = 'Active', card_type = ? WHERE reader_id = ?";
        String queryInsert = "INSERT INTO ReaderCards (reader_id, issue_date, expiration_date, status, card_type) VALUES (?, ?, ?, 'Active', ?)";

        try {
            conn = db.getConnection();
            // 1. Kiểm tra xem đã tồn tại bản ghi thẻ nào chưa
            ps = conn.prepareStatement(queryCheck);
            ps.setInt(1, readerId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 2. Nếu có (thẻ cũ hết hạn) -> CẬP NHẬT (UPDATE)
                ps.close(); // Đóng PreparedStatement cũ
                ps = conn.prepareStatement(queryUpdate);
                ps.setDate(1, java.sql.Date.valueOf(issueDate)); // Chuyển LocalDate sang sql.Date
                ps.setDate(2, java.sql.Date.valueOf(expirationDate));
                ps.setString(3, cardType);
                ps.setInt(4, readerId);
            } else {
                // 3. Nếu chưa có -> TẠO MỚI (INSERT)
                ps.close(); // Đóng PreparedStatement cũ
                ps = conn.prepareStatement(queryInsert);
                ps.setInt(1, readerId);
                ps.setDate(2, java.sql.Date.valueOf(issueDate));
                ps.setDate(3, java.sql.Date.valueOf(expirationDate));
                ps.setString(4, cardType);
            }

            return ps.executeUpdate() > 0; // Trả về true nếu thực thi thành công

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close(conn, ps, rs);
        }
    }
}