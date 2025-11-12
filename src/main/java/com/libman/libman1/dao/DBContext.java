package com.libman.libman1.dao;

import java.sql.*;

public class DBContext {
    private final String serverName = "localhost";
    private final String dbName = "library";
    private final String portNumber = "3306";
    private final String userID = "root";
    private final String password = "tuanphat2004";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Xây dựng URL kết nối
        String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName
                + "?useSSL=false&allowPublicKeyRetrieval=true";


        Class.forName("com.mysql.cj.jdbc.Driver");

        // Trả về đối tượng Connection
        return DriverManager.getConnection(url, userID, password);
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
        }
    }

}