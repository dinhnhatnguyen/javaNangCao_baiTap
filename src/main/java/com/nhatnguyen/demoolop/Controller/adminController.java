package com.nhatnguyen.demoolop.Controller;


import com.nhatnguyen.demoolop.model.hoadonModal.hoadon;
import com.nhatnguyen.demoolop.model.hoadonModal.hoadonbo;
import com.nhatnguyen.demoolop.model.sachModal.sach;
import com.nhatnguyen.demoolop.model.sachModal.sachbo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(name = "adminController", value = "/adminController")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,  // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class adminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private sachbo bookBO;
    private hoadonbo orderBO;

    public void init() throws ServletException {
        bookBO = new sachbo();
        orderBO = new hoadonbo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "showManagement";
            }

            switch (action) {
                case "showManagement":
                    showBookManagement(request, response);
                    break;
                case "create":
                    showCreateBookForm(request, response);
                    break;
                case "edit":
                    showEditBookForm(request, response);
                    break;
                case "delete":
                    deleteBook(request, response);
                    break;
                case "toggleOrderStatus":
                    toggleOrderStatus(request, response);
                    break;
                case "viewOrderDetails":
                    viewOrderDetails(request, response);
                    break;
                case "searchBooks":
                    searchBooks(request, response);
                    break;
                case "searchOrders":
                    searchOrders(request, response);
                    break;
                default:
                    showBookManagement(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    createBook(request, response);
                    break;
                case "update":
                    updateBook(request, response);
                    break;
                default:
                    showBookManagement(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    private void searchBooks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String searchKey = request.getParameter("searchKey");
        ArrayList<sach> searchResults;

        if (searchKey != null && !searchKey.trim().isEmpty()) {
            searchResults = bookBO.Tim(searchKey);
        } else {
            searchResults = bookBO.getsach();
        }

        ArrayList<hoadon> orders = orderBO.getHoaDon();

        request.setAttribute("books", searchResults);
        request.setAttribute("orders", orders);
        request.setAttribute("searchKey", searchKey);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void searchOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String searchKey = request.getParameter("orderSearchKey");
        ArrayList<hoadon> searchResults = new ArrayList<>();
        ArrayList<hoadon> allOrders = orderBO.getHoaDon();

        if (searchKey != null && !searchKey.trim().isEmpty()) {
            try {
                long searchId = Long.parseLong(searchKey);
                for (hoadon order : allOrders) {
                    if (order.getMahoadon() == searchId) {
                        searchResults.add(order);
                    }
                }
            } catch (NumberFormatException e) {
                try {
                    long customerId = Long.parseLong(searchKey);
                    for (hoadon order : allOrders) {
                        if (order.getMakh() == customerId) {
                            searchResults.add(order);
                        }
                    }
                } catch (NumberFormatException ex) {
                    searchResults = allOrders;
                }
            }
        } else {
            searchResults = allOrders;
        }

        ArrayList<sach> books = bookBO.getsach();

        request.setAttribute("books", books);
        request.setAttribute("orders", searchResults);
        request.setAttribute("orderSearchKey", searchKey);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }







    private void showBookManagement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        // Retrieve all books
        ArrayList<sach> books = bookBO.getsach();
        request.setAttribute("books", books);

        // Retrieve all orders
        ArrayList<hoadon> orders = orderBO.getHoaDon();
        request.setAttribute("orders", orders);

        // Forward to admin page
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void showCreateBookForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("book-form.jsp").forward(request, response);
    }

    private void showEditBookForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String bookId = request.getParameter("id");
        sach book = bookBO.getProductById(bookId);
        request.setAttribute("book", book);
        request.getRequestDispatcher("book-edit-form.jsp").forward(request, response);
    }

    private void createBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        long quantity = Long.parseLong(request.getParameter("quantity"));
        long price = Long.parseLong(request.getParameter("price"));
        String category = request.getParameter("category");


        String fileName = uploadImage(request);


        sach newBook = new sach(
                generateBookId(),
                title,
                author,
                price,
                quantity,
                fileName,
                category
        );

        int result = bookBO.themSach(newBook);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/adminController?action=showManagement");
        } else {
            request.setAttribute("errorMessage", "Thêm sách thất bại");
            request.getRequestDispatcher("book-form.jsp").forward(request, response);
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String bookId = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        long quantity = Long.parseLong(request.getParameter("quantity"));
        long price = Long.parseLong(request.getParameter("price"));
        String category = request.getParameter("category");

        // Handle file upload
        String fileName = uploadImage(request);

        // If no new image was uploaded, keep the existing image
        if (fileName == null || fileName.isEmpty()) {
            sach existingBook = bookBO.getProductById(bookId);
            fileName = existingBook.getAnh();
        }

        sach updatedBook = new sach(
                bookId,
                title,
                author,
                price,
                quantity,
                fileName,
                category
        );

        int result = bookBO.suaSach(updatedBook);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/adminController?action=showManagement");
        } else {
            request.setAttribute("errorMessage", "Cập nhật sách thất bại");
            request.setAttribute("book", updatedBook);
            request.getRequestDispatcher("book-edit-form.jsp").forward(request, response);
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String bookId = request.getParameter("id");

        // Get the book's image filename before deletion
        sach book = bookBO.getProductById(bookId);
        String imageFileName = book.getAnh();

        // Delete book from database
        int result = bookBO.xoaSach(bookId);

        if (result > 0 && imageFileName != null && !imageFileName.isEmpty()) {
            // Delete the associated image file
            String imagePath = getServletContext().getRealPath("") + File.separator + "image_sach" + File.separator + imageFileName;
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                imageFile.delete();
            }
        }

        response.sendRedirect(request.getContextPath() + "/adminController?action=showManagement");
    }

    private void toggleOrderStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        long orderId = Long.parseLong(request.getParameter("id"));
        orderBO.toggleOrderStatus(orderId);
        response.sendRedirect(request.getContextPath() + "/adminController?action=showManagement");
    }

    private void viewOrderDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String orderId = request.getParameter("id");
        hoadon order = orderBO.getOrderById(orderId);
        request.setAttribute("order", order);
        request.getRequestDispatcher("order-details.jsp").forward(request, response);
    }



    private String uploadImage(HttpServletRequest request) throws ServletException, IOException {
        // Tạo đường dẫn đến thư mục lưu ảnh
        String uploadDir = "image_sach"; // Thư mục con trong project
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + uploadDir;

        // Tạo thư mục nếu chưa tồn tại
        File directory = new File(uploadPath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Không thể tạo thư mục lưu trữ ảnh.");
        }

        // Lấy phần ảnh từ form
        Part filePart = request.getPart("image");

        // Kiểm tra xem có tệp nào được tải lên không
        if (filePart == null || filePart.getSize() == 0) {
            return null; // Không có tệp nào được tải lên
        }

        // Kiểm tra loại tệp hợp lệ
        String originalFileName = getFileName(filePart);
        if (originalFileName == null ||
                !(originalFileName.toLowerCase().endsWith(".jpg") ||
                        originalFileName.toLowerCase().endsWith(".jpeg") ||
                        originalFileName.toLowerCase().endsWith(".png"))) {
            throw new ServletException("Chỉ chấp nhận các tệp ảnh có định dạng JPG, JPEG, hoặc PNG.");
        }

        // Sinh tên tệp duy nhất để tránh trùng lặp
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString().substring(0, 4) + fileExtension;

        // Tạo đường dẫn đầy đủ cho file
        String filePath = uploadPath + File.separator + uniqueFileName;

        // Ghi file lên server
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Lỗi trong quá trình ghi file: " + e.getMessage(), e);
        }

        return uploadDir + "/" + uniqueFileName;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return null;
    }



    private String generateBookId() throws Exception {
        // Generate a timestamp-based ID with a prefix
        return "S" + System.currentTimeMillis();
    }
}