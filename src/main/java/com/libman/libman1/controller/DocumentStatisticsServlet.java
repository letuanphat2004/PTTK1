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


@WebServlet(name = "DocumentStatisticsServlet", urlPatterns = {"/docStats"})
public class DocumentStatisticsServlet extends HttpServlet {

    private DocumentStatisticDAO docStatsDAO;

    @Override
    public void init() {
        docStatsDAO = new DocumentStatisticDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");

            List<DocumentStatistic> statsList;

            if (startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()) {
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate endDate = LocalDate.parse(endDateStr);
                statsList = docStatsDAO.getDocumentStatisticByDate(startDate, endDate);

                request.setAttribute("startDate", startDateStr);
                request.setAttribute("endDate", endDateStr);
            } else {

                statsList = docStatsDAO.getAllDocumentStatistic();
            }

            request.setAttribute("statsList", statsList);
            request.getRequestDispatcher("GDTKtailieu.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}