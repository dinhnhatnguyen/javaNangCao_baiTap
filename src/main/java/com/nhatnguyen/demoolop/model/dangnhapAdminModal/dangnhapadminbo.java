package com.nhatnguyen.demoolop.model.dangnhapAdminModal;

public class dangnhapadminbo {
	dangnhapadmindao dndao = new dangnhapadmindao();

	public String ktDangNhap(String tendn, String pass) throws Exception {
		return dndao.ktDangNhap(tendn, pass);
	}
}
