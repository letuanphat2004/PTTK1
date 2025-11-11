<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Thống kê Tài liệu</title>
  <style>
    body { font-family: Arial, sans-serif; padding: 20px; }
    .filter-form { background-color: #f4f4f4; padding: 20px; border-radius: 8px; margin-bottom: 20px; }
    .stats-table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    .stats-table th, .stats-table td { border: 1px solid #ddd; padding: 12px; text-align: left; }
    .stats-table a { color: #007bff; text-decoration: none; }
  </style>
</head>
<body>

<h1>Thống kê Tài liệu theo Lượt mượn</h1>
<a href="home.jsp">Quay lại trang chủ</a>

<div class="filter-form">
  <form action="docStats" method="GET">
    <label>Từ ngày:</label>
    <input type="date" name="startDate" value="${startDate}">
    <label>Đến ngày:</label>
    <input type="date" name="endDate" value="${endDate}">
    <input type="submit" value="Lọc">
    <a href="docStats">Xem tất cả</a>
  </form>
</div>

<table class="stats-table">
  <thead>
  <tr>
    <th>Tên tài liệu</th>
    <th>Tác giả</th>
    <th>Số lượt mượn</th>
    <th>Chi tiết</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${statsList}" var="stat">
    <tr>
      <td>${stat.documentTitle}</td>
      <td>${stat.documentAuthor}</td>
      <td>${stat.borrowCount}</td>
      <td>
        <a href="borrowDetails?docId=${stat.documentId}&start=${startDate}&end=${endDate}">
          Xem các lần mượn
        </a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>