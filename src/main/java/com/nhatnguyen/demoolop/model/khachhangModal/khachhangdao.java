package com.nhatnguyen.demoolop.model.khachhangModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class khachhangdao {
	public khachhang ktDangNhap(String tendn, String pass) throws Exception{
		Connection conn = dbHelper.getConnection();
		
		String sql = "SELECT TOP 1 * FROM KhachHang WHERE tendn = ? AND pass = ?";
		PreparedStatement cmd = conn.prepareStatement(sql);
		
		cmd.setString(1, tendn);
	    cmd.setString(2, pass);
		
		ResultSet rs = cmd.executeQuery();
		khachhang kh = null;
		
		if(rs.next()) {
			Long makh = rs.getLong("makh");
			String hoten = rs.getString("hoten");
			String diachi = rs.getString("diachi");   
			String sodt = rs.getString("sodt");
			String email = rs.getString("email");
			kh = new khachhang(makh, hoten, diachi, sodt, email, tendn, pass);

		}
		rs.close();
		cmd.close();
		conn.close();
		return kh;

	}
	
	//ktra email có tồn tại chưa
	public boolean checkEmail(String email) throws Exception {
		Connection conn = dbHelper.getConnection();
		
		String sql = "SELECT COUNT(*) FROM KhachHang WHERE email = ?";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString(1, email);
		
		
		ResultSet rs = cmd.executeQuery();
		boolean check = false;
		
		if(rs.next()) {
			check =  rs.getInt(1) > 0;
		}
		
		rs.close();
		cmd.close();
		conn.close();
		return check;
	}
	
	//them moi khach hang
	public boolean themKhachHang(khachhang kh) throws Exception {
		Connection conn = dbHelper.getConnection();
		
		String sql = "INSERT INTO KhachHang(hoten, diachi, sodt, email, tendn, pass) values (?,?,?,?,?,?)";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString(1, kh.getHoten());
		cmd.setString(2, kh.getDiachi());
		cmd.setString(3, kh.getSodt());
		cmd.setString(4, kh.getEmail());
		cmd.setString(5, kh.getTendn());
		cmd.setString(6, kh.getPass());
		
		
		boolean rs = cmd.executeUpdate() > 0;
		cmd.close();
		conn.close();
				
		return rs;
	}
}
