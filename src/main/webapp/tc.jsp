<%@ page import="com.nhatnguyen.demoolop.model.khachhangModal.khachhang" %>
<%@ page import="com.nhatnguyen.demoolop.model.sachModal.sachbo" %>
<%@ page import="com.nhatnguyen.demoolop.model.sachModal.sach" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html lang="en">
<%--<head>--%>
<title>Nha sach Minh Khai</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	.listSach {
		display: grid;
		grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
		gap: 25px;
	}
	.card img {
		height: 190px;
	}
	.navbar-custom {
		background-color: #ccd1e3;
	}
	.listSach .card {
	  transition: transform 0.2s ease-in-out;
	  border: 1px solid rgba(0,0,0,0.125);
	}

	.listSach .card:hover {
	  transform: translateY(-5px);
	  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
	}

	.listSach .card-img-wrapper {
	  background: #f8f9fa;
	  border-bottom: 1px solid rgba(0,0,0,0.125);
	}

	.listSach .card-title {
	  font-size: 1rem;
	  line-height: 1.4;
	}

	.listSach .btn {
	  padding: 0.375rem 0.75rem;
	}


	.pagination {
	    display: flex;
	    justify-content: center;
	    margin: 20px 0;
	}

	.pagination a {
	    color: black;
	    padding: 8px 16px;
	    text-decoration: none;
	    border: 1px solid #ddd;
	    margin: 0 4px;
	}

	.pagination a.active {
	    background-color: #4CAF50;
	    color: white;
	    border: 1px solid #4CAF50;
	}

	.pagination a:hover:not(.active) {
	    background-color: #ddd;
	}
</style>
<%

%>
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
                <a class="nav-link" href="sachController">Home <span class="sr-only">(current)</span></a>
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
            <li class="nav-item">
                <a class="nav-link text-dark" href="dangky.jsp">
                    <span class="fa-solid fa-user-plus"></span> Sign Up
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

	<div class="px-4">
		<div class="row">
			<div class="col-sm-2">
				<a href="sachController">Tất cả</a>
				<hr />
				<%
					//ArrayList<loai> dsloai = (ArrayList<loai>) request.getAttribute("dsloai");
					//for (loai l : dsloai) {
				%>
				<c:forEach var="l" items="${dsloai}">
					<a href="sachController?ml=${l.getMaloai()}"> ${l.getTenloai()}</a>
					<hr />
				</c:forEach>

			</div>



			<div class="col-sm-8 listSach">
				  <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 g-4">
				    <%
				    sachbo sbo = new sachbo();
				    ArrayList<sach> ds = (ArrayList<sach>) request.getAttribute("dssach");

				    if (ds != null && !ds.isEmpty()) {
				    	int n = ds.size();
				      	for(int i = 0;i<n;i++) {
				      		sach s = ds.get(i);
				    %>
				    <c:forEach var="s" items="${dssach}">
				    <div class="col-sm-3 mb-4">
					      <div class="card h-100 d-flex flex-column">
					        <div class="card-img-wrapper" style="height: 250px; padding: 1rem;">
					          <img class="card-img-top h-100 w-100"
					               src="${s.getAnh()}"
					               alt="Card image cap"
					               style="object-fit: contain;">
					        </div>
					        <div class="card-body d-flex flex-column">
					          <h5 class="card-title text-truncate mb-1">${s.getTensach()}</h5>
					          <p class="card-text small text-muted mb-3">
					            Tác giả: ${s.getTacgia()}
					          </p>
					          <div class="row g-2 mt-auto">
					            <div class="col-5">
					              <form method="post" action="cartController">
					                <input type="hidden" name="id" value="${s.getMasach()}">
					                <input type="hidden" name="action" value="buy">
					                <button type="submit"
					                        class="btn btn-success w-100"
					                        style="font-size: 14px">Buy</button>
					              </form>
					            </div>
					            <div class="col-7 text-center">
					              <form method="post" action="cartController">
					                <input type="hidden" name="id" value="${s.getMasach()}">
					                <input type="hidden" name="action" value="addToCart">
					                <button type="submit"
					                        class="btn btn-primary w-100"
					                        style="font-size: 14px">Add to cart</button>
					              </form>
					            </div>
					          </div>
					        </div>
					      </div>
					    </div>
				    </c:forEach>



				    <%
				      }
				    } else {
				      out.println("<div class='col-12'><p class='text-center'>Không có sách nào.</p></div>");
				    }
				    %>
				  </div>


  			</div>
			<div class="col-sm-2">
				<%
					String ml = (String) request.getAttribute("ml"); //maLoai
					String key = (String) request.getAttribute("key");
				%>
				<form method="post" action="sachController" accept-charset="UTF-8">
					<input type="text" class="form-control" name="q"
						placeholder="Tìm kiếm sách..." value="<%=key != null ? key : ""%>">

					<div class="input-group-btn" style="margin-top: 20px;">
						<button type="submit" class="btn btn-primary custom-margin">Tìm kiếm</button>
					</div>

				</form>
			</div>
		</div>
	</div>

		<div class="container">
		    <div class="row">
		        <div class="col-sm-12 text-center">
		            <nav aria-label="Page navigation">
		                <ul class="pagination justify-content-center">
		                    <%
		                        int currentPage = (Integer) request.getAttribute("currentPage");
		                        int totalPages = (Integer) request.getAttribute("totalPages");

		                        if (currentPage > 1) {
		                    %>
		                        <li class="page-item">
		                            <a class="page-link" href="sachController?page=<%= currentPage - 1 %>" aria-label="Previous">
		                                <span aria-hidden="true">&laquo;</span>
		                            </a>
		                        </li>
		                    <%
		                        }

		                        for (int i = 1; i <= totalPages; i++) {
		                            if (i == currentPage) {
		                    %>
		                                <li class="page-item active"><a class="page-link" href="#"><%= i %></a></li>
		                    <%
		                            } else {
		                    %>
		                                <li class="page-item"><a class="page-link" href="sachController?page=<%= i %>"><%= i %></a></li>
		                    <%
		                            }
		                        }

		                        if (currentPage < totalPages) {
		                    %>
		                        <li class="page-item">
		                            <a class="page-link" href="sachController?page=<%= currentPage + 1 %>" aria-label="Next">
		                                <span aria-hidden="true">&raquo;</span>
		                            </a>
		                        </li>
		                    <%
		                        }
		                    %>
		                </ul>
		            </nav>
		        </div>
		    </div>
		</div>
</body>
</html>