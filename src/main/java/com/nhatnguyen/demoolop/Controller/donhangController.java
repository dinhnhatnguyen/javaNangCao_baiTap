package com.nhatnguyen.demoolop.Controller;

import com.nhatnguyen.demoolop.model.chitiethoadonModal.chitiethoadon;
import com.nhatnguyen.demoolop.model.chitiethoadonModal.chitiethoadonbo;
import com.nhatnguyen.demoolop.model.hoadonModal.hoadon;
import com.nhatnguyen.demoolop.model.hoadonModal.hoadonbo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "donhangController", value = "/donhangController")
public class donhangController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public donhangController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String action = request.getParameter("action");

//            if(action == null) {
//                listHoaDon(request, response);
//            }
//            else {
//                if(action.equals("search")) {
//                    searchHoaDon(request, response);
//                }
//                else if (action.equals("edit")) {
//                    chiTietHoaDon(request, response);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            String action = request.getParameter("action");
            String mahoadonStr = request.getParameter("mahoadon").replace("/", "");

            Long mahoadon = Long.parseLong(mahoadonStr);

            hoadonbo hdbo = new hoadonbo();
            if(action.equals("xacnhan")) {
                hoadon hd = hdbo.getHoaDon(mahoadon);
                if(hd!=null) {
                    hdbo.updateTrangThai(mahoadon);
                    response.sendRedirect("/adminController");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void listHoaDon(HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//
//        hoadonbo hdbo = new hoadonbo();
//        ArrayList<hoadon> ds = hdbo.getListHoaDon();
//        request.setAttribute("dshd", ds);
//        RequestDispatcher rd = request.getRequestDispatcher("donhang.jsp");
//        rd.forward(request, response);
//    }
//    private void searchHoaDon(HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        String key = request.getParameter("key");
//        hoadonbo hdbo = new hoadonbo();
//        ArrayList<hoadon> ds = null;
//        if(key!=null && !key.isEmpty()) {
//            ds = hdbo.Tim(key);
//        }
//        else {
//            ds = hdbo.getListHoaDon();
//        }
//
//        request.setAttribute("dshd", ds);
//        request.setAttribute("key", key);
//        RequestDispatcher rd = request.getRequestDispatcher("donhang.jsp");
//        rd.forward(request, response);
//
//    }
//
//
//    private void chiTietHoaDon(HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        String mahoadonStr = request.getParameter("mahoadon");
//        hoadonbo hdbo = new hoadonbo();
//        chitiethoadonbo ctbo = new chitiethoadonbo();
//        Long mahoadon = Long.parseLong(mahoadonStr);
//        boolean trangthai = hdbo.getHoaDon(mahoadon).isTrangthai();
//
//
//        ArrayList<chitiethoadon> ds = null;
//        if(mahoadon != null && mahoadon > 0) {
//            ds = ctbo.getListChiTietTheoMaHD(mahoadon);
//        }
//        request.setAttribute("dsct", ds);
//        request.setAttribute("trangthai", trangthai);
//        request.getRequestDispatcher("editTrangThaiHD.jsp").forward(request, response);
//    }
}