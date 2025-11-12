package com.libman.libman1.servlet;

import com.libman.libman1.dao.ReaderCardDAO; // Import DAO thẻ
import com.libman.libman1.dao.UserDAO;       // Import DAO user
import com.libman.libman1.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;

@WebServlet(name = "RegisterCardServlet", urlPatterns = {"/registerCard"})
public class RegisterCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("account");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            LocalDate dob = LocalDate.parse(request.getParameter("dob"));

            String cardType = request.getParameter("cardType");
            LocalDate issueDate = LocalDate.now();
            LocalDate expirationDate = issueDate.plusYears(5);


            UserDAO userDAO = new UserDAO();
            ReaderCardDAO cardDAO = new ReaderCardDAO();

            boolean profileUpdated = userDAO.updateReaderProfile(loggedInUser.getId(), fullname, email, phone, address, dob);

            boolean cardCreated = cardDAO.activateOrCreateCard(loggedInUser.getId(), cardType, issueDate, expirationDate);


            try (PrintWriter out = response.getWriter()) {
                if (profileUpdated && cardCreated) {
                    loggedInUser.setFullname(fullname);
                    loggedInUser.setEmail(email);
                    loggedInUser.setPhone(phone);
                    loggedInUser.setAddress(address);
                    java.util.Date dateDob = java.util.Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    loggedInUser.setDob(dateDob);
                    session.setAttribute("account", loggedInUser);

                    session.setAttribute("hasActiveCard", true);

                    out.println("<script type='text/javascript'>");
                    out.println("alert('Cập nhật hồ sơ và Đăng ký thẻ thành công!');");
                    out.println("window.location.href = 'home.jsp';");
                    out.println("</script>");
                } else {
                    out.println("<script type'text/javascript'>");
                    out.println("alert('Lỗi! Không thể cập nhật thông tin. Vui lòng thử lại.');");
                    out.println("window.location.href = 'register_card.jsp';");
                    out.println("</script>");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            try (PrintWriter out = response.getWriter()) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Đã xảy ra lỗi. Vui lòng kiểm tra lại thông tin đã nhập.');");
                out.println("window.location.href = 'register_card.jsp';");
                out.println("</script>");
            }
        }
    }
}