<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.nhatnguyen.demoolop.model.khachhangModal.khachhang" %>
<%@ page import="com.nhatnguyen.demoolop.model.lichsuModal.lichsu" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử đặt mua</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<style>
    :root {
        --primary-color: #2575fc;
        --secondary-color: #6a11cb;
    }

    body {
        background-color: #f8f9fa;
    }

    .navbar-custom {
        background: linear-gradient(135deg, var(--secondary-color) 0%, var(--primary-color) 100%);
        padding: 1rem 0;
    }

    .navbar-brand {
        font-size: 1.5rem;
        font-weight: bold;
        color: white !important;
    }

    .nav-link {
        color: white !important;
        font-weight: 500;
        transition: all 0.3s;
        padding: 0.5rem 1rem;
        border-radius: 5px;
    }

    .nav-link:hover {
        background-color: rgba(255, 255, 255, 0.1);
        transform: translateY(-2px);
    }

    .search-form {
        background: white;
        border-radius: 25px;
        overflow: hidden;
        box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
    }

    .search-form input {
        border: none;
        padding: 0.5rem 1rem;
    }

    .search-form button {
        background: var(--primary-color);
        color: white;
        border: none;
        padding: 0.5rem 1.5rem;
    }

    .category-sidebar {
        background: white;
        border-radius: 10px;
        padding: 1rem;
        box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
    }

    .category-link {
        color: #333;
        text-decoration: none;
        padding: 0.5rem 1rem;
        display: block;
        transition: all 0.3s;
        border-radius: 5px;
    }

    .category-link:hover {
        background-color: #f8f9fa;
        color: var(--primary-color);
        transform: translateX(5px);
    }

    .book-card {
        background: white;
        border-radius: 10px;
        overflow: hidden;
        transition: all 0.3s;
        height: 100%;
        box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
    }

    .book-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 5px 20px rgba(0, 0, 0, 0.15);
    }

    .book-image {
        height: 200px;
        object-fit: cover;
        width: 100%;
    }

    .book-info {
        padding: 1rem;
    }

    .book-title {
        font-size: 1.1rem;
        font-weight: bold;
        margin-bottom: 0.5rem;
        color: #333;
    }

    .book-author {
        color: #666;
        font-size: 0.9rem;
    }

    .book-price {
        color: var(--primary-color);
        font-weight: bold;
        font-size: 1.2rem;
        margin: 0.5rem 0;
    }

    .btn-add-cart {
        background: linear-gradient(135deg, var(--secondary-color) 0%, var(--primary-color) 100%);
        color: white;
        border: none;
        width: 100%;
        padding: 0.5rem;
        border-radius: 5px;
        transition: all 0.3s;
    }

    .btn-add-cart:hover {
        transform: scale(1.05);
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
    }

    .pagination {
        margin-top: 2rem;
        justify-content: center;
    }

    .page-link {
        color: var(--primary-color);
        border: none;
        margin: 0 5px;
        border-radius: 5px;
    }

    .page-link:hover {
        background-color: var(--primary-color);
        color: white;
    }

    .page-item.active .page-link {
        background-color: var(--primary-color);
        border-color: var(--primary-color);
    }
</style>
<body>
<%--<nav class="navbar navbar-default navbar-expand-lg navbar-light navbar-custom mb-3">--%>
<%--    <button class="navbar-toggler" type="button" data-toggle="collapse"--%>
<%--            data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"--%>
<%--            aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>
<%--    <div class="collapse navbar-collapse" id="navbarNavDropdown">--%>
<%--        <ul class="navbar-nav">--%>
<%--            <li class="nav-item active">--%>
<%--                <a class="nav-link" href="sachController">Home <span class="sr-only"></span></a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="cartController">Cart</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="xacnhanController">Xác nhận đặt mua</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="lichsuController">Lịch sử mua hàng</a>--%>
<%--            </li>--%>
<%--        </ul>--%>

