<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Hoàn tất Hồ sơ & Đăng ký thẻ</title>
  <style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f9; display: flex;
      justify-content: center; align-items: center; padding: 2rem 0; }
    .container { background-color: #ffffff; padding: 2rem 3rem; border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); width: 450px; }
    h2 { text-align: center; margin-top: 0; color: #333; }
    .form-group { margin-bottom: 1.2rem; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
    .form-group input, .form-group select {
      width: 100%; padding: 10px; border: 1px solid #ddd;
      border-radius: 4px; box-sizing: border-box; /* Quan trọng */
    }
    input[type="submit"] {
      width: 100%; padding: 12px; background-color: #007bff;
      color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 1.1em;
      font-weight: bold; transition: background-color 0.3s; margin-top: 1rem;
    }
    input[type="submit"]:hover { background-color: #0056b3; }
  </style>
</head>
<body>
<div class="container">
  <h2>Hoàn tất Hồ sơ & Đăng ký thẻ</h2>

  <form action="${pageContext.request.contextPath}/registerCard" method="post">

    <%--
      Chúng ta dùng sessionScope.account để điền sẵn thông tin
      (value="${sessionScope.account.fullname}")
      Điều này giúp người dùng chỉ cần sửa, không cần gõ lại.
    --%>

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
</div>
</body>
</html>