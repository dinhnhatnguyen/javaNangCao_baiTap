//package com.nhatnguyen.demoolop.model.hoadonModal;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Date;
//
//
//import com.nhatnguyen.demoolop.model.Helper.dbHelper;
//
//
//public class hoadondao {
//    //	public int themHoaDon(long makh, Date NgayMua, boolean damua) throws Exception{
////		Connection cn = dbHelper.getConnection();
////		String sql = "insert into hoadon(makh, NgayMua, damua) values (?, ?, ?)";
////		//b3: Thuc hien cau lenh
////		PreparedStatement cmd = cn.prepareStatement(sql);
////		cmd.setLong(1, makh);
////		cmd.setDate(2, new java.sql.Date(NgayMua.getTime()));
////		cmd.setBoolean(3, false);
////		int kq = cmd.executeUpdate();
////		cmd.close();
////		cn.close();
////		return kq;
////	}
//    public long themHoaDon(long makh, Date NgayMua, boolean damua) throws Exception {
//        Connection cn = dbHelper.getConnection();
//        String sql = "insert into hoadon(makh, NgayMua, damua) output INSERTED.MaHoaDon values (?, ?, ?)";
//        PreparedStatement cmd = cn.prepareStatement(sql);
//        cmd.setLong(1, makh);
//        cmd.setDate(2, new java.sql.Date(NgayMua.getTime()));
//        cmd.setBoolean(3, false);
//
//        ResultSet rs = cmd.executeQuery();
//        long newMaHoaDon = 0;
//        if (rs.next()) {
//            newMaHoaDon = rs.getLong(1);
//        }
//
//        rs.close();
//        cmd.close();
//        cn.close();
//        return newMaHoaDon;
//    }
//
//    public long getMaxHoaDon() throws Exception {
//        Connection conn = dbHelper.getConnection();
//        String sql = "select max(mahoadon) as MaxHD from hoadon";
//
//        PreparedStatement cmd = conn.prepareStatement(sql);
//        ResultSet rs = cmd.executeQuery();
//        long max = 0;
//        if (rs.next()) {
//            max = rs.getLong(1);
//        }
//        rs.close();
//        cmd.close();
//        conn.close();
//        return max;
//
//    }
//
//
//}
package com.nhatnguyen.demoolop.model.hoadonModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class hoadondao {
    public ArrayList<hoadon> getListHoaDon() throws Exception {
        ArrayList<hoadon> ds = new ArrayList<hoadon>();
        Connection conn = dbHelper.getConnection();
        String sql = "select * from ViewHoaDon";
        PreparedStatement cmd = conn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        while(rs.next()) {
            Long mahoadon = rs.getLong("mahoadon");
            Long makhachhang = rs.getLong("makhachhang");
            String hotenkhach = rs.getString("hotenkhach");
            Date ngaymua = rs.getDate("ngaymua");
            boolean trangthai = rs.getBoolean("trangthai");
            ds.add(new hoadon(mahoadon, makhachhang, hotenkhach, ngaymua, trangthai));


        }
        rs.close();
        cmd.close();
        conn.close();
        return ds;
    }
    public hoadon getHoaDon(Long mahoadon) throws Exception {
        Connection conn = dbHelper.getConnection();
        hoadon hd = null;
        String sql = "select * from ViewHoaDon where mahoadon = ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setLong(1, mahoadon);
        ResultSet rs = cmd.executeQuery();
        if(rs.next()) {
            Long makhachhang = rs.getLong("makhachhang");
            String hotenkhach = rs.getString("hotenkhach");
            Date ngaymua = rs.getDate("ngaymua");
            boolean trangthai = rs.getBoolean("trangthai");
            hd = new hoadon(mahoadon, makhachhang, hotenkhach, ngaymua, trangthai);

        }
        rs.close();
        cmd.close();
        conn.close();
        return hd;
    }

    public long themHoaDon(long makh, Date NgayMua, boolean damua) throws Exception {
        Connection cn = null;
        PreparedStatement checkCmd = null;
        PreparedStatement insertCmd = null;
        ResultSet rs = null;
        Random random = new Random();
        long newMaHoaDon = 0;

        try {
            cn = dbHelper.getConnection();
            cn.setAutoCommit(false);

            // Query kiểm tra mã hóa đơn đã tồn tại
            String checkSql = "SELECT COUNT(*) FROM hoadon WHERE MaHoaDon = ?";

            // Query thêm mới hóa đơn
            String insertSql = "INSERT INTO hoadon (MaHoaDon, makh, NgayMua, damua) OUTPUT INSERTED.MaHoaDon VALUES (?, ?, ?, ?)";

            // Lặp để sinh mã và kiểm tra
            boolean maHoaDonHopLe = false;
            while (!maHoaDonHopLe) {
                newMaHoaDon = random.nextInt(999999); // Sinh mã ngẫu nhiên từ 0 đến 999999

                // Kiểm tra xem mã hóa đơn đã tồn tại hay chưa
                checkCmd = cn.prepareStatement(checkSql);
                checkCmd.setLong(1, newMaHoaDon);
                rs = checkCmd.executeQuery();

                if (rs.next() && rs.getInt(1) == 0) {
                    maHoaDonHopLe = true; // Mã chưa tồn tại
                }

                if (rs != null) rs.close();
                if (checkCmd != null) checkCmd.close();
            }

            // Thêm hóa đơn mới với mã hợp lệ
            insertCmd = cn.prepareStatement(insertSql);
            insertCmd.setLong(1, newMaHoaDon);
            insertCmd.setLong(2, makh);
            insertCmd.setDate(3, new java.sql.Date(NgayMua.getTime()));
            insertCmd.setBoolean(4, damua);

            rs = insertCmd.executeQuery();
            if (rs.next()) {
                newMaHoaDon = rs.getLong(1);
            }

            cn.commit();
            return newMaHoaDon;
        } catch (Exception e) {
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (Exception ex) {
                    throw ex;
                }
            }
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (checkCmd != null) checkCmd.close();
            if (insertCmd != null) insertCmd.close();
            if (cn != null) {
                cn.setAutoCommit(true);
                cn.close();
            }
        }
    }


    public long getMaxHoaDon() throws Exception {
        Connection cn = null;
        PreparedStatement cmd = null;
        ResultSet rs = null;
        try {
            cn = dbHelper.getConnection();
            String sql = "SELECT MAX(MaHoaDon) FROM hoadon";
            cmd = cn.prepareStatement(sql);
            rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0;
        } finally {
            if (rs != null) rs.close();
            if (cmd != null) cmd.close();
            if (cn != null) cn.close();
        }
    }
    public int updateTrangThai(Long mahoadon) throws Exception {
        Connection conn = dbHelper.getConnection();

        String sql = "update ViewHoaDon\r\n"
                + "set trangthai = ?\r\n"
                + "where mahoadon = ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setLong(2, mahoadon);
        cmd.setBoolean(1, true);

        int result = cmd.executeUpdate();
        cmd.close();
        conn.close();

        return result;
    }
}