<%--        <ul class="navbar-nav ms-auto ml-auto">--%>
<%--            <%--%>
<%--                if (session.getAttribute("login") == null) {--%>
<%--            %>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link text-dark" href="dangNhapController">--%>
<%--                    <span class="fa fa-sign-in-alt"></span> Login--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <%--%>
<%--            } else {--%>
<%--                khachhang kh = (khachhang) session.getAttribute("login");--%>
<%--            %>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link text-dark" href="logoutController">--%>
<%--                    <span class="fa fa-sign-out-alt"></span> Log out--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="#">--%>
<%--                    <span class="fa fa-user"></span> Xin Chào, <%=kh.getHoten()%>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom sticky-top">
    <div class="container">
        <a class="navbar-brand" href="sachController"><i class="fas fa-book"></i> Nhà Sách Online</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="sachController">
                        <i class="fas fa-home"></i> Trang chủ
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="cartController">
                        <i class="fas fa-shopping-cart"></i> Giỏ hàng
                        <c:if test="${not empty sessionScope.cart}">
                            <span class="badge bg-danger">${sessionScope.cart.cartItems.size()}</span>
                        </c:if>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="lichsuController">
                        <i class="fas fa-history"></i> Lịch sử mua
                    </a>
                </li>
                <c:if test="${sessionScope.loginadmin}">
                    <li class="nav-item">
                        <a class="nav-link" href="/adminController">
                            <i class="fas fa-cog"></i> Admin manager
                        </a>
                    </li>
                </c:if>

            </ul>
            <ul class="navbar-nav">
                <c:choose>
                    <c:when test="${empty sessionScope.login}">
                        <li class="nav-item">
                            <a class="nav-link" href="/dangNhapController">
                                <i class="fas fa-sign-in-alt"></i> Đăng nhập
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/dangkyController">
                                <i class="fas fa-user-plus"></i> Đăng ký
                            </a>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link">
                                <i class="fas fa-user"></i> Xin chào, ${sessionScope.login.hoten}
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="logoutController">
                                <i class="fas fa-sign-out-alt"></i> Đăng xuất
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${empty sessionScope.loginadmin}">
                        <li class="nav-item">
                            <a class="nav-link" href="dangnhapAdminController">
                                <i class="fas fa-sign-in-alt"></i> Đăng nhập admin
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="/logoutAdmin">
                                <i class="fas fa-sign-out-alt"></i> Đăng xuất admin
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/adminController">
                                <i class="fas fa-cog"></i> Admin manager
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>

        </div>
    </div>
</nav>
<div class="container mt-3">
    <h2>Lịch sử đặt mua</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Họ tên người</th>
            <th>Tên sách</th>
            <th>Số lượng mua</th>
            <th>Giá</th>
            <th>Thành tiền</th>
            <th>Đã mua</th>
            <th>Ngày mua</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <%
                ArrayList<lichsu> ds = (ArrayList<lichsu>) request.getAttribute("dslichsutrue");
                if (ds != null && !ds.isEmpty()) {
                    int n = ds.size();
                    for (int i = 0; i < n; i++) {
                        lichsu ls = ds.get(i);
            %>
            <td><%=ls.getHoten()%>
            </td>
            <td><%=ls.getTensach() %>
            </td>
            <td><%=ls.getSoLuongMua() %>
            </td>
            <td><%=ls.getGia() %>
            </td>
            <td><%=ls.getThanhTien() %>
            </td>
            <td><%=ls.isDamua() %>
            </td>
            <td><%=ls.getNgayMua() %>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <table class="table table-striped mt-3">
        <thead>
        <tr>
            <th>Họ tên người</th>
            <th>Tên sách</th>
            <th>Số lượng mua</th>
            <th>Giá</th>
            <th>Thành tiền</th>
            <th>Đã mua</th>
            <th>Ngày mua</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <%
                ArrayList<lichsu> ds2 = (ArrayList<lichsu>) request.getAttribute("dslichsufalse");
                if (ds2 != null && !ds2.isEmpty()) {
                    int n = ds2.size();
                    for (int i = 0; i < n; i++) {
                        lichsu ls = ds2.get(i);
            %>
            <td><%=ls.getHoten()%>
            </td>
            <td><%=ls.getTensach() %>
            </td>
            <td><%=ls.getSoLuongMua() %>
            </td>
            <td><%=ls.getGia() %>
            </td>
            <td><%=ls.getThanhTien() %>
            </td>
            <td><%=ls.isDamua() %>
            </td>
            <td><%=ls.getNgayMua() %>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

</body>
</body>
</html>