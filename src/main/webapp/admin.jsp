<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Sách và Đơn hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f5f5f5;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .status-pending {
            color: #ffc107;
            font-weight: bold;
        }

        .status-completed {
            color: #28a745;
            font-weight: bold;
        }

        img.book-thumbnail {
            max-width: 50px;
            max-height: 50px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Quản lý Sách và Đơn hàng</h1>

    <div class="table-container">
        <a href="adminController?action=create" class="btn btn-primary mb-3">Thêm Sách Mới</a>
        <div class="search-form mb-3">
            <form action="adminController" method="get" class="form-inline">
                <input type="hidden" name="action" value="searchBooks">
                <input type="text" name="searchKey" placeholder="Tìm kiếm sách..."
                       class="form-control mr-2" value="${searchKey != null ? searchKey : ''}">
                <button type="submit" class="btn btn-secondary">Tìm Sách</button>
            </form>
        </div>

        <h2>Danh sách Sách</h2>
        <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>Mã sách</th>
                    <th>Ảnh</th>
                    <th>Tên sách</th>
                    <th>Tác giả</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.masach}</td>
                        <td>
                            <img src="${book.anh}" alt="${book.tensach}" class="book-thumbnail">
                        </td>
                        <td>${book.tensach}</td>
                        <td>${book.tacgia}</td>
                        <td>${book.gia}</td>
                        <td>${book.soluong}</td>
                        <td class="actions">
                            <a href="adminController?action=edit&id=${book.masach}" class="btn btn-success btn-sm">Sửa</a>
                            <a href="adminController?action=delete&id=${book.masach}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Bạn có chắc muốn xóa sách này?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="table-container mt-5">
        <h2>Danh sách Đơn Hàng</h2>
        <div class="search-form mb-3">
            <form action="adminController" method="get" class="form-inline">
                <input type="hidden" name="action" value="searchOrders">
                <input type="text" name="orderSearchKey" placeholder="Tìm kiếm hoá đơn..."
                       class="form-control mr-2" value="${orderSearchKey != null ? orderSearchKey : ''}">
                <button type="submit" class="btn btn-secondary">Tìm Hoá Đơn</button>
            </form>
        </div>

        <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>Mã Đơn</th>
                    <th>Mã Khách Hàng</th>
                    <th>Ngày Mua</th>
                    <th>Trạng Thái</th>
                    <th>Thao Tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.mahoadon}</td>
                        <td>${order.makh}</td>
                        <td>${order.ngaymua}</td>
                        <td class="${order.damua ? 'status-completed' : 'status-pending'}">
                                ${order.damua ? 'Đã xử lý' : 'Chờ xử lý'}
                        </td>
                        <td class="actions">
                            <a href="adminController?action=toggleOrderStatus&id=${order.mahoadon}"
                               class="btn ${order.damua ? 'btn-danger' : 'btn-success'} btn-sm">
                                    ${order.damua ? 'Hủy xử lý' : 'Xác nhận'}
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>




