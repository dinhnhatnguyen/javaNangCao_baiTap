package com.nhatnguyen.demoolop.Controller;
import com.nhatnguyen.demoolop.model.cartModal.cart;
import com.nhatnguyen.demoolop.model.cartModal.cartbo;
import com.nhatnguyen.demoolop.model.chitiethoadonModal.chitiethoadonbo;
import com.nhatnguyen.demoolop.model.hoadonModal.hoadonbo;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhang;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "xacnhanController", value = "/xacnhanController")
public class xacnhanController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public xacnhanController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //neu chua dang nhap
        if(session.getAttribute("login")==null) {
            response.sendRedirect("dangNhapController");
            return;
        }
        else {
            try {
                cartbo gh = (cartbo) session.getAttribute("cart");
                khachhang kh = (khachhang) session.getAttribute("login");

                //them vao 1 hoa don
                hoadonbo hdbo = new hoadonbo();
                Date ngaymua = new Date();
                long makh = kh.getMakh();
                try {
                    hdbo.themHoaDon(makh,ngaymua, false);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //them vao chi tiet hoa don
                chitiethoadonbo cthdbo = new chitiethoadonbo();
                for(cart s : gh.getCartItems()) {
                    try {
                        cthdbo.themChiTietHoaDon(s.getBook().getMasach(), s.getQuantity(), hdbo.getMaxHoaDon());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                //xoa gio hang sau khi dat hang thanh cong
                session.removeAttribute("cart");
                response.sendRedirect("lichsuController");

                // Chuyển đến trang xác nhận đơn hàng
                RequestDispatcher rd = request.getRequestDispatcher("xacNhanDonHang.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
            }



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