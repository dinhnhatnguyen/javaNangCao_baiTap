package com.nhatnguyen.demoolop.model.sachModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class sachdao {
    public ArrayList<sach> getsach() throws Exception {
        ArrayList<sach> ds = new ArrayList<sach>();
        Connection conn = dbHelper.getConnection();
        String sql = "SELECT * FROM sach";
        PreparedStatement cmd = conn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            String masach = rs.getString("masach");
            String tensach = rs.getString("tensach");
            String tacgia = rs.getString("tacgia");
            Long gia = rs.getLong("gia");
            Long soluong = rs.getLong("soluong");
            String anh = rs.getString("anh");
            String maloai = rs.getString("maloai");
            ds.add(new sach(masach, tensach, tacgia, gia, soluong, anh, maloai));
        }
        rs.close();
        cmd.close();
        conn.close();
        return ds;
    }

    //ham lay ds sach theo trang
    public ArrayList<sach> getListByPage(ArrayList<sach> ds, int start, int end) {
        ArrayList<sach> arr = new ArrayList<sach>();
        for (int i = start; i < end; i++) {
            arr.add(ds.get(i));
        }
        return arr;

    }


    public ArrayList<sach> getsachPhanTrang(int page, int pagesize) throws Exception {
        ArrayList<sach> ds = new ArrayList<sach>();
        Connection conn = dbHelper.getConnection();

        //tinh offset dua tren page va pagesize
        int offset = page * pagesize;

        //them limit va offset vao cau lenh sql
        String sql = "SELECT * FROM sach LIMIT ? OFFSET ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setInt(1, pagesize);
        cmd.setInt(2, offset);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            String masach = rs.getString("masach");
            String tensach = rs.getString("tensach");
            String tacgia = rs.getString("tacgia");
            Long gia = rs.getLong("gia");
            Long soluong = rs.getLong("soluong");
            String anh = rs.getString("anh");
            String maloai = rs.getString("maloai");

            ds.add(new sach(masach, tensach, tacgia, gia, soluong, anh, maloai));

        }
        rs.close();
        cmd.close();
        conn.close();
        return ds;

    }

    //lay tong so ban ghi trong sach (de tinh tong so trang)
    public int tongSoBanGhi() throws Exception {
        Connection conn = dbHelper.getConnection();
        String sql = "SELECT COUNT(*) FROM sach";
        PreparedStatement cmd = conn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        int tong = 0;
        while (rs.next()) {
            tong = rs.getInt(1);
        }
        rs.close();
        cmd.close();
        conn.close();
        return tong;
    }

    //tim theo ma loai co phan trang
    public ArrayList<sach> TimMaPhanTrang(String maloai, int page, int pagesize) throws Exception {
        ArrayList<sach> ds = new ArrayList<sach>();
        Connection conn = dbHelper.getConnection();

        int offset = page * pagesize;
        String sql = "SELECT * FROM sach WHERE maloai = ? LIMIT ? OFFSET ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setString(1, maloai);
        cmd.setInt(2, pagesize);
        cmd.setInt(3, offset);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            String masach = rs.getString("masach");
            String tensach = rs.getString("tensach");
            String tacgia = rs.getString("tacgia");
            Long gia = rs.getLong("gia");
            Long soluong = rs.getLong("soluong");
            String anh = rs.getString("anh");

            ds.add(new sach(masach, tensach, tacgia, gia, soluong, anh, maloai));
        }
        rs.close();
        cmd.close();
        conn.close();
        return ds;
    }

    //lay tong so sach theo ma loai
    public int layTongSachTheoMaLoai(String maloai) throws Exception {
        Connection conn = dbHelper.getConnection();
        String sql = "SELECT COUNT(*) FROM sach WHERE maloai = ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setString(1, maloai);

        ResultSet rs = cmd.executeQuery();
        int tong = 0;
        if (rs.next()) {
            tong = rs.getInt(1);
        }
        rs.close();
        cmd.close();
        conn.close();
        return tong;
    }

    //tim theo tu khoa tim kiem co phan trang
    public ArrayList<sach> TimTheoTuKhoa(String key, int page, int pagesize) throws Exception {
        ArrayList<sach> ds = new ArrayList<sach>();
        Connection conn = dbHelper.getConnection();

        int offset = page * pagesize;
        String sql = "SELECT * FROM sach WHERE tensach LIKE ? OR tacgia LIKE ? LIMIT ? OFFSET ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setString(1, "%" + key + "%");
        cmd.setString(2, "%" + key + "%");
        cmd.setInt(3, pagesize);
        cmd.setInt(4, offset);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            String masach = rs.getString("masach");
            String tensach = rs.getString("tensach");
            String tacgia = rs.getString("tacgia");
            Long gia = rs.getLong("gia");
            Long soluong = rs.getLong("soluong");
            String anh = rs.getString("anh");
            String maloai = rs.getString("maloai");

            ds.add(new sach(masach, tensach, tacgia, gia, soluong, anh, maloai));
        }
        rs.close();
        cmd.close();
        conn.close();
        return ds;

    }

    //lay tong so sach theo tu khoa tim kiem
    public int layTongSachTheoTuKhoa(String key) throws Exception {
        Connection conn = dbHelper.getConnection();
        String sql = "SELECT COUNT(*) FROM sach WHERE tensach LIKE ? OR tacgia LIKE ?";
        PreparedStatement cmd = conn.prepareStatement(sql);
        cmd.setString(1, "%" + key + "%");
        cmd.setString(1, "%" + key + "%");
        ResultSet rs = cmd.executeQuery();
        int tong = 0;
        if (rs.next()) {
            tong = rs.getInt(1);
        }
        rs.close();
        cmd.close();
        conn.close();
        return tong;
    }



}
