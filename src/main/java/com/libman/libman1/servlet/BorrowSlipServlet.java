package com.libman.libman1.servlet;

import com.libman.libman1.dao.BorrowSlipDAO;
import com.libman.libman1.model.BorrowSlip;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BorrowSlipServlet", urlPatterns = {"/slipDetails"})
public class BorrowSlipServlet extends HttpServlet {

    private BorrowSlipDAO borrowSlipDAO;

    @Override
    public void init() {
        borrowSlipDAO = new BorrowSlipDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int slipId = Integer.parseInt(request.getParameter("slipId"));

            BorrowSlip slip = borrowSlipDAO.getFullBorrowSlip(slipId);

            request.setAttribute("slip", slip);
            request.getRequestDispatcher("GDChitietphieumuon.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}