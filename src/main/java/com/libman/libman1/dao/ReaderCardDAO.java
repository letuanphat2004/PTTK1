package com.libman.libman1.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ReaderCardDAO {


    public boolean hasActiveReaderCard(int userId) {
        String query = "SELECT 1 FROM ReaderCards WHERE reader_id = ? AND status = 'Active'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBContext db = new DBContext();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close(conn, ps, rs);
        }
    }


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
            ps = conn.prepareStatement(queryCheck);
            ps.setInt(1, readerId);
            rs = ps.executeQuery();

            if (rs.next()) {

                ps.close();
                ps = conn.prepareStatement(queryUpdate);
                ps.setDate(1, java.sql.Date.valueOf(issueDate));
                ps.setDate(2, java.sql.Date.valueOf(expirationDate));
                ps.setString(3, cardType);
                ps.setInt(4, readerId);
            } else {
                ps.close();
                ps = conn.prepareStatement(queryInsert);
                ps.setInt(1, readerId);
                ps.setDate(2, java.sql.Date.valueOf(issueDate));
                ps.setDate(3, java.sql.Date.valueOf(expirationDate));
                ps.setString(4, cardType);
            }

            return ps.executeUpdate() > 0;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close(conn, ps, rs);
        }
    }
}