<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 1. BỔ SUNG THƯ VIỆN JSTL (Rất quan trọng) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Trang chủ Thư viện</title>
    <style>
        /* === 2. ĐỒNG BỘ BODY VỚI TRANG LOGIN === */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9; /* Đồng bộ màu nền xám nhạt */
            margin: 0;
            padding: 0;
        }

        /* Bố cục container chung */
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 20px auto;
        }

        /* === 3. TẠO KHUNG "CARD" CHO HEADER === */
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #ffffff; /* Nền trắng */
            padding: 2rem; /* Padding đồng bộ */
            border-radius: 8px; /* Bo góc đồng bộ */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng đồng bộ */
            margin-bottom: 20px; /* Khoảng cách với content */
        }
        .header h1 {
            margin: 0;
            color: #333;
            font-size: 1.8em;
        }

        /* === 4. ĐỒNG BỘ NÚT BẤM (Đăng xuất) === */
        .header a {
            text-decoration: none;
            background-color: #d9534f; /* Màu đỏ */
            color: white;
            padding: 12px 18px; /* Đồng bộ padding */
            border-radius: 4px; /* Đồng bộ bo góc */
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .header a:hover {
            background-color: #c9302c;
            text-decoration: none;
        }

        /* === 5. TẠO KHUNG "CARD" CHO CONTENT === */
        .content {
            background-color: #ffffff; /* Nền trắng */
            padding: 2rem; /* Padding đồng bộ */
            border-radius: 8px; /* Bo góc đồng bộ */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng đồng bộ */
        }
        .content h2 {
            margin-top: 0; /* Căn chỉnh lại */
            color: #333;
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
        }
        .menu {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }

        /* === 6. ĐỒNG BỘ NÚT BẤM (Menu) === */
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
    </style>
</head>
<body>

<div class="container">

    <div class="header">
        <h1>Chào mừng, ${sessionScope.account.fullname}</h1>
        <a href="login.jsp">Đăng xuất</a>
    </div>

    <div class="content">
        <h2>Menu Chức năng</h2>

        <div class="menu">

            <%-- Menu cho Bạn đọc --%>
            <c:if test="${sessionScope.account.role == 'Reader'}">
                <c:if test="${!sessionScope.hasActiveCard}">
                    <a href="register_card.jsp">Đăng ký thẻ bạn đọc</a>
                </c:if>
            </c:if>

            <c:if test="${sessionScope.account.role == 'Manager'}">
                <a href="GDTKtailieu.jsp">Xem báo cáo</a>
            </c:if>


        </div>
    </div>

</div>

</body>
</html>