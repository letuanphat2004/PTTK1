package com.libman.libman1.dao;



import com.libman.libman1.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public User checkLogin(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try {
            conn = new DBContext().getConnection(); // Lấy kết nối CSDL
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getDate("dob"),
                        rs.getInt("borrow_count")

                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy user
    }
    public boolean updateReaderProfile(int readerId, String fullname, String email, String phone, String address, java.time.LocalDate dob) {
        String query = "UPDATE Users SET fullname = ?, email = ?, phone = ?, address = ?, dob = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        DBContext db = new DBContext();

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setDate(5, java.sql.Date.valueOf(dob));
            ps.setInt(6, readerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close(conn, ps, null);
        }
    }

}