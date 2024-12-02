<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
	<title>Giỏ hàng của bạn</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	<style>
		.cart-container {
			max-width: 1200px;
			margin: 0 auto;
			padding: 20px;
		}

		.product-card {
			background: #fff;
			border-radius: 8px;
			box-shadow: 0 2px 4px rgba(0,0,0,0.1);
			padding: 15px;
			margin-bottom: 15px;
			transition: transform 0.2s;
		}

		.product-card:hover {
			transform: translateY(-2px);
		}

		.product-image {
			max-height: 120px;
			object-fit: contain;
		}

		.navbar-custom {
			background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
		}

		.nav-link {
			color: white !important;
			font-weight: 500;
			transition: all 0.3s;
		}

		.nav-link:hover {
			transform: translateY(-2px);
			text-shadow: 0 2px 4px rgba(0,0,0,0.2);
		}

		.quantity-input {
			width: 70px;
			text-align: center;
		}

		.total-amount {
			background: #f8f9fa;
			padding: 20px;
			border-radius: 8px;
			margin-top: 20px;
		}

		.action-buttons {
			gap: 10px;
		}

		.btn-custom {
			transition: all 0.3s;
		}

		.btn-custom:hover {
			transform: scale(1.05);
		}
	</style>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom mb-4">
	<div class="container">
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
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="xacnhanController">
						<i class="fas fa-check-circle"></i> Xác nhận đặt mua
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="lichsuController">
						<i class="fas fa-history"></i> Lịch sử mua hàng
					</a>
				</li>
			</ul>

			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${empty sessionScope.login}">
						<li class="nav-item">
							<a class="nav-link" href="dangNhapController">
								<i class="fas fa-sign-in-alt"></i> Đăng nhập
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item">
							<a class="nav-link" href="#">
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
			</ul>
		</div>
	</div>
</nav>

<div class="cart-container">
	<h2 class="mb-4"><i class="fas fa-shopping-cart"></i> Giỏ hàng của bạn</h2>

	<form action="cartController" method="post">
		<c:choose>
			<c:when test="${not empty sessionScope.cart.cartItems}">
				<div class="cart-items">
					<c:forEach items="${sessionScope.cart.cartItems}" var="item">
						<div class="product-card d-flex align-items-center justify-content-between">
							<div class="form-check">
								<input type="checkbox" name="selectedBooks"
									   value="${item.book.masach}"
									   class="form-check-input"
									   onchange="toggleDeleteButton()">
							</div>

							<img src="${item.book.anh}" alt="${item.book.tensach}"
								 class="product-image">

							<div class="product-info">
								<h6>${item.book.tensach}</h6>
								<p class="text-muted">
									Giá: <fmt:formatNumber value="${item.book.gia}" type="currency"
														   currencySymbol="₫"/>
								</p>
							</div>

							<div class="quantity-control">
								<input type="number" name="quantity_${item.book.masach}"
									   value="${item.quantity}" min="1"
									   class="form-control quantity-input">
								<button type="submit" name="action"
										value="updateId_${item.book.masach}"
										class="btn btn-primary btn-sm mt-2">
									<i class="fas fa-sync-alt"></i> Cập nhật
								</button>
							</div>

							<button type="submit" name="action"
									value="delete_${item.book.masach}"
									class="btn btn-danger btn-sm">
								<i class="fas fa-trash"></i>
							</button>
						</div>
					</c:forEach>
				</div>

				<div class="total-amount">
					<h3>Tổng tiền:
						<fmt:formatNumber value="${sessionScope.cart.totalAmount}"
										  type="currency" currencySymbol="₫"/>
					</h3>
				</div>

				<div class="action-buttons d-flex mt-4">
					<button type="submit" name="action" value="clearAll"
							class="btn btn-danger btn-custom">
						<i class="fas fa-trash-alt"></i> Xóa tất cả
					</button>

					<button type="submit" name="action" value="deleteSelected"
							id="deleteSelectedButton"
							class="btn btn-warning btn-custom d-none">
						<i class="fas fa-minus-circle"></i> Xóa đã chọn
					</button>

					<button type="submit" formaction="xacnhanController"
							name="btnxacnhan"
							class="btn btn-success btn-custom">
						<i class="fas fa-check-circle"></i> Xác nhận đặt mua
					</button>
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-info">
					<i class="fas fa-info-circle"></i> Giỏ hàng của bạn đang trống.
				</div>
			</c:otherwise>
		</c:choose>
	</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
	function toggleDeleteButton() {
		const checkboxes = document.querySelectorAll('input[name="selectedBooks"]');
		const deleteButton = document.getElementById('deleteSelectedButton');
		const hasChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);

		deleteButton.classList.toggle('d-none', !hasChecked);
	}
</script>
</body>
</html>

<%--<%@ page import="com.nhatnguyen.demoolop.model.khachhangModal.khachhang" %>--%>
<%--<%@ page import="com.nhatnguyen.demoolop.model.cartModal.cartbo" %>--%>
<%--<%@ page import="com.nhatnguyen.demoolop.model.cartModal.cart" %>--%>
<%--<!DOCTYPE html>--%>

