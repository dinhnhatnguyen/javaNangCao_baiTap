<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.nhatnguyen.demoolop.model.chitiethoadonModal.chitiethoadon" %>

<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <title>Chi tiết đơn hàng</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">
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
  <style>
    :root {
      --primary-color: #2575fc;
      --secondary-color: #6a11cb;
    }

    .navbar-custom {
      background: linear-gradient(135deg, var(--secondary-color) 0%, var(--primary-color) 100%);
    }

    .admin-sidebar {
      background: #f8f9fa;
      min-height: calc(100vh - 56px);
      padding: 20px;
      border-right: 1px solid #dee2e6;
    }

    .admin-content {
      padding: 20px;
    }

    .stats-card {
      background: white;
      border-radius: 8px;
      padding: 20px;
      margin-bottom: 20px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .table-container {
      background: white;
      border-radius: 8px;
      padding: 20px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .nav-link {
      color: #333;
      padding: 8px 16px;
      margin: 4px 0;
      border-radius: 4px;
    }

    .nav-link:hover {
      background-color: #e9ecef;
      color: var(--primary-color);
    }

    .nav-link.active {
      background-color: var(--primary-color);
      color: white;
    }

    .table-hover tbody tr:hover {
      background-color: rgba(0, 0, 0, .075);
    }

    .action-buttons .btn {
      margin: 0 2px;
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Trang Quản Trị</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="/logoutAdmin">
            <i class="fas fa-sign-out-alt"></i> Đăng xuất
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container m-auto">
  <form action="donhangController" method="post">
    <h1>Chi tiết đơn hàng</h1>
    <hr>
    <div class="cart">
      <%
        ArrayList<chitiethoadon> ds = (ArrayList<chitiethoadon>) request.getAttribute("dsct");
        boolean trangthai = (boolean) request.getAttribute("trangthai");
        double tongtien = 0;
        if (ds != null && !ds.isEmpty()) {
          for (chitiethoadon ct : ds) {
            tongtien += ct.getGia() * ct.getSoluongmua();
      %>
      <div class="product w-100 d-flex align-items-center justify-content-between p-2">
        <input type="hidden" name="mahoadon" value=<%=ct.getMahoadon() %>/>
        <div>
          <img style="height: 100px" alt="" src="<%=ct.getAnh()%>">
        </div>
        <div>
          <h6><%=ct.getTensach()%></h6>
          <p>
            Giá:
            <%= ct.getGia() %> VND
          </p>
        </div>
        <div>
          <p>
            Số lượng mua:
            <%= ct.getSoluongmua() %>
          </p>
        </div>
      </div>
      <%
          }
        } else {
          out.println("<p>Không có sách nào trong đơn hàng.</p>");
        }
      %>
    </div>
    <hr>
    <div>
      <h3>
        Tổng tiền:
        <%=String.format("%.0f", tongtien)%> VND</h3>
    </div>
    <div class="d-flex">
      <button class="btn btn-default" style="margin-right: 10px;">
        <a href="/adminController" class="btn btn-default ml-2">
          Quay lại
        </a>
      </button>
      <% if(!trangthai) {
      %>
      <button type="submit" name="action" value="xacnhan"
              class="btn btn-primary" style="margin-right: 10px;">Xác nhận
      </button>
      <%} %>
    </div>

  </form>
</div>
</body>
</html>
