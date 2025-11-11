package com.libman.libman1.controller;

import com.libman.libman1.dao.BorrowDetailDAO;
import com.libman.libman1.entity.BorrowDetail;
// (Bạn nên có DocumentDAO để lấy tên tài liệu)
// import com.libman.libman1.dao.DocumentDAO;
// import com.libman.libman1.entity.Document;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "BorrowDetailServlet", urlPatterns = {"/borrowDetails"})
public class BorrowDetailServlet extends HttpServlet {

    private BorrowDetailDAO borrowDetailDAO;
    // private DocumentDAO documentDAO;

    @Override
    public void init() {
        borrowDetailDAO = new BorrowDetailDAO();
        // documentDAO = new DocumentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int docId = Integer.parseInt(request.getParameter("docId"));
            String startDateStr = request.getParameter("start");
            String endDateStr = request.getParameter("end");

            List<BorrowDetail> instances;

            // Kiểm tra xem có lọc ngày không
            if (startDateStr != null && !startDateStr.equals("null") && !startDateStr.isEmpty()) {
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);
                instances = borrowDetailDAO.getBorrowDetailListByDate(docId, startDate, endDate);

                // Gửi lại ngày để nút "Quay lại" ở trang 2 hoạt động đúng
                request.setAttribute("startDate", startDateStr);
                request.setAttribute("endDate", endDateStr);
            } else {
                // Lấy tất cả
                instances = borrowDetailDAO.getAllBorrowDetailList(docId);
            }

            // Lấy tên tài liệu để hiển thị
            // Document doc = documentDAO.getDocumentById(docId);
            // request.setAttribute("documentName", doc.getTitle());
            request.setAttribute("documentName", "Tên tài liệu demo"); // Xóa dòng này khi có DAO

            request.setAttribute("borrowInstances", instances);
            request.getRequestDispatcher("GDTKchitiettailieu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}