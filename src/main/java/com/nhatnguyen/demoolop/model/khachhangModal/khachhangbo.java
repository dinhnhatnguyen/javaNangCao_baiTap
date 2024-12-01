package com.nhatnguyen.demoolop.model.khachhangModal;

public class khachhangbo {
	khachhangdao khdao = new khachhangdao();
	public khachhang ktDangNhap(String tendn, String pass) throws Exception {
		return khdao.ktDangNhap(tendn, pass);
	}
	public boolean checkEmail(String email) throws Exception {
		return khdao.checkEmail(email);
	}
	public boolean themKhachHang(khachhang kh) throws Exception{
		return khdao.themKhachHang(kh);
	}
}
