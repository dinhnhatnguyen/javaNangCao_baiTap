<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>
<body>
<div class="container mt-4">
    <h2>Quản lý Sách</h2>
    <div class="mb-3">
        <a href="adminController?action=create" class="btn btn-primary">Thêm Sách Mới</a>
    </div>

    <!-- Bảng quản lý sách -->
    <div class="table-responsive" style="max-height: 400px; overflow-y: auto; border: 1px solid #ddd; padding: 10px;">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Mã Sách</th>
                <th>Tên Sách</th>
                <th>Tác Giả</th>
                <th>Giá</th>
                <th>Số Lượng</th>
                <th>Loại</th>
                <th>Ảnh</th>
                <th>Thao Tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.masach}</td>
                    <td>${book.tensach}</td>
                    <td>${book.tacgia}</td>
                    <td>${book.gia}</td>
                    <td>${book.soluong}</td>
                    <td>${book.maloai}</td>
                    <td>
                        <img src="${book.anh}" alt="${book.tensach}" style="width: 50px; height: 50px;">
                    </td>
                    <td>
                        <a href="adminController?action=edit&id=${book.masach}" class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil"></i>
                        </a>
                        <a href="adminController?action=delete&id=${book.masach}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc muốn xóa sách này?')">
                            <i class="bi bi-trash"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <h2 class="mt-5">Quản lý Hóa Đơn</h2>
    <!-- Bảng quản lý hóa đơn -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Mã Hóa Đơn</th>
            <th>Mã Khách Hàng</th>
            <th>Ngày Mua</th>
            <th>Trạng Thái</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.mahoadon}</td>
                <td>${order.makh}</td>
                <td>${order.ngaymua}</td>
                <td>
                            <span class="badge ${order.damua ? 'bg-success' : 'bg-warning'}">
                                    ${order.damua ? 'Đã xử lý' : 'Chờ xử lý'}
                            </span>
                </td>
                <td>
                    <a href="adminController?action=toggleOrderStatus&id=${order.mahoadon}"
                       class="btn ${order.damua ? 'btn-warning' : 'btn-success'} btn-sm">
                            ${order.damua ? 'Hủy xử lý' : 'Xác nhận'}
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>