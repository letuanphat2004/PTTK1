package com.libman.libman1.controller;
import com.libman.libman1.dao.ReaderCardDAO;
import com.libman.libman1.dao.UserDAO;
import com.libman.libman1.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User account = dao.checkLogin(user, pass);

        if (account == null) {
            // Đăng nhập thất bại
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Đăng nhập thành công, lưu user vào session
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            session.setMaxInactiveInterval(1800); // Session tồn tại 30 phút
            if ("Reader".equals(account.getRole())) {
                // 2. Dùng ReaderCardDAO để kiểm tra thẻ
                ReaderCardDAO cardDAO = new ReaderCardDAO(); // <-- THAY ĐỔI
                boolean hasActiveCard = cardDAO.hasActiveReaderCard(account.getId()); // <-- THAY ĐỔI
                session.setAttribute("hasActiveCard", hasActiveCard);
            }
            response.sendRedirect("home.jsp"); // Chuyển hướng tới trang chủ
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Khi người dùng truy cập /login, chỉ cần hiển thị trang login.jsp
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
