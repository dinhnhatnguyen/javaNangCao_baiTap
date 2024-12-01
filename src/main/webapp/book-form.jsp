<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Thêm Sách Mới</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h2>Thêm Sách Mới</h2>
  <form action="adminController?action=create" method="post" enctype="multipart/form-data">
    <div class="mb-3">
      <label for="title" class="form-label">Tên Sách</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>
    <div class="mb-3">
      <label for="author" class="form-label">Tác Giả</label>
      <input type="text" class="form-control" id="author" name="author" required>
    </div>
    <div class="mb-3">
      <label for="price" class="form-label">Giá</label>
      <input type="number" class="form-control" id="price" name="price" required>
    </div>
    <div class="mb-3">
      <label for="quantity" class="form-label">Số Lượng</label>
      <input type="number" class="form-control" id="quantity" name="quantity" required>
    </div>
    <div class="mb-3">
      <label for="category" class="form-label">Loại Sách</label>
      <input type="text" class="form-control" id="category" name="category" required>
    </div>
    <div class="mb-3">
      <label for="image" class="form-label">Ảnh</label>
      <input type="file" class="form-control" id="image" name="image" accept="image/*">
    </div>
    <button type="submit" class="btn btn-primary">Thêm Sách</button>
    <a href="adminController" class="btn btn-secondary">Hủy</a>
  </form>
</div>
</body>
</html>