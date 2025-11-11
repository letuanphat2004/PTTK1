<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Chi tiết Phiếu mượn</title>
  <style>
    body { font-family: Arial, sans-serif; padding: 20px; }
    .invoice-box { max-width: 800px; margin: auto; padding: 30px; border: 1px solid #eee; box-shadow: 0 0 10px rgba(0, 0, 0, .15); }
    .info { margin-bottom: 20px; }
    .info p { margin: 0; line-height: 1.6; }
    .items-table { width: 100%; border-collapse: collapse; margin-top: 10px; }
    .items-table th, .items-table td { border: 1px solid #ddd; padding: 12px; text-align: left; }
  </style>
</head>
<body>
<div class="invoice-box">
  <h1>Chi tiết Phiếu mượn #${slip.slipId}</h1>
  <a href="javascript:history.back()">Quay lại</a>

  <div classs="info" style="margin-top: 20px;">
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