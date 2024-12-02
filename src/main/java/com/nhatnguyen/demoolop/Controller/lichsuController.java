package com.nhatnguyen.demoolop.Controller;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhang;
import com.nhatnguyen.demoolop.model.lichsuModal.lichsubo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "lichsuController", value = "/lichsuController")
public class lichsuController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public lichsuController() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();
            if(session.getAttribute("login")==null) {
                response.sendRedirect("dangNhapController");
                return;
            }
            else {
                khachhang kh = (khachhang) session.getAttribute("login");
                lichsubo lsbo = new lichsubo();
                request.setAttribute("dslichsutrue", lsbo.getLichSu(kh.getMakh(), true));
                request.setAttribute("dslichsufalse", lsbo.getLichSu(kh.getMakh(), false));

                RequestDispatcher rd = request.getRequestDispatcher("lichsu.jsp");
                rd.forward(request, response);


            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}