package com.nhatnguyen.demoolop.Controller;
import cn.apiclub.captcha.Captcha;
import com.nhatnguyen.demoolop.model.dangnhapAdminModal.dangnhapadminbo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "dangnhapAdminController", value = "/dangnhapAdminController")
public class dangnhapAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public dangnhapAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String chuoiTen = request.getParameter("username");
        String chuoiMK = request.getParameter("password");
        String error = null;
        HttpSession session = request.getSession();

        // Captcha handling
        int dem = 0;
        if(session.getAttribute("dem") != null)
            dem = (int) session.getAttribute("dem");

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
                        RequestDispatcher rd = request.getRequestDispatcher("dangnhapAdmin.jsp");
                        rd.forward(request, response);
                        return;
                    }
                }

                try {
                    dangnhapadminbo adminbo = new dangnhapadminbo();
                    String tendn = adminbo.ktDangNhap(chuoiTen, chuoiMK);

                    if(tendn != null) {
                        // Reset login attempts on successful login
                        session.removeAttribute("dem");
                        session.setAttribute("loginadmin", tendn);
//                        response.sendRedirect("adminController");
                        response.sendRedirect("sachController");
                        return;
                    } else {
                        error = "Tên đăng nhập hoặc mật khẩu không đúng";
                        request.setAttribute("error", error);

                        // Increment login attempts
                        if(session.getAttribute("dem") == null) {
                            session.setAttribute("dem", 1);
                        } else {
                            int d = (int) session.getAttribute("dem");
                            session.setAttribute("dem", d + 1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("dangnhapAdmin.jsp");
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