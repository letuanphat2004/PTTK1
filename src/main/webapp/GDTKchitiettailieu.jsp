<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chi tiết Lần mượn</title>
    <style>
        /* === 1. ĐỒNG BỘ BODY VÀ FONT === */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9; /* Nền xám nhạt */
            margin: 0;
            padding: 20px;
        }

        /* === 2. ĐỒNG BỘ KHUNG "CARD" === */
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 2.5rem;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        /* === 3. ĐỒNG BỘ TIÊU ĐỀ === */
        h2 {
            font-size: 1.8rem;
            color: #333;
            margin-top: 0;
            margin-bottom: 0.5rem;
            /* Thêm một chút space để không dính vào tên sách */
            display: inline-block;
            margin-right: 10px;
        }

        /* Style cho tên tài liệu (sẽ nằm cạnh h2) */
        .document-title {
            font-size: 1.7rem; /* Hơi nhỏ hơn h2 một chút */
            color: #0056b3; /* Màu xanh đậm */
            font-weight: bold;
        }

        /* === 4. STYLE CHO LINK "QUAY LẠI" === */
        .back-link {
            display: inline-block;
            margin-bottom: 25px; /* Tăng khoảng cách với bảng */
            color: #551a8b; /* Màu tím */
            text-decoration: underline;
            font-size: 0.9em;
        }

        /* === 5. ĐỒNG BỘ BẢNG (Giống trang trước) === */
        .stats-table {
            width: 100%;
            border-collapse: collapse;
        }
        .stats-table th, .stats-table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
            font-size: 1rem;
        }
        .stats-table th {
            background-color: #f4f4f9;
        }
        .stats-table tr:nth-child(even) {
            background-color: #fdfdfd;
        }
        .stats-table a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .stats-table a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">

    <div>
        <h2>Chi tiết các lần mượn </h2>
    </div>

    <a href="docStats?startDate=${startDate}&endDate=${endDate}" class="back-link">Quay lại thống kê</a>

    <table class="stats-table">
        <thead>
        <tr>
            <th>Ngày mượn</th>
            <th>Ngày hẹn trả</th>
            <th>Trạng thái</th>
            <th>Người mượn</th>
            <th>Chi tiết phiếu</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${borrowInstances}" var="instance">
            <tr>
                <td>${instance.borrowDate}</td>
                <td>${instance.dueDate}</td>
                <td>${instance.status}</td>
                <td>${instance.readerName}</td>
                <td>
                    <a href="slipDetails?slipId=${instance.borrowSlipId}">
                        Xem phiếu mượn
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>