package com.libman.libman1.controller;

import com.libman.libman1.dao.ReaderCardDAO; // Import DAO thẻ
import com.libman.libman1.dao.UserDAO;       // Import DAO user
import com.libman.libman1.entity.User;
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
        request.setCharacterEncoding("UTF-8"); // Thêm để đọc tiếng Việt

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("account");

        if (loggedInUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // --- 1. LẤY DỮ LIỆU TỪ FORM (CHO CẢ 2 BẢNG) ---

            // Thông tin cá nhân (để UPDATE bảng Users)
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            LocalDate dob = LocalDate.parse(request.getParameter("dob"));

            // Thông tin thẻ (để INSERT/UPDATE bảng ReaderCards)
            String cardType = request.getParameter("cardType");
            LocalDate issueDate = LocalDate.now();
            LocalDate expirationDate = issueDate.plusYears(5);

            // --- 2. GỌI CÁC DAO ---
            UserDAO userDAO = new UserDAO();
            ReaderCardDAO cardDAO = new ReaderCardDAO();

            // Cập nhật profile (bảng Users)
            boolean profileUpdated = userDAO.updateReaderProfile(loggedInUser.getId(), fullname, email, phone, address, dob);

            // Kích hoạt/Tạo thẻ (bảng ReaderCards)
            boolean cardCreated = cardDAO.activateOrCreateCard(loggedInUser.getId(), cardType, issueDate, expirationDate);

            // --- 3. XỬ LÝ KẾT QUẢ ---
            try (PrintWriter out = response.getWriter()) {
                if (profileUpdated && cardCreated) {
                    // Cập nhật lại thông tin user trong session
                    loggedInUser.setFullname(fullname);
                    loggedInUser.setEmail(email);
                    loggedInUser.setPhone(phone);
                    loggedInUser.setAddress(address);
                    java.util.Date dateDob = java.util.Date.from(dob.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    loggedInUser.setDob(dateDob); // Giờ đã đúng kiểu Date
                    session.setAttribute("account", loggedInUser); // Lưu lại

                    // Cập nhật trạng thái thẻ
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
            // Xử lý lỗi (ví dụ: người dùng nhập ngày sinh sai định dạng)
            try (PrintWriter out = response.getWriter()) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Đã xảy ra lỗi. Vui lòng kiểm tra lại thông tin đã nhập.');");
                out.println("window.location.href = 'register_card.jsp';");
                out.println("</script>");
            }
        }
    }
}