<%@ page import="com.nhatnguyen.demoolop.model.sachModal.sach" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Trang Quản Trị</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
<!-- Navbar -->
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

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3 col-lg-2 admin-sidebar">
            <div class="d-flex flex-column">
                <h5 class="mb-3">Menu Quản Lý</h5>
                <a href="adminController" class="nav-link active">
                    <i class="fas fa-book"></i> Quản lý sách
                </a>
                <a href="adminloaiController" class="nav-link">
                    <i class="fas fa-list"></i> Quản lý loại
                </a>
                <a href="adminxacnhanController" class="nav-link">
                    <i class="fas fa-check-circle"></i> Xác nhận đơn hàng
                </a>
            </div>
        </div>

        <!-- Main Content -->
        <div class="col-md-9 col-lg-10 admin-content">
            <div class="row mb-4">
                <div class="col-md-4">
                    <div class="stats-card">
                        <h5>Tổng số sách</h5>
                        <h3>${books.size()} cuốn</h3>
                    </div>
                </div>
            </div>
            <div class="table-container">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4>Danh Sách Sách</h4>
                    <a href="adminController?action=create" class="btn btn-primary "> <i class="fas fa-plus"></i> Thêm
                        Sách Mới</a>
                    <div class="search-form mb-3 ">
                        <div class="search-form d-flex">
                            <form action="adminController" method="get" class="d-flex">
                                <input type="hidden" name="action" value="searchBooks">
                                <input type="text" name="searchKey" placeholder="Tìm kiếm sách..."
                                       class="form-control mr-2" value="${searchKey != null ? searchKey : ''}">
                                <button type="submit" class="btn btn-secondary">Tìm Sách</button>
                            </form>
                            <%--                            <form action="adminController" method="get" class="mt-4 mb-4 d-flex">--%>
                            <%--                                <input type="text" class="form-control" name="q"--%>
                            <%--                                       placeholder="Tìm kiếm sách..."--%>
                            <%--                                       value="${searchKey != null ? searchKey : ''}">--%>
                            <%--                                <button type="submit" class="btn btn-primary custom-margin"><i--%>
                            <%--                                        class="fas fa-search"></i>Tìm kiếm--%>
                            <%--                                </button>--%>
                            <%--                            </form>--%>
                        </div>
                    </div>
                </div>

                <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
                    <table class="table table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>Mã sách</th>
                            <th>Tên sách</th>
                            <th>Tác giả</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Ảnh</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="book" items="${books}">
                            <tr>
                                <td>${book.masach}</td>
                                <td>
                                    <img src="${book.anh}" alt="${book.tensach}"
                                         style="width: 50px; height: 50px; object-fit: cover;">
                                </td>
                                <td>${book.tensach}</td>
                                <td>${book.tacgia}</td>
                                <td>${book.gia}</td>
                                <td>${book.soluong}</td>
                                <td class="action-buttons">
                                    <a href="adminController?action=edit&id=${book.masach}"
                                       class="btn btn-warning btn-sm"><i class="fas fa-edit"></i> Sửa</a>
                                    <a href="adminController?action=delete&id=${book.masach}"
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('Bạn có chắc muốn xóa sách này?')"><i
                                            class="fas fa-trash"></i> Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination -->
                <c:if test="${endPage > 1}">
                    <nav aria-label="Page navigation" class="mt-4">
                        <ul class="pagination justify-content-center">
                            <c:forEach begin="1" end="${endPage}" var="i">
                                <li class="page-item ${tag == i ? 'active' : ''}">
                                    <a class="page-link" href="adminController?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </c:if>
            </div>


            <%--order --%>
            <div class="table-container mt-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h2>Danh sách Đơn Hàng</h2>

                    <div class="search-form mb-3 ">
                        <div class="search-form d-flex">
                            <form action="adminController" method="get" class="mt-4 mb-4 d-flex">
                                <input type="hidden" name="action" value="searchOrders">
                                <input type="text" name="orderSearchKey" placeholder="Tìm kiếm hoá đơn..."
                                       class="form-control mr-2"
                                       value="${orderSearchKey != null ? orderSearchKey : ''}">
                                <button type="submit" class="btn btn-primary">Tìm Hoá Đơn</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
                    <table class="table table-bordered">
                        <thead class="thead-light">
                        <tr>
                            <%--                            <th>Mã Đơn</th>--%>
                            <%--                            <th>Mã Khách Hàng</th>--%>
                            <%--                            <th>Ngày Mua</th>--%>
                            <%--                            <th>Trạng Thái</th>--%>
                            <%--                            <th>Thao Tác</th>--%>
                            <th>Họ tên người</th>
                            <th>Tên sách</th>
                            <th>Số lượng mua</th>
                            <th>Giá</th>
                            <th>Thành tiền</th>
                            <th>Đã mua</th>
                            <th>Ngày mua</th>
                            <th>Thao tác</th>


                        </tr>
                        </thead>
