<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

  <title>Chi tiết Phiếu mượn</title>
  <style>
    /* === 1. ĐỒNG BỘ BODY VÀ FONT === */
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9; /* Nền xám nhạt */
      margin: 0;
      padding: 20px;
    }

    /* === 2. ĐỒNG BỘ KHUNG "CARD" === */
    /* Giữ nguyên class .invoice-box của bạn */
    .invoice-box {
      max-width: 800px;
      margin: auto;
      background-color: #ffffff; /* Nền trắng */
      padding: 2.5rem; /* Padding đồng bộ */
      border-radius: 8px; /* Bo góc */
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng */
    }

    /* === 3. ĐỒNG BỘ TIÊU ĐỀ === */
    h1 {
      font-size: 1.8rem;
      color: #333;
      margin-top: 0;
      margin-bottom: 0.5rem;
    }
    h3 {
      font-size: 1.3rem;
      color: #333;
      border-bottom: 2px solid #eee;
      padding-bottom: 5px;
      margin-top: 25px;
    }

    /* === 4. STYLE CHO LINK "QUAY LẠI" (Như bạn yêu cầu) === */
    /* Đổi style cho link quay lại của bạn */
    .invoice-box > a {
      display: inline-block;
      margin-bottom: 25px;
      color: #551a8b; /* Màu tím */
      text-decoration: underline;
      font-size: 0.9em;
    }

    /* Style cho thông tin chung */
    .info p {
      margin: 0;
      line-height: 1.6;
      font-size: 1.1em;
    }

    /* === 5. ĐỒNG BỘ BẢNG (Giống trang trước) === */
    .items-table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 15px;
    }
    .items-table th, .items-table td {
      border: 1px solid #ddd;
      padding: 12px;
      text-align: left;
      font-size: 1rem;
    }
    .items-table th {
      background-color: #f4f4f9;
    }
    .items-table tr:nth-child(even) {
      background-color: #fdfdfd;
    }
  </style>
</head>
<body>
<div class="invoice-box">
  <h1>Chi tiết Phiếu mượn #${slip.slipId}</h1>
  <a href="javascript:history.back()">Quay lại</a>

  <%-- Sửa lỗi typo "classs" thành "class" --%>
  <div class="info" style="margin-top: 20px;">
    <h3>Thông tin chung</h3>
    <p><strong>Bạn đọc:</strong> ${slip.readerName}</p>
    <p><strong>Nhân viên:</strong> ${slip.staffName}</p>
    <p><strong>Ghi chú:</strong> ${empty slip.note ? 'Không có' : slip.note}</p>
  </div>

  <div style="margin-top: 20px;">
    <h3>Các tài liệu đã mượn</h3>
    <table class="items-table">
      <thead>
      <tr>
        <th>Tên tài liệu</th>
        <th>Trạng thái</th>
        <th>Ngày mượn</th>
        <th>Ngày hẹn trả</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${slip.items}" var="item">
        <tr>
          <td>${item.documentTitle}</td>
          <td>${item.status}</td>
          <td>${item.borrowDate}</td>
          <td>${item.dueDate}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>