<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nhà Sách Online</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom sticky-top">
    <div class="container">
        <a class="navbar-brand" href="#"><i class="fas fa-book"></i> Nhà Sách Online</a>
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

<div class="container mt-4">
    <!-- Search Form -->

    <%
        String ml = (String) request.getAttribute("ml"); //maLoai
        String key = (String) request.getAttribute("key");
    %>
    <form method="post" action="sachController" accept-charset="UTF-8" class="mt-4 mb-4">
        <div class="search-form d-flex">
            <input type="text" class="form-control" name="q"
                   placeholder="Tìm kiếm sách..." value="<%=key != null ? key : ""%>">
            <button type="submit" class="btn custom-margin"><i class="fas fa-search"></i>Tìm kiếm</button>
        </div>

    </form>


    <div class="row">
        <!-- Category Sidebar -->
        <div class="col-md-3 mb-4">
            <div class="category-sidebar">
                <h5 class="mb-3"><i class="fas fa-list"></i> Danh mục sách</h5>
                <div class="list-group">
                    <a href="sachController" class="category-link">
                        <i class="fas fa-book-open"></i> Tất cả sách
                    </a>
                    <c:forEach items="${dsloai}" var="loai">
                        <a href="sachController?ml=${loai.maloai}" class="category-link">
                            <i class="fas fa-bookmark"></i> ${loai.tenloai}
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Book Grid -->
        <div class="col-md-9">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach items="${dssach}" var="s">
                    <div class="col">
                        <div class="book-card">
                            <img src="${s.anh}" class="book-image" alt="${s.tensach}" style="min-height: 200px; ">
                            <div class="book-info">
                                <h5 class="book-title">${s.tensach}</h5>
                                <p class="book-author"><i class="fas fa-pen"></i> ${s.tacgia}</p>
                                <p class="book-price">
                                    <i class="fas fa-tag"></i>
                                    <fmt:formatNumber value="${s.gia}" type="currency" currencySymbol="₫"/>
                                </p>
                                <div class="row g-2 mt-auto">
                                    <div class="col-5">
                                        <form method="post" action="cartController">
                                            <input type="hidden" name="id" value="${s.getMasach()}">
                                            <input type="hidden" name="action" value="buy">
                                            <button type="submit"
                                                    class="btn btn-success w-100"
                                                    style="font-size: 14px">Buy
                                            </button>
                                        </form>
                                    </div>
                                    <div class="col-7 text-center">
                                        <form method="post" action="cartController">
                                            <input type="hidden" name="id" value="${s.getMasach()}">
                                            <input type="hidden" name="action" value="addToCart">
                                            <button type="submit"
                                                    class="btn btn-primary w-100 btn-add-cart"
                                                    style="font-size: 14px"><i class="fas fa-cart-plus"></i> Add to cart
                                            </button>
                                        </form>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Pagination -->
            <c:if test="${totalPages > 1}">
                <nav aria-label="Page navigation" class="mt-4">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="sachController?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>
</div>

<footer class="bg-dark text-white mt-5 py-4">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h5><i class="fas fa-book"></i> Nhà Sách Online</h5>
                <p>Đem đến trải nghiệm mua sắm sách tốt nhất cho bạn</p>
            </div>
            <div class="col-md-4">
                <h5><i class="fas fa-map-marker-alt"></i> Liên hệ</h5>
                <p>
                    <i class="fas fa-phone"></i> Hotline: 1900 xxxx<br>
                    <i class="fas fa-envelope"></i> Email: contact@example.com
                </p>
            </div>
            <div class="col-md-4">
                <h5><i class="fas fa-share-alt"></i> Kết nối với chúng tôi</h5>
                <div class="social-links">
                    <a href="#" class="text-white me-3"><i class="fab fa-facebook fa-lg"></i></a>
                    <a href="#" class="text-white me-3"><i class="fab fa-instagram fa-lg"></i></a>
                    <a href="#" class="text-white me-3"><i class="fab fa-twitter fa-lg"></i></a>
                </div>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Add smooth scrolling
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Add loading animation for book cards
    window.addEventListener('load', function () {
        document.querySelectorAll('.book-card').forEach((card, index) => {
            setTimeout(() => {
                card.style.opacity = '1';
                card.style.transform = 'translateY(0)';
            }, index * 100);
        });
    });
