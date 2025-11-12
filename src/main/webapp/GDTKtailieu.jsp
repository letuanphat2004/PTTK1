<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 1. BỔ SUNG THƯ VIỆN JSTL (Rất quan trọng) --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Thống kê Tài liệu</title>
  <style>
    /* === 2. ĐỒNG BỘ BODY VÀ FONT === */
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9; /* Nền xám nhạt */
      margin: 0;
      padding: 20px; /* Thêm padding chung */
    }

    /* === 3. ĐỒNG BỘ KHUNG "CARD" === */
    .container {
      width: 90%;
      max-width: 1200px;
      margin: 0 auto; /* Tự động căn giữa */
      background-color: #ffffff;
      padding: 2.5rem; /* Padding đồng bộ */
      border-radius: 8px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    /* Đồng bộ H1 */
    h1 {
      font-size: 1.8rem;
      color: #333;
      margin-top: 0;
      margin-bottom: 0.5rem;
    }

    /* === 4. STYLE CHO LINK "QUAY LẠI" (Như bạn yêu cầu) === */
    .back-link {
      display: inline-block;
      margin-bottom: 20px;
      color: #551a8b; /* Màu tím */
      text-decoration: underline;
      font-size: 0.9em;
    }

    /* === 5. ĐỒNG BỘ FORM LỌC === */
    .filter-form {
      background-color: #f8f9fa; /* Màu nền xám của form */
      padding: 20px;
      border-radius: 8px;
      margin-bottom: 25px;
      display: flex;
      align-items: center;
      gap: 15px; /* Khoảng cách giữa các phần tử form */
    }
    .filter-form label {
      font-weight: bold;
      margin-left: 5px;
    }
    /* Đồng bộ ô input ngày */
    .filter-form input[type="date"] {
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      font-size: 0.95em;
    }

    /* === 6. ĐỒNG BỘ NÚT BẤM === */
    /* Nút "Lọc" (Xanh dương) */
    .filter-form input[type="submit"] {
      padding: 10px 18px;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 1rem;
      font-weight: bold;
      transition: background-color 0.3s ease;
    }
    .filter-form input[type="submit"]:hover {
      background-color: #0056b3;
    }
    /* Nút "Xem tất cả" (Xám) */
    .filter-form a {
      padding: 10px 18px;
      background-color: #6c757d;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      font-size: 1rem;
      font-weight: bold;
      transition: background-color 0.3s ease;
    }
    .filter-form a:hover {
      background-color: #5a6268;
      text-decoration: none;
    }

    /* === 7. ĐỒNG BỘ BẢNG === */
    .stats-table {
      width: 100%;
      border-collapse: collapse;
    }
    .stats-table th, .stats-table td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: left;
      font-size: 1rem; /* Đồng bộ font */
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

  <h1>Thống kê Tài liệu theo Lượt mượn</h1>
  <a href="GDThongke.jsp" class="back-link">Quay lại Menu Báo cáo</a>

  <div class="filter-form">
    <form action="docStats" method="GET" style="display: flex; align-items: center; gap: 15px;">
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

</div>
</body>
</html>