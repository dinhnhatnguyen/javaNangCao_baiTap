package com.nhatnguyen.demoolop.model.khachhangModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;
import com.nhatnguyen.demoolop.model.khachhangModal.khachhang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;


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
		String checkSql = "SELECT COUNT(*) FROM KhachHang WHERE makh = ?";
		String insertSql = "INSERT INTO KhachHang(makh, hoten, diachi, sodt, email, tendn, pass) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Random random = new Random();
		int makh;

		// Sinh mã khách hàng và đảm bảo không trùng
		while (true) {
			makh = random.nextInt(1000000); // Sinh số ngẫu nhiên từ 0 đến 999999
			PreparedStatement checkCmd = conn.prepareStatement(checkSql);
			checkCmd.setInt(1, makh);
			ResultSet rs = checkCmd.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) { // Nếu không trùng
				rs.close();
				checkCmd.close();
				break;
			}
			rs.close();
			checkCmd.close();
		}

		// Thêm khách hàng vào cơ sở dữ liệu
		PreparedStatement insertCmd = conn.prepareStatement(insertSql);
		insertCmd.setInt(1, makh);
		insertCmd.setString(2, kh.getHoten());
		insertCmd.setString(3, kh.getDiachi());
		insertCmd.setString(4, kh.getSodt());
		insertCmd.setString(5, kh.getEmail());
		insertCmd.setString(6, kh.getTendn());
		insertCmd.setString(7, kh.getPass());

		boolean result = insertCmd.executeUpdate() > 0;
		insertCmd.close();
		conn.close();

		return result;
	}

//	public boolean themKhachHang(khachhang kh) throws Exception {
//		Connection conn = dbHelper.getConnection();
//
//		String sql = "INSERT INTO KhachHang(hoten, diachi, sodt, email, tendn, pass) values (?,?,?,?,?,?)";
//		PreparedStatement cmd = conn.prepareStatement(sql);
//		cmd.setString(1, kh.getHoten());
//		cmd.setString(2, kh.getDiachi());
//		cmd.setString(3, kh.getSodt());
//		cmd.setString(4, kh.getEmail());
//		cmd.setString(5, kh.getTendn());
//		cmd.setString(6, kh.getPass());
//
//
//		boolean rs = cmd.executeUpdate() > 0;
//		cmd.close();
//		conn.close();
//
//		return rs;
//	}
}
