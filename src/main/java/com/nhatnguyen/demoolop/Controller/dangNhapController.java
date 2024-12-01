package com.nhatnguyen.demoolop.Controller;
import cn.apiclub.captcha.Captcha;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhang;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhangbo;
import com.nhatnguyen.demoolop.model.loaiModal.loai;
import com.nhatnguyen.demoolop.model.loaiModal.loaibo;
import com.nhatnguyen.demoolop.model.sachModal.sach;
import com.nhatnguyen.demoolop.model.sachModal.sachbo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "dangNhapController", value = "/dangNhapController")
public class dangNhapController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangNhapController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//        String chuoiTen = request.getParameter("username");
//        String chuoiMK = request.getParameter("password");
//        String error = null;
//
//        // Kiểm tra dữ liệu nhập
//        if (chuoiTen!=null && chuoiMK!=null) {
//            if (chuoiTen == null || chuoiTen.isEmpty()) {
//                error = "Tên đăng nhập không được để trống";
//            } else if (chuoiMK == null || chuoiMK.isEmpty()) {
//                error = "Mật khẩu không được để trống";
//            } else {
//                try {
//                    khachhangbo khbo = new khachhangbo();
//                    khachhang kh = khbo.ktDangNhap(chuoiTen, chuoiMK);
//                    if(kh!=null) {
//                        //Tạo session khi đăng nhập thành công
//                        HttpSession session = request.getSession();
//                        session.setAttribute("login", kh);
//
//                        loaibo lbo = new loaibo();
//                        ArrayList<loai> dsLoai = lbo.getloai();
//                        request.setAttribute("dsloai", dsLoai);
//
//                        sachbo sbo = new sachbo();
//                        ArrayList<sach> ds = sbo.getsach();
//                        request.setAttribute("dssach", ds);
//
//                        // Sử dụng RequestDispatcher để điều hướng nội bộ
//                        response.sendRedirect("sachController");
//                        return;
//                    } else {
//                        error = "Tên đăng nhập hoặc mật khẩu không đúng";
//                    }
//                } catch (Exception e) {
//                    // TODO: handle exception
//                    e.printStackTrace();
//
//                    //error = "Lỗi kết nối cơ sở dữ liệu";
//                }
//            }
//        }
//
//        // Trả về thông báo lỗi nếu có
//        request.setAttribute("error", error);
//        RequestDispatcher rd = request.getRequestDispatcher("dangnhap.jsp");
//        rd.forward(request, response);
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String chuoiTen = request.getParameter("username");
        String chuoiMK = request.getParameter("password");
        String error = null;

        // Captcha handling
        int dem = 0;
        if(session.getAttribute("demCustomer") != null)
            dem = (int) session.getAttribute("demCustomer");

        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
        String answer = request.getParameter("answer");

        // Kiểm tra dữ liệu nhập
        if (chuoiTen!=null && chuoiMK!=null) {
            if (chuoiTen == null || chuoiTen.isEmpty()) {
                error = "Tên đăng nhập không được để trống";
            } else if (chuoiMK == null || chuoiMK.isEmpty()) {
                error = "Mật khẩu không được để trống";
            } else {
                // Captcha validation for attempts >= 3
                if(dem >= 3) {
                    if(captcha == null || answer == null || !captcha.isCorrect(answer)) {
                        error = "Mã CAPTCHA không đúng";
                        request.setAttribute("error", error);
                        RequestDispatcher rd = request.getRequestDispatcher("dangnhap.jsp");
                        rd.forward(request, response);
                        return;
                    }
                }

                try {
                    khachhangbo khbo = new khachhangbo();
                    khachhang kh = khbo.ktDangNhap(chuoiTen, chuoiMK);

                    if(kh!=null) {
                        // Reset login attempts on successful login
                        session.removeAttribute("demCustomer");
                        session.setAttribute("login", kh);

                        loaibo lbo = new loaibo();
                        ArrayList<loai> dsLoai = lbo.getloai();
                        request.setAttribute("dsloai", dsLoai);

                        sachbo sbo = new sachbo();
                        ArrayList<sach> ds = sbo.getsach();
                        request.setAttribute("dssach", ds);

                        response.sendRedirect("sachController");
                        return;
                    } else {
                        error = "Tên đăng nhập hoặc mật khẩu không đúng";

                        // Increment login attempts
                        if(session.getAttribute("demCustomer") == null) {
                            session.setAttribute("demCustomer", 1);
                        } else {
                            int d = (int) session.getAttribute("demCustomer");
                            session.setAttribute("demCustomer", d + 1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Trả về thông báo lỗi nếu có
        request.setAttribute("error", error);
        RequestDispatcher rd = request.getRequestDispatcher("dangnhap.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}