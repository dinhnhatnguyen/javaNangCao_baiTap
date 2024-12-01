<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập Hệ Thống</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <style>
        body {
            background: radial-gradient(#6bb9f0, #006EB7);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
        }

        .hitec-signin {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            padding: 40px;
            width: 400px;
        }

        .hitec-signin-logo {
            text-align: center;
            margin-bottom: 30px;
        }

        .hitec-signin-logo img {
            width: 100px;
            height: 100px;
        }

        .form-signin-heading {
            font-size: 24px;
            font-weight: bold;
            color: #006eb7;
            margin-bottom: 20px;
        }

        .btn-custom {
            background-color: #006eb7;
            color: #fff;
            font-weight: bold;
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            width: 100%;
            cursor: pointer;
        }

        .btn-custom:hover {
            background-color: #0056b3;
        }

        .text-danger {
            color: #dc3545 !important;
        }
    </style>
</head>
<body>
<div class="hitec-signin">


    <h2 class="form-signin-heading">Đăng Nhập Hệ Thống</h2>
    <form action="dangNhapController" method="post">
        <div class="mb-3">
            <label for="username" class="form-label"><b>Tên đăng nhập:</b></label>
            <input type="text" class="form-control" name="username" placeholder="Tên đăng nhập/Email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label"><b>Mật khẩu:</b></label>
            <input type="password" class="form-control" name="password" placeholder="Mật khẩu" required>
        </div>
        <%
            int d = 0;
            if (session.getAttribute("demCustomer") != null)
                d = (int) session.getAttribute("demCustomer");
            if (d >= 3) {
        %>
        <div class="mb-3">
            <label>Mã CAPTCHA:</label>
            <img src="CaptchaServlet" class="img-fluid mb-2" alt="CAPTCHA" />
            <input type="text" class="form-control" name="answer" placeholder="Nhập mã CAPTCHA" required>
        </div>
        <%}%>
        <button type="submit" class="btn btn-custom">Đăng Nhập</button>
        <%
            String chuoiError = (String) request.getAttribute("error");
            if (chuoiError != null && !chuoiError.isEmpty()) {
        %>
        <p class="text-danger text-center mt-2"><%= chuoiError %></p>
        <%}%>
    </form>
</div>
</body>
</html>

