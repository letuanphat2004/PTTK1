package com.libman.libman1.controller;

import com.libman.libman1.dao.DocumentStatisticDAO;
import com.libman.libman1.entity.DocumentStatistic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

// Link từ home.jsp sẽ trỏ vào "/docStats"
@WebServlet(name = "DocumentStatisticsServlet", urlPatterns = {"/docStats"})
public class DocumentStatisticsServlet extends HttpServlet {

    private DocumentStatisticDAO docStatsDAO;

    @Override
    public void init() {
        docStatsDAO = new DocumentStatisticDAO();
    }

    // Chúng ta xử lý cả GET và POST trong doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");

            List<DocumentStatistic> statsList;

            // Kiểm tra xem có lọc ngày không
            if (startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()) {
                // TRƯỜNG HỢP 2: Lọc theo ngày
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);
                statsList = docStatsDAO.getDocumentStatisticByDate(startDate, endDate);

                // Gửi lại ngày đã chọn để hiển thị trên form
                request.setAttribute("startDate", startDateStr);
                request.setAttribute("endDate", endDateStr);
            } else {
                // TRƯỜNG HỢP 1: Tải trang ban đầu (lấy tất cả)
                statsList = docStatsDAO.getAllDocumentStatistic();
            }

            request.setAttribute("statsList", statsList);
            request.getRequestDispatcher("GDTKtailieu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // (Nên tạo trang error.jsp để xử lý lỗi)
        }
    }

    // Nếu form dùng method="post" thì chỉ cần gọi doGet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}