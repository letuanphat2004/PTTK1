<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Thêm thư viện JSTL để sử dụng các thẻ <c:if> --%>

<html>
<head>
    <title>Trang chủ Thư viện</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #fff;
            padding: 20px;
            border-bottom: 2px solid #ddd;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
            border-radius: 8px;
        }
        .header h1 {
            margin: 0;
            color: #333;
            font-size: 1.8em;
        }
        .header a {
            text-decoration: none;
            background-color: #d9534f; /* Màu đỏ */
            color: white;
            padding: 10px 18px;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .header a:hover {
            background-color: #c9302c; /* Đỏ đậm hơn */
        }
        .content {
            margin-top: 30px;
        }
        .content h2 {
            color: #555;
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
        }
        .menu {
            display: flex;
            flex-wrap: wrap; /* Cho phép xuống hàng trên màn hình nhỏ */
            gap: 15px; /* Khoảng cách giữa các nút */
        }
        .menu a {
            display: inline-block;
            padding: 18px 30px;
            background-color: #007bff; /* Màu xanh dương */
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-size: 1.1em;
            font-weight: 500;
            transition: background-color 0.3s, box-shadow 0.3s;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .menu a:hover {
            background-color: #0056b3; /* Xanh đậm hơn */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }
    </style>
</head>
<body>

<div class="container">

    <div class="header">
        <%-- Hiển thị tên đầy đủ của người dùng từ session --%>
        <h1>Chào mừng, ${sessionScope.account.fullname}</h1>

        <%-- Nút Đăng xuất --%>
        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </div>

    <div class="content">
        <h2>Menu Chức năng</h2>

        <div class="menu">

            <%-- 1. MENU DÀNH CHO BẠN ĐỌC (Reader) --%>
            <c:if test="${sessionScope.account.role == 'Reader'}">

                <%-- 1a. Nếu Reader CHƯA CÓ THẺ HOẠT ĐỘNG --%>
                <c:if test="${!sessionScope.hasActiveCard}">
                    <a href="register_card.jsp">Đăng ký thẻ bạn đọc</a>
                </c:if>

            </c:if>

            <c:if test="${sessionScope.account.role == 'Manager'}">
                <a href="docStats">Xem báo cáo thống kê</a>
            </c:if>

        </div>
    </div>

</div>

</body>
</html>