</script>
</body>
</html>

<%--<%@ page import="com.nhatnguyen.demoolop.model.khachhangModal.khachhang" %>--%>
<%--<%@ page import="com.nhatnguyen.demoolop.model.sachModal.sachbo" %>--%>
<%--<%@ page import="com.nhatnguyen.demoolop.model.sachModal.sach" %>--%>
<%--<%@ page import="java.util.ArrayList" %>--%>
<%--<!DOCTYPE html>--%>

<%--<%@page language="java" contentType="text/html; charset=UTF-8"--%>
<%--	pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="jakarta.tags.core" prefix="c" %>--%>

<%--<html lang="en">--%>
<%--&lt;%&ndash;<head>&ndash;%&gt;--%>
<%--<title>Nha sach Minh Khai</title>--%>
<%--<meta charset="utf-8">--%>
<%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--<link rel="stylesheet"--%>
<%--	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"--%>
<%--	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"--%>
<%--	crossorigin="anonymous">--%>
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">--%>

<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
<%--	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
<%--	crossorigin="anonymous"></script>--%>
<%--<script--%>
<%--	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"--%>
<%--	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"--%>
<%--	crossorigin="anonymous"></script>--%>
<%--<script--%>
<%--	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"--%>
<%--	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"--%>
<%--	crossorigin="anonymous"></script>--%>

<%--</head>--%>
<%--<style>--%>
<%--	.listSach {--%>
<%--		display: grid;--%>
<%--		grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));--%>
<%--		gap: 25px;--%>
<%--	}--%>
<%--	.card img {--%>
<%--		height: 190px;--%>
<%--	}--%>
<%--	.navbar-custom {--%>
<%--		background-color: #ccd1e3;--%>
<%--	}--%>
<%--	.listSach .card {--%>
<%--	  transition: transform 0.2s ease-in-out;--%>
<%--	  border: 1px solid rgba(0,0,0,0.125);--%>
<%--	}--%>

<%--	.listSach .card:hover {--%>
<%--	  transform: translateY(-5px);--%>
<%--	  box-shadow: 0 4px 8px rgba(0,0,0,0.1);--%>
<%--	}--%>

<%--	.listSach .card-img-wrapper {--%>
<%--	  background: #f8f9fa;--%>
<%--	  border-bottom: 1px solid rgba(0,0,0,0.125);--%>
<%--	}--%>
<%--	.book_card{--%>
<%--		max-height: 400px;--%>
<%--	}--%>

<%--	.listSach .card-title {--%>
<%--	  font-size: 1rem;--%>
<%--	  line-height: 1.4;--%>
<%--	}--%>

<%--	.listSach .btn {--%>
<%--	  padding: 0.375rem 0.75rem;--%>
<%--	}--%>


<%--	.pagination {--%>
<%--	    display: flex;--%>
<%--	    justify-content: center;--%>
<%--	    margin: 20px 0;--%>
<%--	}--%>

<%--	.pagination a {--%>
<%--	    color: black;--%>
<%--	    padding: 8px 16px;--%>
<%--	    text-decoration: none;--%>
<%--	    border: 1px solid #ddd;--%>
<%--	    margin: 0 4px;--%>
<%--	}--%>

<%--	.pagination a.active {--%>
<%--	    background-color: #4CAF50;--%>
<%--	    color: white;--%>
<%--	    border: 1px solid #4CAF50;--%>
<%--	}--%>

<%--	.pagination a:hover:not(.active) {--%>
<%--	    background-color: #ddd;--%>
<%--	}--%>
<%--</style>--%>
<%--<%--%>

