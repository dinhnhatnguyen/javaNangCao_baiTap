package com.nhatnguyen.demoolop.Controller;

//import com.nhatnguyen.demoolop.model.hoadonModal.hoadon;
import com.nhatnguyen.demoolop.model.hoadonModal.hoadonbo;
import com.nhatnguyen.demoolop.model.sachModal.sach;
import com.nhatnguyen.demoolop.model.sachModal.sachbo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
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
//        orderBO = new hoadonbo();
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

    private void showBookManagement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        // Retrieve all books
        ArrayList<sach> books = bookBO.getsach();
        request.setAttribute("books", books);

        // Retrieve all orders
//        ArrayList<hoadon> orders = orderBO.getHoaDon();
//        request.setAttribute("orders", orders);

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
        // Get book details from form
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        long quantity = Long.parseLong(request.getParameter("quantity"));
        long price = Long.parseLong(request.getParameter("price"));
        String category = request.getParameter("category");

        // Handle file upload
        String fileName = uploadImage(request);

        // Create book object
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
//        orderBO.toggleOrderStatus(orderId);
        response.sendRedirect(request.getContextPath() + "/adminController?action=showManagement");
    }

    private void viewOrderDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String orderId = request.getParameter("id");
//        hoadon order = orderBO.getOrderById(orderId);
//        request.setAttribute("order", order);
        request.getRequestDispatcher("order-details.jsp").forward(request, response);
    }

//    private String uploadImage(HttpServletRequest request) throws ServletException, IOException {
//        Part filePart = request.getPart("image");
//
//        // If no image was uploaded, return null
//        if (filePart == null || filePart.getSize() == 0) {
//            return null;
//        }
//
//        String uploadPath = request.getServletContext().getRealPath("") +  File.separator + "image_sach";
//
//        // Create the directory if it doesn't exist
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//
//        // Generate a unique filename
//        String originalFileName = getFileName(filePart);
//        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
//
//        // Full path for saving the file
//        String filePath = uploadPath + File.separator + uniqueFileName;
//
//        // Write the file
//        filePart.write(filePath);
//
//        return uniqueFileName;
//    }
//
//    private String getFileName(Part part) {
//        for (String content : part.getHeader("content-disposition").split(";")) {
//            if (content.trim().startsWith("filename")) {
//                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }

    private String uploadImage(HttpServletRequest request) throws ServletException, IOException {
        Part filePart = request.getPart("image");

        // Kiểm tra xem có tệp được tải lên không
        if (filePart == null || filePart.getSize() == 0) {
            return null; // Không có tệp nào được tải lên
        }

        // Kiểm tra loại tệp hợp lệ
        String originalFileName = getFileName(filePart);
        if (originalFileName == null || !(originalFileName.endsWith(".jpg") || originalFileName.endsWith(".jpeg") || originalFileName.endsWith(".png"))) {
            throw new ServletException("Chỉ chấp nhận các tệp ảnh có định dạng JPG, JPEG, hoặc PNG.");
        }

        // Tạo đường dẫn đến thư mục lưu ảnh
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + "image_sach";

        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists() && !uploadDir.mkdir()) {
            throw new IOException("Không thể tạo thư mục lưu trữ ảnh.");
        }

        // Sinh tên tệp duy nhất để tránh trùng lặp
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Full path để lưu file
        String filePath = uploadPath + File.separator + uniqueFileName;

        // Ghi file lên server
        try {
            filePart.write(filePath);
        } catch (IOException e) {
            throw new IOException("Lỗi trong quá trình ghi file: " + e.getMessage(), e);
        }

        // Trả về tên file duy nhất để lưu trong DB
        return uniqueFileName;
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