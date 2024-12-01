package com.nhatnguyen.demoolop.model.cartModal;

import com.nhatnguyen.demoolop.model.sachModal.sach;

import java.util.ArrayList;



public class cartbo {
    private ArrayList<cart> cartItems; 

	public cartbo() {
        this.cartItems = new ArrayList<>(); 
    }
    public ArrayList<cart> getCartItems() {
        return cartItems;
    }
    public void addItem(sach book, int quantity) {
        for (cart item : cartItems) {
            if (item.getBook().getMasach().equals(book.getMasach())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
     // Nếu không có sản phẩm, thêm mới
        cartItems.add(new cart(book, quantity)); 
    }

    public void removeItem(String masach) {
    	cartItems.removeIf(item -> item.getBook().getMasach().equals(masach.trim()));
    }

    public void updateQuantity(String masach, int quantity) {
        for (cart item : cartItems) {
            if (item.getBook().getMasach().equals(masach)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    public double getTotalAmount() {
        double total = 0;
        for (cart item : cartItems) {
            total += item.getBook().getGia() * item.getQuantity();
        }
        return total;
    }

   
}
