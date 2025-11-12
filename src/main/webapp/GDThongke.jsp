<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Menu Báo cáo</title>
    <style>
        /* === 1. ĐỒNG BỘ BODY VÀ FONT === */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9; /* Nền xám nhạt */
            margin: 0;
            padding: 20px; /* Thêm padding chung */
        }

        /* === 2. ĐỒNG BỘ KHUNG "CARD" === */
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto; /* Tự động căn giữa */
            background-color: #ffffff;
            padding: 2.5rem; /* Padding đồng bộ */
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        /* === 3. ĐỒNG BỘ HEADER (Giống home.jsp) === */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #eee;
            padding-bottom: 1rem;
        }
        .header h1 {
            margin: 0;
            color: #333;
            font-size: 1.8em;
        }
        .header span { /* Style cho "Chào, ..." */
            font-size: 1.1em;
            color: #555;
            font-weight: bold;
        }

        /* === 4. STYLE CHO LINK "QUAY LẠI" (Như bạn yêu cầu) === */
        .back-link {
            display: inline-block;
            margin-top: 20px; /* Khoảng cách với header */
            color: #551a8b; /* Màu tím */
            text-decoration: underline;
            font-size: 0.9em;
        }

        /* Tiêu đề "Vui lòng chọn..." */
        h2 {
            color: #333;
            margin-top: 25px;
            margin-bottom: 15px;
        }

        /* === 5. ĐỒNG BỘ NÚT BẤM (Menu) === */
        .menu {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }
        .menu a {
            display: inline-block;
            padding: 12px 18px; /* Đồng bộ padding */
            background-color: #007bff; /* Màu xanh dương */
            color: white;
            text-decoration: none;
            border-radius: 4px; /* Đồng bộ bo góc */
            font-size: 1rem; /* Đồng bộ font */
            font-weight: bold; /* Đồng bộ font */
            transition: background-color 0.3s;
        }
        .menu a:hover {
            background-color: #0056b3;
            text-decoration: none;
        }
        /* Nút "chưa có" (màu xám) */
        .menu a.disabled {
            background-color: #6c757d;
            cursor: not-allowed;
        }
        .menu a.disabled:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Trang Báo cáo & Thống kê</h1>
        <span>Chào, ${sessionScope.account.fullname}</span>
    </div>

    <a href="home.jsp" class="back-link">Quay lại trang chủ</a>

    <h2>Vui lòng chọn loại báo cáo bạn muốn xem:</h2>

    <div class="menu">
        <a href="GDTKtailieu.jsp">
            Thống kê tài liệu theo lượt mượn
        </a>

    </div>
</div>
</body>
</html>