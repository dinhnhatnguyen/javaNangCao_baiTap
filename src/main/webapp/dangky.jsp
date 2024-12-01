<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Ký Tài Khoản Khách Hàng</title>
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

    <h2 class="form-signin-heading">Đăng Ký Tài Khoản</h2>
    <form action="dangkyController" method="post">
        <div class="mb-3">
            <label for="hoten" class="form-label"><b>Họ tên:</b></label>
            <input type="text" class="form-control" name="hoten" placeholder="Nhập họ và tên" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label"><b>Email:</b></label>
            <input type="email" class="form-control" name="email" placeholder="Nhập email" required>
        </div>
        <div class="mb-3">
            <label for="sodt" class="form-label"><b>Số điện thoại:</b></label>
            <input type="text" class="form-control" name="sodt" placeholder="Nhập số điện thoại" required>
        </div>
        <div class="mb-3">
            <label for="diachi" class="form-label"><b>Địa chỉ:</b></label>
            <input type="text" class="form-control" name="diachi" placeholder="Nhập địa chỉ" required>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label"><b>Tên đăng nhập:</b></label>
            <input type="text" class="form-control" name="tendn" placeholder="Nhập tên đăng nhập" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label"><b>Mật khẩu:</b></label>
            <input type="password" class="form-control" name="matkhau" placeholder="Nhập mật khẩu" required>
        </div>
        <button type="submit" class="btn btn-custom">Đăng Ký Tài Khoản</button>
        <div class="text-center mt-3">
            Đã có tài khoản? <a href="dangNhapController" class="btn btn-link">Đăng nhập</a>
        </div>
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


<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta charset="UTF-8">--%>
<%--<title>Dang ky tai khoan khach hang</title>--%>
<%--<link--%>
<%--  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"--%>
<%--  rel="stylesheet"--%>
<%--  integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"--%>
<%--  crossorigin="anonymous"--%>
<%--/>--%>
<%--<script--%>
<%--  src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"--%>
<%--  integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"--%>
<%--  crossorigin="anonymous"--%>
<%--></script>--%>
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>--%>
<%--<style>--%>
<%--		body {--%>
<%--		  background: radial-gradient(#6bb9f0, #006EB7);--%>
<%--		  height: 100vh;--%>
<%--		  overflow: hidden;--%>
<%--		}--%>
<%--		.hitec-signin {--%>
<%--		  display: flex;    --%>
<%--		  width: 1000px;--%>
<%--		  margin: auto;--%>
<%--		  overflow: hidden;--%>
<%--		}--%>
<%--		.hitec-col-image {--%>
<%--		  width: 650px;--%>
<%--		}--%>
<%--		.hitec-signin-logo {--%>
<%--		  margin-top: 50px;--%>
<%--		}--%>
<%--		.hitec-signin-logo img {--%>
<%--		  width: 112px;--%>
<%--		  height: 112px;--%>
<%--		}--%>
<%--		.hitec-signin-image {--%>
<%--		  margin-top: 70px;    --%>
<%--		}--%>
<%--		.hitec-col-signin {--%>
<%--		  width: 350px;--%>
<%--		}--%>
<%--		.hitec-form-signin {--%>
<%--		  width: 320px;--%>
<%--		  border: 5px solid #6bb9f0;--%>
<%--		  background: #f7f7f7;--%>
<%--		  padding: 15px;--%>
<%--		  height: 370px;--%>
<%--		  margin: 0 auto;--%>
<%--		  margin-top: 205px;--%>
<%--		  position: relative;--%>
<%--		}--%>
<%--		.hitec-form-signin img {--%>
<%--		  border: 5px solid #fff;--%>
<%--		  border-radius: 50%;--%>
<%--		  position: absolute;--%>
<%--		  top: -30px;--%>
<%--		  right: 20px;--%>
<%--		}--%>
<%--		.form-signin-heading {--%>
<%--		  font-size: 25px;--%>
<%--		  font-weight: bold;--%>
<%--		  color: #006eb7;--%>
<%--		  margin-bottom: 20px;--%>
<%--		}--%>
<%--		.hitec-form-signin a.forgot-password {--%>
<%--		  display: block;--%>
<%--		}--%>
<%--		.hitec-form-signin .btn-custom {--%>
<%--		  margin: 20px auto;--%>
<%--		  width: 100%;--%>
<%--		}--%>
<%--		footer {--%>
<%--		  padding-top: 150px;--%>
<%--		  color: #fff;--%>
<%--		}--%>
<%--</style>--%>
<%--</head>--%>
<%--<body class="bg-light">--%>
<%--	    <div class="container">--%>
<%--        <div class="row justify-content-center align-items-center min-vh-100">--%>
<%--            <div class="col-md-4">--%>
<%--                <div class="card">--%>
<%--                    <div class="card-body">    --%>
<%--                        <form action="dangkyController" method="post">--%>
<%--                        	<div class="mb-3">--%>
<%--                                <label for="hoten" class="form-label"><b>Họ tên:</b></label>--%>
<%--                                <input type="text" class="form-control" name="hoten" placeholder="Nhập họ và tên" autofocus>--%>
<%--                            </div>--%>
<%--                            <div class="mb-3">--%>
<%--                                <label for="email" class="form-label"><b>Email:</b></label>--%>
<%--                                <input type="email" class="form-control" name="email" placeholder="Nhập email" autofocus>--%>
<%--                            </div>--%>
<%--                            <div class="mb-3">--%>
<%--                                <label for="sodt" class="form-label"><b>Số điện thoại:</b></label>--%>
<%--                                <input type="text" class="form-control" name="sodt" placeholder="Nhập số điện thoại" autofocus>--%>
<%--                            </div>--%>
<%--                            <div class="mb-3">--%>
<%--                                <label for="diachi" class="form-label"><b>Địa chỉ:</b></label>--%>
<%--                                <input type="text" class="form-control" name="diachi" placeholder="Nhập địa chỉ" autofocus>--%>
<%--                            </div>--%>
<%--                            <div class="mb-3">--%>
<%--                                <label for="username" class="form-label"><b>Tên đăng nhập:</b></label>--%>
<%--                                <input type="text" class="form-control" name="tendn" placeholder="Nhập tên đăng nhập" autofocus>--%>
<%--                            </div>--%>
<%--                            <div class="mb-3">--%>
<%--                                <label for="password" class="form-label"><b>Mật khẩu:</b></label>--%>
<%--                                <input type="password" class="form-control" name="matkhau" placeholder="Nhập mật khẩu" required>--%>
<%--                            </div>--%>
<%--                            <div class="d-grid">--%>
<%--                                <button type="submit" class="btn btn-primary">Đăng Ký Tài Khoản</button>--%>
<%--                            </div>--%>
<%--                            <div class="text-center">--%>
<%--                                Đã có tài khoản?<a href="dangNhapController" class="btn btn-link"> Đăng nhập</a>--%>
<%--                            </div>--%>
<%--                            <%String chuoiError = (String) request.getAttribute("error"); %>--%>
<%--                            <% if (chuoiError != null && !chuoiError.isEmpty()) { %>--%>
<%--                                <p class="text-danger text-center mt-2"><%= chuoiError %></p>--%>
<%--                            <% } %>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--	 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>