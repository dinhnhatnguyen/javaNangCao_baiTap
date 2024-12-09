package com.nhatnguyen.demoolop.Controller;

import com.nhatnguyen.demoolop.model.cartModal.cart;
import com.nhatnguyen.demoolop.model.cartModal.cartbo;
import com.nhatnguyen.demoolop.model.chitiethoadonModal.chitiethoadonbo;
import com.nhatnguyen.demoolop.model.hoadonModal.hoadonbo;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhang;
import com.nhatnguyen.demoolop.model.sachModal.sachbo;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Kiểm tra đăng nhập
        if(session.getAttribute("login") == null) {
            response.sendRedirect("dangNhapController");
            return;
        }

        try {
            cartbo gh = (cartbo) session.getAttribute("cart");
            khachhang kh = (khachhang) session.getAttribute("login");

            // Kiểm tra giỏ hàng có sản phẩm không
            if (gh == null || gh.getCartItems().isEmpty()) {
                request.setAttribute("error", "Giỏ hàng trống");
                request.getRequestDispatcher("cartController").forward(request, response);
                return;
            }

            // Kiểm tra số lượng sách còn đủ không
            sachbo sachBo = new sachbo();
            for (cart item : gh.getCartItems()) {
                if (!sachBo.checkQuantityAvailable(item.getBook().getMasach(), item.getQuantity())) {
                    request.setAttribute("error", "Sách " + item.getBook().getTensach() + " không đủ số lượng");
                    request.getRequestDispatcher("cartController").forward(request, response);
                    return;
                }
            }

            // Tạo hóa đơn
            hoadonbo hdbo = new hoadonbo();
            Date ngaymua = new Date();
            long makh = kh.getMakh();
            long maHoaDon = hdbo.themHoaDon(makh, ngaymua, false);

            if (maHoaDon <= 0) {
                throw new Exception("Không thể tạo hóa đơn mới");
            }

            // Thêm chi tiết hóa đơn
            chitiethoadonbo cthdbo = new chitiethoadonbo();
            for (cart s : gh.getCartItems()) {
                int result = cthdbo.themChiTietHoaDon(s.getBook().getMasach(), s.getQuantity(), maHoaDon);
                if (result <= 0) {
                    throw new Exception("Không thể thêm chi tiết hóa đơn");
                }

                // Trừ số lượng sách trong kho
                sachBo.updateQuantity(s.getBook().getMasach(), s.getQuantity());
            }

            // Xóa giỏ hàng sau khi đặt hàng thành công
            session.removeAttribute("cart");

            // Chuyển hướng đến trang lịch sử đơn hàng
            response.sendRedirect("lichsuController");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("cartController").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


