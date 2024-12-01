<%@page import="java.util.ArrayList"%>
<%@ page import="com.nhatnguyen.demoolop.model.khachhangModal.khachhang" %>
<%@ page import="com.nhatnguyen.demoolop.model.lichsuModal.lichsu" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	.navbar-custom {
		background-color: #ccd1e3;
	}
</style>
<body>
<nav class="navbar navbar-default navbar-expand-lg navbar-light navbar-custom mb-3">
    <button class="navbar-toggler" type="button" data-toggle="collapse"
        data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="sachController">Home <span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="cartController">Cart</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="xacnhanController">Xác nhận đặt mua</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="lichsuController">Lịch sử mua hàng</a>
            </li>
        </ul>

        <ul class="navbar-nav ms-auto ml-auto">
            <%
            if (session.getAttribute("login") == null) {
            %>
            <li class="nav-item">
                <a class="nav-link text-dark" href="dangNhapController">
                    <span class="fa fa-sign-in-alt"></span> Login
                </a>
            </li>
            <%
            } else {
            	khachhang kh = (khachhang) session.getAttribute("login");
            %>
            <li class="nav-item">
                <a class="nav-link text-dark" href="logoutController">
                    <span class="fa fa-sign-out-alt"></span> Log out
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span class="fa fa-user"></span> Xin Chào, <%=kh.getHoten()%>
                </a>
            </li>
            <%
            }
            %>
        </ul>
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
		      	for(int i = 0;i<n;i++) {
		      		lichsu ls = ds.get(i);
		 %>
	        <td><%=ls.getHoten()%></td>
	        <td><%=ls.getTensach() %></td>
	        <td><%=ls.getSoLuongMua() %></td>
	        <td><%=ls.getGia() %></td>
	        <td><%=ls.getThanhTien() %></td>
	        <td><%=ls.isDamua() %></td>
	        <td><%=ls.getNgayMua() %></td>
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
		      	for(int i = 0;i<n;i++) {
		      		lichsu ls = ds2.get(i);
		 %>
	        <td><%=ls.getHoten()%></td>
	        <td><%=ls.getTensach() %></td>
	        <td><%=ls.getSoLuongMua() %></td>
	        <td><%=ls.getGia() %></td>
	        <td><%=ls.getThanhTien() %></td>
	        <td><%=ls.isDamua() %></td>
	        <td><%=ls.getNgayMua() %></td>
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