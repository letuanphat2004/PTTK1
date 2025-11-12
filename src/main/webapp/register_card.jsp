<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Hoàn tất Hồ sơ & Đăng ký thẻ</title>
  <style>
    /* === 1. ĐỒNG BỘ BODY (giống login.jsp) === */
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      display: flex;
      justify-content: center;
      align-items: center;
      /* Thêm padding cho trường hợp form dài hơn màn hình */
      padding: 2rem 0;
      /* Đảm bảo chiều cao tối thiểu */
      min-height: 100vh;
      box-sizing: border-box;
      margin: 0;
    }

    /* === 2. ĐỒNG BỘ CONTAINER (giống login.jsp) === */
    .container {
      background-color: #ffffff;
      padding: 2.5rem; /* Đồng bộ padding */
      border-radius: 8px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      width: 450px; /* Rộng hơn login để chứa form */
    }
    h2 {
      text-align: center;
      margin-top: 0;
      color: #333;
      font-size: 1.8rem;
      margin-bottom: 1.5rem;
    }

    /* === 3. ĐỒNG BỘ FORM === */
    .form-group {
      margin-bottom: 1rem; /* Đồng bộ khoảng cách */
    }
    .form-group label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
      color: #555;
      text-align: left; /* Căn trái label */
    }
    /* Đồng bộ tất cả input/select */
    .form-group input[type="text"],
    .form-group input[type="password"],
    .form-group input[type="email"],
    .form-group input[type="date"],
    .form-group select {
      width: 100%;
      padding: 12px; /* Đồng bộ padding */
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
      font-size: 1rem; /* Đồng bộ font-size */
    }

    /* === 4. ĐỒNG BỘ NÚT BẤM === */
    input[type="submit"] {
      width: 100%;
      padding: 12px;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 1rem;
      font-weight: bold;
      transition: background-color 0.3s ease;
      margin-top: 1rem;
    }
    input[type="submit"]:hover {
      background-color: #0056b3;
    }

    /* === 5. NÚT QUAY LẠI === */
    .back-link {
      display: block;
      text-align: center;
      margin-top: 1.5rem;
      color: #007bff;
      text-decoration: none;
      font-size: 0.9rem;
    }
    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Hoàn tất Hồ sơ & Đăng ký thẻ</h2>

  <form action="${pageContext.request.contextPath}/registerCard" method="post">

    <div class="form-group">
      <label for="fullname">Họ và tên:</label>
      <input type="text" id="fullname" name="fullname" value="${sessionScope.account.fullname}" required>
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" value="${sessionScope.account.email}" required>
    </div>

    <div class="form-group">
      <label for="phone">Số điện thoại:</label>
      <input type="text" id="phone" name="phone" value="${sessionScope.account.phone}" required>
    </div>

    <div class="form-group">
      <label for="address">Địa chỉ:</label>
      <input type="text" id="address" name="address" value="${sessionScope.account.address}" required>
    </div>

    <div class="form-group">
      <label for="dob">Ngày sinh:</label>
      <%-- (Lưu ý: EL ${} cho type="date" có thể cần định dạng yyyy-MM-dd) --%>
      <input type="date" id="dob" name="dob" value="${sessionScope.account.dob}" required>
    </div>

    <hr style="border: 0; border-top: 1px solid #eee; margin: 1.5rem 0;">

    <div class="form-group">
      <label for="cardType">Loại thẻ:</label>
      <select id="cardType" name="cardType" required>
        <option value="Sinh viên">Sinh viên</option>
        <option value="Giảng viên">Giảng viên</option>
        <option value="Người lớn">Người lớn (Bên ngoài)</option>
      </select>
    </div>

    <input type="submit" value="Xác nhận & Nhận thẻ">
  </form>

  <a href="home.jsp" class="back-link">Quay lại trang chủ</a>
</div>
</body>
</html>