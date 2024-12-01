package com.nhatnguyen.demoolop.model.hoadonModal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


import com.nhatnguyen.demoolop.model.Helper.dbHelper;



public class hoadondao {
	public int themHoaDon(long makh, Date NgayMua, boolean damua) throws Exception{
		Connection cn = dbHelper.getConnection();
		String sql = "insert into hoadon(makh, NgayMua, damua) values (?, ?, ?)";
		//b3: Thuc hien cau lenh
		PreparedStatement cmd = cn.prepareStatement(sql);
		cmd.setLong(1, makh);
		cmd.setDate(2, new java.sql.Date(NgayMua.getTime()));
		cmd.setBoolean(3, false);
		int kq = cmd.executeUpdate();
		cmd.close();
		cn.close();
		return kq;
	}
	
	public long getMaxHoaDon() throws Exception{
		Connection conn = dbHelper.getConnection();
		String sql = "select max(mahoadon) as MaxHD from hoadon";

		PreparedStatement cmd = conn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		long max = 0;
		if(rs.next()) {
			max = rs.getLong(1);
		}
		rs.close();
		cmd.close();
		conn.close();
		return max;
		
	}




}
