package com.nhatnguyen.demoolop.model.lichsuModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class lichsubo {
	lichsudao lsdao = new lichsudao();
	
	public ArrayList<lichsu> getLichSu(long makh, boolean damua) throws Exception {
		return lsdao.getLichSu(makh, damua);
	}

	public ArrayList<lichsu> getAllDonHang() throws Exception {
		return lsdao.getAllDonHang();
	}

	public void toggleOrderStatus(long mahoadon) throws Exception {
		Connection conn = dbHelper.getConnection();
		String sql = "UPDATE VLichSu SET damua = NOT damua WHERE mahoadon = ?";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setLong(1, mahoadon);
		cmd.executeUpdate();
		cmd.close();
		conn.close();
	}
}

