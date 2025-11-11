<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>

    <style>
        /* CSS cho toàn bộ trang */
        body {
            font-family: Arial, sans-serif; /* Đặt font chữ dễ đọc */
            background-color: #f4f4f9; /* Màu nền xám nhạt */
            display: flex; /* Sử dụng Flexbox để căn giữa */
            justify-content: center; /* Căn giữa theo chiều ngang */
            align-items: center; /* Căn giữa theo chiều dọc */
            height: 100vh; /* Chiều cao 100% màn hình */
            margin: 0;
        }

        /* CSS cho khung đăng nhập */
        .login-container {
            background-color: #ffffff; /* Màu nền trắng cho khung */
            padding: 2rem; /* Khoảng cách đệm bên trong */
            border-radius: 8px; /* Bo góc */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng mềm */
            width: 300px; /* Độ rộng cố định */
            text-align: center; /* Căn giữa nội dung bên trong */
        }

        /* CSS cho tiêu đề H1 */
        h1 {
            font-size: 1.8rem;
            color: #333;
            margin-bottom: 1.5rem;
        }

        /* CSS cho các ô input (text và password) */
        input[type="text"],
        input[type="password"] {
            width: 100%; /* Chiếm 100% độ rộng của cha */
            padding: 12px; /* Đệm bên trong */
            margin-bottom: 1rem; /* Khoảng cách với phần tử dưới */
            border: 1px solid #ddd; /* Viền xám nhạt */
            border-radius: 4px; /* Bo góc nhẹ */
            box-sizing: border-box; /* Giúp padding không ảnh hưởng độ rộng */
        }

        /* CSS cho nút "Đăng nhập" */
        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff; /* Màu xanh dương */
            color: white; /* Chữ màu trắng */
            border: none;
            border-radius: 4px;
            cursor: pointer; /* Biểu tượng bàn tay khi di chuột */
            font-size: 1rem;
            font-weight: bold;
            transition: background-color 0.3s ease; /* Hiệu ứng đổi màu */
        }

        /* CSS cho nút khi di chuột vào */
        input[type="submit"]:hover {
            background-color: #0056b3; /* Màu xanh đậm hơn */
        }

        /* CSS cho thông báo lỗi */
        .error {
            color: #d9534f; /* Màu đỏ */
            font-size: 0.9rem;
            margin-top: 1rem;
        }

        /* CSS cho link đăng ký */
        .register-link {
            display: block;
            margin-top: 1.5rem;
            color: #007bff;
            text-decoration: none; /* Bỏ gạch chân */
            font-size: 0.9rem;
        }

        .register-link:hover {
            text-decoration: underline; /* Thêm gạch chân khi di chuột */
        }
    </style>
</head>
<body>

<div class="login-container">
    <h1>Đăng nhập</h1>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <input type="text" name="username" placeholder="Tên đăng nhập" required>

        <input type="password" name="password" placeholder="Mật khẩu" required>

        <input type="submit" value="Đăng nhập">
    </form>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

</div>

</body>
</html>