<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chi tiết Lần mượn</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .stats-table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        .stats-table th, .stats-table td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        .stats-table a { color: #007bff; text-decoration: none; }
    </style>
</head>
<body>
<h2>Chi tiết các lần mượn cho: ${documentName}</h2>

<a href="docStats?startDate=${startDate}&endDate=${endDate}">Quay lại thống kê</a>

<table class="stats-table" style="margin-top: 20px;">
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
</body>
</html>