<%--<%@page language="java" contentType="text/html; charset=UTF-8"--%>
<%--	pageEncoding="UTF-8"%>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--<title>Giỏ hàng của bạn</title>--%>
<%--<meta charset="utf-8">--%>
<%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--<link rel="stylesheet"--%>
<%--	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"--%>
<%--	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"--%>
<%--	crossorigin="anonymous">--%>
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
<%--<style>--%>
<%--.listSach {--%>
<%--	display: grid;--%>
<%--	grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));--%>
<%--	gap: 25px;--%>
<%--}--%>

<%--p {--%>
<%--	margin: 0;--%>
<%--}--%>

<%--.hidden {--%>
<%--	display: none;--%>
<%--}--%>

<%--.product:not(:last-child) {--%>
<%--	border-bottom: 1px solid black;--%>
<%--}--%>
<%--	.navbar-custom {--%>
<%--		background-color: #ccd1e3;--%>
<%--	}--%>
<%--</style>--%>
<%--</head>--%>
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
<%--            <%--%>
<%--            if (session.getAttribute("login") == null) {--%>
<%--            %>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link text-dark" href="dangNhapController">--%>
<%--                    <span class="fa fa-sign-in-alt"></span> Login--%>
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
<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>

<%--	<div class="container m-auto">--%>
<%--		<form action="cartController" method="post">--%>
<%--			<h1>Giỏ hàng của bạn</h1>--%>
<%--			<hr>--%>
<%--			<div class="cart">--%>
<%--				<%--%>
<%--				cartbo cbo = (cartbo) session.getAttribute("cart");--%>
<%--				if (cbo != null && !cbo.getCartItems().isEmpty()) {--%>
<%--					for (cart s : cbo.getCartItems()) {--%>
<%--				%>--%>
<%--				<div--%>
<%--					class="product w-100 d-flex align-items-center justify-content-between p-2">--%>
<%--					<div>--%>
<%--						<input type="checkbox" name="selectedBooks"--%>
<%--							value="<%=s.getBook().getMasach()%>" onchange="toggleDeleteButton()">--%>
<%--					</div>--%>
<%--					<div>--%>
<%--						<img style="height: 100px" alt="" src="<%=s.getBook().getAnh()%>">--%>
<%--					</div>--%>
<%--					<div>--%>
<%--						<h6><%=s.getBook().getTensach()%></h6>--%>
<%--						<p>--%>
<%--							Giá:--%>
<%--							<%=s.getBook().getGia()%>đ--%>
<%--						</p>--%>
<%--					</div>--%>
<%--					<div>--%>
<%--						<input type="hidden" name="bookId"--%>
<%--							value="<%=s.getBook().getMasach()%>"> <input--%>
<%--							type="number" name="quantity_<%=s.getBook().getMasach()%>"--%>
<%--							value="<%=s.getQuantity()%>" min="1" style="width: 60px;">--%>
<%--						<button type="submit" name="action"--%>
<%--							value="updateId_<%=s.getBook().getMasach()%>"--%>
<%--							class="btn btn-primary">Cập nhật</button>--%>
<%--					</div>--%>
<%--					<div>--%>
<%--						<button type="submit" name="action"--%>
<%--							value="delete_<%=s.getBook().getMasach()%>"--%>
<%--							class="btn btn-danger">Xóa</button>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<%--%>
<%--				}--%>
<%--				} else {--%>
<%--				out.println("<p>Không có sách nào trong giỏ hàng.</p>");--%>
<%--				}--%>
<%--				%>--%>
<%--			</div>--%>
<%--			<hr>--%>
<%--			<div>--%>
<%--				<h3>--%>
<%--					Tổng tiền:--%>
<%--					<%=String.format("%.0f", cbo.getTotalAmount())%></h3>--%>
<%--			</div>--%>
<%--			<div class="d-flex">--%>
<%--				<button type="submit" name="action" value="clearAll"--%>
<%--					class="btn btn-danger" style="margin-right: 10px;">Xóa Tất--%>
<%--					Cả</button>--%>

<%--				<button type="submit" name="action" value="deleteSelected" id="deleteSelectedButton"--%>
<%--					class="btn btn-warning hidden">Xóa Sách Chọn</button>--%>
<%--			</div>--%>
<%--			<div class="d-flex mt-2">--%>
<%--				<form action="xacnhanController" method="post">--%>
<%--		    		<input type="submit" class="btn btn-primary" name="btnxacnhan" value="Xác nhận đặt mua">--%>
<%--				</form>--%>
<%--			</div>--%>
<%--		</form>--%>
<%--	</div>--%>
<%--</body>--%>
<%--<script>--%>
<%--	function toggleDeleteButton() {--%>
<%--	    const checkboxes = document.querySelectorAll('input[name="selectedBooks"]');--%>
<%--	    const deleteButton = document.getElementById('deleteSelectedButton');--%>
<%--	    const Checked = Array.from(checkboxes).some(checkbox => checkbox.checked);--%>
<%--	    --%>
<%--	    if (Checked) {--%>
<%--	        deleteButton.classList.remove("hidden");--%>
<%--	    } else {--%>
<%--	        deleteButton.classList.add("hidden");--%>
<%--	    }--%>
<%--	}--%>
<%--	</script>--%>
<%--</html>--%>