<%--                        <tbody>--%>
<%--                        <c:forEach var="order" items="${order}">--%>
<%--                            <tr>--%>
<%--                                    &lt;%&ndash;                                <td>${order.mahoadon}</td>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                <td>${order.makh}</td>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                <td>${order.ngaymua}</td>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                <td class="${order.damua ? 'status-completed' : 'status-pending'}">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        ${order.damua ? 'Đã xử lý' : 'Chờ xử lý'}&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                </td>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                <td class="actions">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    <a href="adminController?action=toggleOrderStatus&id=${order.mahoadon}"&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                       class="btn ${order.damua ? 'btn-danger' : 'btn-success'} btn-sm">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                            ${order.damua ? 'Hủy xử lý' : 'Xác nhận'}&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    </a>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                </td>&ndash;%&gt;--%>
<%--                                <td>${order.getHoten()}--%>
<%--                                </td>--%>
<%--                                <td>${order.getTensach()} %>--%>
<%--                                </td>--%>
<%--                                <td>${order.getSoLuongMua()} %>--%>
<%--                                </td>--%>
<%--                                <td>${order.getGia()} %>--%>
<%--                                </td>--%>
<%--                                <td>${order.getThanhTien()} %>--%>
<%--                                </td>--%>
<%--                                <td>${order.isDamua()} %>--%>
<%--                                </td>--%>
<%--                                <td>${order.getNgayMua()} %>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>

                        <tbody>
<%--                        <c:forEach var="order" items="${orders}">--%>
<%--                            <tr>--%>
<%--                                <td>${order.hoten}</td>--%>
<%--                                <td>${order.tensach}</td>--%>
<%--                                <td>${order.soLuongMua}</td>--%>
<%--                                <td>${order.gia}</td>--%>
<%--                                <td>${order.thanhTien}</td>--%>
<%--                                <td>${order.damua ? 'Đã mua' : 'Chưa mua'}</td>--%>
<%--                                <td>${order.ngayMua}</td>--%>
<%--&lt;%&ndash;                        --%>
<%--&lt;%&ndash;                                <td class="actions">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <a href="adminController?action=toggleOrderStatus&id=${order.mahoadon}"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                       class="btn ${order.damua ? 'btn-danger' : 'btn-success'} btn-sm">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            ${order.damua ? 'Hủy xử lý' : 'Xác nhận'}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    </a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </td>&ndash;%&gt;--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>

                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.hoten}</td>
                                <td>${order.tensach}</td>
                                <td>${order.soLuongMua}</td>
                                <td>${order.gia}</td>
                                <td>${order.thanhTien}</td>
                                <td>${order.damua ? 'Đã mua' : 'Chưa mua'}</td>
                                <td>${order.ngayMua}</td>
                                <td class="actions">
                                    <a href="adminController?action=toggleOrderStatus&id=${order.mahoadon}"
                                       class="btn ${order.damua ? 'btn-danger' : 'btn-success'} btn-sm">
                                            ${order.damua ? 'Hủy xử lý' : 'Xác nhận'}
                                    </a>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>

                        </tbody>

                        </tbody>
                    </table>
                </div>


            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>





