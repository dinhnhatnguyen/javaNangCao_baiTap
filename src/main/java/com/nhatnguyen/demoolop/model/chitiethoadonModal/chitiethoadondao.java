//package com.nhatnguyen.demoolop.model.chitiethoadonModal;
//
//import com.nhatnguyen.demoolop.model.Helper.dbHelper;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//
//
//
//public class chitiethoadondao {
//	public int themChiTietHoaDon(String MaSach, long SoLuongMua, long MaHoaDon) throws Exception{
//		Connection conn = dbHelper.getConnection();
//		String sql = "insert into ChiTietHoaDon(MaSach, SoLuongMua, MaHoaDon)\r\n"
//				+ "values (?,?,?)";
//		PreparedStatement cmd = conn.prepareStatement(sql);
//		cmd.setString(1, MaSach);
//		cmd.setLong(2, SoLuongMua);
/// /		cmd.setString(3, MaSach);
//
//
//// Should be:
//		cmd.setLong(3, MaHoaDon);
//
//		int kq = cmd.executeUpdate();
//
//		cmd.close();
//		conn.close();
//		return kq;
//	}
//
//}
package com.nhatnguyen.demoolop.model.chitiethoadonModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class chitiethoadondao {

    public ArrayList<chitiethoadon> getListChiTietTheoMaHD(Long mahoadon) throws Exception {
        ArrayList<chitiethoadon> ds = new ArrayList<chitiethoadon>();
        Connection conn = dbHelper.getConnection();
        String sql = "select * from ViewChiTietHoaDon where mahoadon = ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setLong(1, mahoadon);
        ResultSet rs = cmd.executeQuery();
        while(rs.next()) {
            Long machitiethd = rs.getLong("machitiethd");
            String masach = rs.getString("masach");
            String tensach = rs.getString("tensach");
            Long soluongmua = rs.getLong("soluongmua");
            Long gia = rs.getLong("gia");
            String anh = rs.getString("anh");
            ds.add(new chitiethoadon(machitiethd, masach, tensach, soluongmua, mahoadon, gia, anh));


        }
        rs.close();
        cmd.close();
        conn.close();
        return ds;
    }
    public int themChiTietHoaDon(String MaSach, long SoLuongMua, long MaHoaDon) throws Exception {
        Connection conn = null;
        PreparedStatement checkCmd = null;
        PreparedStatement insertCmd = null;
        ResultSet rs = null;
        Random random = new Random();
        long newMaChiTietHD = 0;

        try {
            conn = dbHelper.getConnection();

            // Query kiểm tra mã chi tiết hóa đơn đã tồn tại
            String checkSql = "SELECT COUNT(*) FROM ChiTietHoaDon WHERE MaChiTietHD = ?";

            // Query thêm mới chi tiết hóa đơn
            String insertSql = "INSERT INTO ChiTietHoaDon (MaChiTietHD, MaSach, SoLuongMua, MaHoaDon) VALUES (?, ?, ?, ?)";

            // Lặp để sinh mã và kiểm tra
            boolean maChiTietHDHopLe = false;
            while (!maChiTietHDHopLe) {
                newMaChiTietHD = random.nextInt(999999); // Sinh mã ngẫu nhiên từ 0 đến 999999

                // Kiểm tra xem mã chi tiết hóa đơn đã tồn tại hay chưa
                checkCmd = conn.prepareStatement(checkSql);
                checkCmd.setLong(1, newMaChiTietHD);
                rs = checkCmd.executeQuery();

                if (rs.next() && rs.getInt(1) == 0) {
                    maChiTietHDHopLe = true; // Mã chưa tồn tại
                }

                if (rs != null) rs.close();
                if (checkCmd != null) checkCmd.close();
            }

            // Thêm chi tiết hóa đơn mới với mã hợp lệ
            insertCmd = conn.prepareStatement(insertSql);
            insertCmd.setLong(1, newMaChiTietHD);
            insertCmd.setString(2, MaSach);
            insertCmd.setLong(3, SoLuongMua);
            insertCmd.setLong(4, MaHoaDon);

            return insertCmd.executeUpdate();
        } finally {
            if (rs != null) rs.close();
            if (checkCmd != null) checkCmd.close();
            if (insertCmd != null) insertCmd.close();
            if (conn != null) conn.close();
        }
    }

    public boolean checkOrderDetail(long maHoaDon) throws Exception {
        Connection conn = null;
        PreparedStatement cmd = null;
        ResultSet rs = null;
        try {
            conn = dbHelper.getConnection();
            String sql = "SELECT COUNT(*) FROM ChiTietHoaDon WHERE MaHoaDon = ?";
            cmd = conn.prepareStatement(sql);
            cmd.setLong(1, maHoaDon);
            rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } finally {
            if (rs != null) rs.close();
            if (cmd != null) cmd.close();
            if (conn != null) conn.close();
        }
    }
}