<%--%>--%>
<%--<body>--%>
<%--	<nav class="navbar navbar-default navbar-expand-lg navbar-light navbar-custom mb-3">--%>
<%--    <button class="navbar-toggler" type="button" data-toggle="collapse"--%>
<%--        data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"--%>
<%--        aria-expanded="false" aria-label="Toggle navigation">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>
<%--    <div class="collapse navbar-collapse" id="navbarNavDropdown">--%>
<%--        <ul class="navbar-nav">--%>
<%--            <li class="nav-item active">--%>
<%--                <a class="nav-link" href="sachController">Home <span class="sr-only">(current)</span></a>--%>
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
<%--			<%--%>
<%--				if(session.getAttribute("loginadmin") == null) {--%>
<%--			%>--%>
<%--				<li class="nav-item">--%>
<%--					<a class="nav-link text-dark" href="/dangnhapAdminController">--%>
<%--						<span class="fa fa-sign-in-alt"></span> Login admin--%>
<%--					</a>--%>
<%--				</li>--%>
<%--			<%}%>--%>
<%--            <%--%>
<%--            if (session.getAttribute("login") == null) {--%>
<%--            %>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link text-dark" href="dangNhapController">--%>
<%--                    <span class="fa fa-sign-in-alt"></span> Login--%>
<%--                </a>--%>
<%--            </li>--%>

<%--            <li class="nav-item">--%>
<%--                <a class="nav-link text-dark" href="dangky.jsp">--%>
<%--                    <span class="fa-solid fa-user-plus"></span> Sign Up--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <%--%>
<%--            } else {--%>
<%--            	khachhang kh = (khachhang) session.getAttribute("login");--%>
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
<%--            }--%>
<%--            %>--%>

<%--			<%--%>
<%--				if(session.getAttribute("loginadmin") != null) {--%>
<%--			%>--%>
<%--			<li class="nav-item">--%>
<%--				<a class="nav-link" href="/adminController">--%>
<%--					Admin manager--%>
<%--				</a>--%>
<%--			</li>--%>
<%--			<%}%>--%>

<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>

<%--	<div class="px-4">--%>
<%--		<div class="row">--%>
<%--			<div class="col-sm-2">--%>
<%--				<a href="sachController">Tất cả</a>--%>
<%--				<hr />--%>
<%--				<%--%>
<%--					//ArrayList<loai> dsloai = (ArrayList<loai>) request.getAttribute("dsloai");--%>
<%--					//for (loai l : dsloai) {--%>
<%--				%>--%>
<%--				<c:forEach var="l" items="${dsloai}">--%>
<%--					<a href="sachController?ml=${l.getMaloai()}"> ${l.getTenloai()}</a>--%>
<%--					<hr />--%>
<%--				</c:forEach>--%>

<%--			</div>--%>


<%--			<div class="col-sm-8 listSach">--%>
<%--				  <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 g-4">--%>
<%--				    <%--%>
<%--				    sachbo sbo = new sachbo();--%>
<%--				    ArrayList<sach> ds = (ArrayList<sach>) request.getAttribute("dssach");--%>

<%--				    if (ds != null && !ds.isEmpty()) {--%>
<%--				    	int n = ds.size();--%>
<%--				      	for(int i = 0;i<n;i++) {--%>
<%--				      		sach s = ds.get(i);--%>
<%--				    %>--%>
<%--				    <c:forEach var="s" items="${dssach}">--%>
<%--				    <div class="col-sm-3 mb-4 book_card" >--%>
<%--					      <div class="card h-100 d-flex flex-column">--%>
<%--					        <div class="card-img-wrapper" style="height: 250px; padding: 1rem;">--%>
<%--					          <img class="card-img-top h-100 w-100"--%>
<%--					               src="${s.getAnh()}"--%>
<%--					               alt="Card image cap"--%>
<%--					               style="object-fit: contain;">--%>
<%--					        </div>--%>
<%--					        <div class="card-body d-flex flex-column">--%>
<%--					          <h5 class="card-title text-truncate mb-1">${s.getTensach()}</h5>--%>
<%--					          <p class="card-text small text-muted mb-3">--%>
<%--					            Tác giả: ${s.getTacgia()}--%>
<%--					          </p>--%>
<%--					          <div class="row g-2 mt-auto">--%>
<%--					            <div class="col-5">--%>
<%--					              <form method="post" action="cartController">--%>
<%--					                <input type="hidden" name="id" value="${s.getMasach()}">--%>
<%--					                <input type="hidden" name="action" value="buy">--%>
<%--					                <button type="submit"--%>
<%--					                        class="btn btn-success w-100"--%>
<%--					                        style="font-size: 14px">Buy</button>--%>
<%--					              </form>--%>
<%--					            </div>--%>
<%--					            <div class="col-7 text-center">--%>
<%--					              <form method="post" action="cartController">--%>
<%--					                <input type="hidden" name="id" value="${s.getMasach()}">--%>
<%--					                <input type="hidden" name="action" value="addToCart">--%>
<%--					                <button type="submit"--%>
<%--					                        class="btn btn-primary w-100"--%>
<%--					                        style="font-size: 14px">Add to cart</button>--%>
<%--					              </form>--%>
<%--					            </div>--%>
<%--					          </div>--%>
<%--					        </div>--%>
<%--					      </div>--%>
<%--					    </div>--%>
<%--				    </c:forEach>--%>


<%--				    <%--%>
<%--				      }--%>
<%--				    } else {--%>
<%--				      out.println("<div class='col-12'><p class='text-center'>Không có sách nào.</p></div>");--%>
<%--				    }--%>
<%--				    %>--%>
<%--				  </div>--%>


