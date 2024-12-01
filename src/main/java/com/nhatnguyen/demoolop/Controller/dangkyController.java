package com.nhatnguyen.demoolop.Controller;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhang;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhangbo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "dangkyController", value = "/dangkyController")
public class dangkyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangkyController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            String chuoiHoTen = request.getParameter("hoten");
            String chuoiEmail = request.getParameter("email");
            String chuoiSoDienThoai  = request.getParameter("sodt");
            String chuoiDiaChi = request.getParameter("diachi");
            String chuoiTenDangNhap = request.getParameter("tendn");
            String chuoiMatKhau = request.getParameter("matkhau");
            String error = null;

            if(chuoiHoTen != null && chuoiEmail != null && chuoiSoDienThoai != null && chuoiDiaChi != null && chuoiTenDangNhap != null && chuoiMatKhau != null) {
                khachhangbo khbo = new khachhangbo();

                if(khbo.checkEmail(chuoiEmail)) {
                    request.setAttribute("error", "Email đã được sử dụng!");
                    RequestDispatcher rd = request.getRequestDispatcher("dangky.jsp");
                    rd.forward(request, response);
                }
                boolean rs = khbo.themKhachHang(new khachhang(0, chuoiHoTen, chuoiDiaChi, chuoiSoDienThoai, chuoiEmail, chuoiTenDangNhap, chuoiMatKhau));

                if(rs) {
                    response.sendRedirect("dangNhapController");
                    return;
                }
                else {
                    request.setAttribute("error", "Đăng ký không thành công. Vui lòng thử lại.");
                    RequestDispatcher rd = request.getRequestDispatcher("dangky.jsp");
                    rd.forward(request, response);
                }



            }
            else {
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("dangky.jsp");
                rd.forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra. Vui lòng thử lại.");
            RequestDispatcher rd = request.getRequestDispatcher("dangky.jsp");
            rd.forward(request, response);

        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}