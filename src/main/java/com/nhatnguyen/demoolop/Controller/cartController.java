package com.nhatnguyen.demoolop.Controller;
import com.nhatnguyen.demoolop.model.cartModal.cartbo;
import com.nhatnguyen.demoolop.model.sachModal.sach;
import com.nhatnguyen.demoolop.model.sachModal.sachbo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "cartController", value = "/cartController")
public class cartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        cartbo cart = (cartbo) session.getAttribute("cart");
        if (cart == null) {
            cart = new cartbo();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        String id = request.getParameter("id");


        if (id != null) {
            sach product = null;
            try {
                product = new sachbo().getProductById(id);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if ("buy".equals(action)) {
                cart.addItem(product, 1);
                response.sendRedirect("htgioController");
                return;
            } else if ("addToCart".equals(action)) {
                cart.addItem(product, 1);
                request.getRequestDispatcher("sachController").forward(request, response);
                return;
            }
        }

        if (action != null) {
            if (action.startsWith("delete_")) {
                String bookId = action.substring(action.indexOf("_") + 1);
                cart.removeItem(bookId);
            } else if (action.startsWith("updateId_")) {
                String bookId = action.substring(action.indexOf("_") + 1);
                String quantityParam = request.getParameter("quantity_" + bookId);
                if (quantityParam != null && !quantityParam.isEmpty()) {
                    cart.updateQuantity(bookId, Integer.parseInt(quantityParam));
                }
            } else if ("clearAll".equals(action)) {
                cart.getCartItems().clear();
            }
        }

        String[] selectedBooks = request.getParameterValues("selectedBooks");
        if (selectedBooks != null) {
            for (String bookId : selectedBooks) {
                cart.removeItem(bookId);
            }
        }

        session.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}