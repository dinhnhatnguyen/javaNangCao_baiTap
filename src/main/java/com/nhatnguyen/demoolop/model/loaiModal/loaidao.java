package com.nhatnguyen.demoolop.model.loaiModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class loaidao {
	public ArrayList<loai> getloai() throws Exception {
		ArrayList<loai> ds = new ArrayList<loai>();
		Connection conn = dbHelper.getConnection();
		String sql = "SELECT * FROM loai";
		PreparedStatement cmd = conn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			String maloai = rs.getString("maloai");
			String tenloai = rs.getString("tenloai");
			ds.add(new loai(maloai, tenloai));
			
		}
		rs.close();
		cmd.close();
		conn.close();
		
//		ds.add(new loai("cntt","Cong nghe thong tin"));
//		ds.add(new loai("toan","Toan ung dung"));
//		ds.add(new loai("ly","Cong nghe dien tu"));
//		ds.add(new loai("hoa","Cong nghe khoa hoc"));
//		ds.add(new loai("sinh","Sinh hoc ung dung"));
		return ds;
	}
}
