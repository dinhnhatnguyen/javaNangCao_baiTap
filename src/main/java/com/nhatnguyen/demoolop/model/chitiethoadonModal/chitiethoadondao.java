package com.nhatnguyen.demoolop.model.chitiethoadonModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;




public class chitiethoadondao {
	public int themChiTietHoaDon(String MaSach, long SoLuongMua, long MaHoaDon) throws Exception{
		Connection conn = dbHelper.getConnection();
		String sql = "insert into ChiTietHoaDon(MaSach, SoLuongMua, MaHoaDon)\r\n"
				+ "values (?,?,?)";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString(1, MaSach);
		cmd.setLong(2, SoLuongMua);
		cmd.setString(3, MaSach);
		
		int kq = cmd.executeUpdate();
		
		cmd.close();
		conn.close();
		return kq;
	}

}
