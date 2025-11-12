package com.libman.libman1.dao;

import com.libman.libman1.model.Documents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DocumentDAO {
    public Documents getDocumentById(int documentId) {
        String sql = "SELECT * FROM documents WHERE id = ?";
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, documentId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Documents doc = new Documents();
                doc.setId(rs.getInt("id"));
                doc.setTitle(rs.getString("title"));
                doc.setAuthor(rs.getString("author"));
                doc.setPublisher(rs.getString("publisher"));

                return doc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close(conn, ps, rs);
        }
        return null;
    }
}
