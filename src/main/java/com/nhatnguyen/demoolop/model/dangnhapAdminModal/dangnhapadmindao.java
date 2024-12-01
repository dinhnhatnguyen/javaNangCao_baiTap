package com.nhatnguyen.demoolop.model.dangnhapAdminModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class dangnhapadmindao {
	public String ktDangNhap(String tendn, String pass) throws Exception {
		Connection conn = dbHelper.getConnection();
		
		String sql = "SELECT * FROM DangNhap WHERE TenDangNhap = ? AND MatKhau = ?";
		PreparedStatement cmd = conn.prepareStatement(sql);
		
		cmd.setString(1, tendn);
	    cmd.setString(2, pass);
		
		ResultSet rs = cmd.executeQuery();
		String kq = null;
		
		if(rs.next()) {
			kq = tendn;

		}
		rs.close();
		cmd.close();
		conn.close();
		return kq;
	}
}
