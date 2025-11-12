package com.libman.libman1.servlet;
import com.libman.libman1.dao.ReaderCardDAO;
import com.libman.libman1.dao.UserDAO;
import com.libman.libman1.model.User;
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
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            session.setMaxInactiveInterval(1800);
            if ("Reader".equals(account.getRole())) {
                ReaderCardDAO cardDAO = new ReaderCardDAO();
                boolean hasActiveCard = cardDAO.hasActiveReaderCard(account.getId());
                session.setAttribute("hasActiveCard", hasActiveCard);
            }
            response.sendRedirect("home.jsp");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
