package com.nhatnguyen.demoolop.Controller;
import com.nhatnguyen.demoolop.model.loaiModal.loaibo;
import com.nhatnguyen.demoolop.model.sachModal.sach;
import com.nhatnguyen.demoolop.model.sachModal.sachbo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "sachController", value = "/sachController")
public class sachController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public sachController() {
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

            loaibo lbo = new loaibo();
            request.setAttribute("dsloai", lbo.getloai());
            sachbo sbo = new sachbo();

            String ml = request.getParameter("ml");
            String key = request.getParameter("q");
            ArrayList<sach> ds = null;
            ds = sbo.getsach();
            if(ml != null) ds = sbo.TimMa(ml);
            else
            if(key != null) ds = sbo.Tim(key);

            int pageSize = 8;
            int totalBooks = ds.size();
            int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

            int page = 1; // Mặc định là trang 1
            String pageParam = request.getParameter("page");
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
            }
            int offset = (page - 1) * pageSize;

            // Lấy danh sách cuốn sách cho trang hiện tại
            ArrayList<sach> paginatedList = new ArrayList<>();
            for (int i = offset; i < Math.min(offset + pageSize, totalBooks); i++) {
                paginatedList.add(ds.get(i));
            }

            request.setAttribute("dssach", paginatedList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);


            request.setAttribute("ml", ml);
            request.setAttribute("key", key);
            RequestDispatcher rd = request.getRequestDispatcher("tc.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}