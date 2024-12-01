<%--
  Created by IntelliJ IDEA.
  User: nhatnguyendinh
  Date: 1/12/24
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin - Order Management</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    .order-status {
      font-weight: bold;
    }
  </style>
</head>
<body>
<div class="container my-5">
  <h1 class="mb-4">Order Management</h1>
  <a href="orderController?action=showOrders" class="btn btn-primary mb-3">Manage Orders</a>

  <table class="table table-striped table-hover">
    <thead class="thead-dark">
    <tr>
      <th>ID</th>
      <th>Customer</th>
      <th>Date</th>
      <th>Delivered</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
      <tr>
        <td>${order.id}</td>
        <td>${order.customer.name}</td>
        <td>${order.date}</td>
        <td class="order-status">
          <c:choose>
            <c:when test="${order.delivered}">Yes</c:when>
            <c:otherwise>No</c:otherwise>
          </c:choose>
        </td>
        <td>
          <a href="orderController?action=updateStatus&orderId=${order.id}&delivered=${!order.delivered}" class="btn btn-primary btn-sm">
            <c:choose>
              <c:when test="${order.delivered}">Mark as Undelivered</c:when>
              <c:otherwise>Mark as Delivered</c:otherwise>
            </c:choose>
          </a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>