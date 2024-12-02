package com.nhatnguyen.demoolop.model.hoadonModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class hoadonbo {
    hoadondao hddao = new hoadondao();

    public int themHoaDon(long makh, Date NgayMua, boolean damua) throws Exception {
        return hddao.themHoaDon(makh, NgayMua, damua);
    }

    public long getMaxHoaDon() throws Exception {
        return hddao.getMaxHoaDon();
    }

    public ArrayList<hoadon> getHoaDon() throws Exception {
        ArrayList<hoadon> dsHoaDon = new ArrayList<>();
        Connection cn = dbHelper.getConnection();
        String sql = "select * from hoadon";
        PreparedStatement cmd = cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();

        while (rs.next()) {
            hoadon hd = new hoadon();
            hd.setMahoadon(rs.getLong("mahoadon"));
            hd.setMakh(rs.getLong("makh"));
            hd.setNgaymua(rs.getDate("NgayMua"));
            hd.setDamua(rs.getBoolean("damua"));
            dsHoaDon.add(hd);
        }

        rs.close();
        cmd.close();
        cn.close();
        return dsHoaDon;
    }

    public hoadon getOrderById(String orderId) throws Exception {
        Connection cn = dbHelper.getConnection();
        String sql = "select * from hoadon where mahoadon=?";
        PreparedStatement cmd = cn.prepareStatement(sql);
        cmd.setString(1, orderId);
        ResultSet rs = cmd.executeQuery();

        hoadon hd = null;
        if (rs.next()) {
            hd = new hoadon();
            hd.setMahoadon(rs.getLong("mahoadon"));
            hd.setMakh(rs.getLong("makh"));
            hd.setNgaymua(rs.getDate("NgayMua"));
            hd.setDamua(rs.getBoolean("damua"));
        }

        rs.close();
        cmd.close();
        cn.close();
        return hd;
    }

    //	public void toggleOrderStatus(long orderId) throws Exception {
//		Connection cn = dbHelper.getConnection();
//		String sql = "update hoadon set damua = false damua where mahoadon=?";
//		PreparedStatement cmd = cn.prepareStatement(sql);
//		cmd.setLong(1, orderId);
//		cmd.executeUpdate();
//		cmd.close();
//		cn.close();
//	}
    public void toggleOrderStatus(long orderId) throws Exception {
        Connection cn = dbHelper.getConnection();
        // Lấy trạng thái hiện tại
        String selectSql = "SELECT damua FROM hoadon WHERE MaHoaDon = ?";
        PreparedStatement selectCmd = cn.prepareStatement(selectSql);
        selectCmd.setLong(1, orderId);
        ResultSet rs = selectCmd.executeQuery();

        if (rs.next()) {
            boolean currentStatus = rs.getBoolean("damua");
            // Cập nhật trạng thái ngược lại
            String updateSql = "UPDATE hoadon SET damua = ? WHERE MaHoaDon = ?";
            PreparedStatement updateCmd = cn.prepareStatement(updateSql);
            updateCmd.setBoolean(1, !currentStatus);
            updateCmd.setLong(2, orderId);
            updateCmd.executeUpdate();
            updateCmd.close();
        }

        rs.close();
        selectCmd.close();
        cn.close();
    }
}
