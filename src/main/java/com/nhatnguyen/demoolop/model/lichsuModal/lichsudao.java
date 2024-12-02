package com.nhatnguyen.demoolop.model.lichsuModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;



public class lichsudao {
	public ArrayList<lichsu> getLichSu(long makh, boolean damua) throws Exception {
		Connection conn = dbHelper.getConnection();
		ArrayList<lichsu> ls = new ArrayList<lichsu>();
		String sql = "select * from VLichSu where makh = ? and damua = ?";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setLong(1, makh);
		cmd.setBoolean(2, damua);
		
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			long mahoadon = rs.getLong("MaHoaDon");
			String hoten = rs.getString("hoten");
			String tensach = rs.getString("tensach");
			long SoLuongMua = rs.getLong("SoLuongMua");
			long gia = rs.getLong("gia");
			long ThanhTien = rs.getLong("ThanhTien");
			Date NgayMua = rs.getDate("NgayMua");
			
			ls.add(new lichsu(mahoadon,hoten, tensach, SoLuongMua, gia, ThanhTien, NgayMua, damua, makh));
		}
		
		rs.close();
		cmd.close();
		conn.close();
		return ls;
	}

	public ArrayList<lichsu> getAllDonHang() throws Exception {
		Connection conn = dbHelper.getConnection();
		ArrayList<lichsu> ls = new ArrayList<lichsu>();
		String sql = "select * from VLichSu ";
		PreparedStatement cmd = conn.prepareStatement(sql);

		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			long mahoadon = rs.getLong("MaHoaDon");
			String hoten = rs.getString("hoten");
			String tensach = rs.getString("tensach");
			long SoLuongMua = rs.getLong("SoLuongMua");
			long gia = rs.getLong("gia");
			long ThanhTien = rs.getLong("ThanhTien");
			Date NgayMua = rs.getDate("NgayMua");
			boolean damua = rs.getBoolean("damua");
			long makh = rs.getLong("makh");

			ls.add(new lichsu(mahoadon,hoten, tensach, SoLuongMua, gia, ThanhTien, NgayMua, damua, makh));
		}

		rs.close();
		cmd.close();
		conn.close();
		return ls;
	}

}