<%--  			</div>--%>
<%--			<div class="col-sm-2">--%>
<%--				<%--%>
<%--					String ml = (String) request.getAttribute("ml"); //maLoai--%>
<%--					String key = (String) request.getAttribute("key");--%>
<%--				%>--%>
<%--				<form method="post" action="sachController" accept-charset="UTF-8">--%>
<%--					<input type="text" class="form-control" name="q"--%>
<%--						placeholder="Tìm kiếm sách..." value="<%=key != null ? key : ""%>">--%>

<%--					<div class="input-group-btn" style="margin-top: 20px;">--%>
<%--						<button type="submit" class="btn btn-primary custom-margin">Tìm kiếm</button>--%>
<%--					</div>--%>

<%--				</form>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--	</div>--%>

<%--		<div class="container">--%>
<%--		    <div class="row">--%>
<%--		        <div class="col-sm-12 text-center">--%>
<%--		            <nav aria-label="Page navigation">--%>
<%--		                <ul class="pagination justify-content-center">--%>
<%--		                    <%--%>
<%--		                        int currentPage = (Integer) request.getAttribute("currentPage");--%>
<%--		                        int totalPages = (Integer) request.getAttribute("totalPages");--%>

<%--		                        if (currentPage > 1) {--%>
<%--		                    %>--%>
<%--		                        <li class="page-item">--%>
<%--		                            <a class="page-link" href="sachController?page=<%= currentPage - 1 %>" aria-label="Previous">--%>
<%--		                                <span aria-hidden="true">&laquo;</span>--%>
<%--		                            </a>--%>
<%--		                        </li>--%>
<%--		                    <%--%>
<%--		                        }--%>

<%--		                        for (int i = 1; i <= totalPages; i++) {--%>
<%--		                            if (i == currentPage) {--%>
<%--		                    %>--%>
<%--		                                <li class="page-item active"><a class="page-link" href="#"><%= i %></a></li>--%>
<%--		                    <%--%>
<%--		                            } else {--%>
<%--		                    %>--%>
<%--		                                <li class="page-item"><a class="page-link" href="sachController?page=<%= i %>"><%= i %></a></li>--%>
<%--		                    <%--%>
<%--		                            }--%>
<%--		                        }--%>

<%--		                        if (currentPage < totalPages) {--%>
<%--		                    %>--%>
<%--		                        <li class="page-item">--%>
<%--		                            <a class="page-link" href="sachController?page=<%= currentPage + 1 %>" aria-label="Next">--%>
<%--		                                <span aria-hidden="true">&raquo;</span>--%>
<%--		                            </a>--%>
<%--		                        </li>--%>
<%--		                    <%--%>
<%--		                        }--%>
<%--		                    %>--%>
<%--		                </ul>--%>
<%--		            </nav>--%>
<%--		        </div>--%>
<%--		    </div>--%>
<%--		</div>--%>
<%--</body>--%>
<%--</html>--%>