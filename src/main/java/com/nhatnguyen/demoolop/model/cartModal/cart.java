package com.nhatnguyen.demoolop.model.cartModal;


import com.nhatnguyen.demoolop.model.sachModal.sach;

public class cart {
    private sach book;
    private int quantity; 

    public cart(sach book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    // Constructor không tham số
    public cart() {
        // Khởi tạo mặc định
    }

    // Getter và Setter
    public sach getBook() {
        return book;
    }

    public void setBook(sach book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
