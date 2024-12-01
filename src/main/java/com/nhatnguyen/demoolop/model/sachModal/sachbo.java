package com.nhatnguyen.demoolop.model.sachModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class sachbo {
	sachdao sdao = new sachdao();
	ArrayList<sach> ds;

	public ArrayList<sach> getsach() throws Exception {
		ds = sdao.getsach();
		return ds;
	}
	public ArrayList<sach> getListByPage(ArrayList<sach> list, int start, int end) throws Exception {
		return sdao.getListByPage(list, start, end);
	}
	//method co phan trang
	public ArrayList<sach> getsachPhanTrang(int page, int pagesize) throws Exception{
		return sdao.getsachPhanTrang(page, pagesize);
	}
	
	//method lay tong so sach
	public int tongSoBanGhi() throws Exception {
		return sdao.tongSoBanGhi();
	}
	
	//tim theo ma loai co phan trang
	public ArrayList<sach> TimMaPhanTrang(String maloai, int page, int pagesize) throws Exception {
		return sdao.TimMaPhanTrang(maloai, page, pagesize);
	}

	public ArrayList<sach> TimMa(String maloai) throws Exception {
		ArrayList<sach> tam = new ArrayList<sach>();
		
		for (sach s : ds) {
			if (s.getMaloai()!=null && s.getMaloai().toLowerCase().trim().equals(maloai.toLowerCase().trim())) {
				tam.add(s);
			}
		}
		return tam;
	}
	
	//lay tong so sach theo ma loai
	public int layTongSachTheoMaLoai(String maloai) throws Exception {
		return sdao.layTongSachTheoMaLoai(maloai);
	}
	
	//tim kiem sach co phan trang
	public ArrayList<sach> TimTheoTuKhoa(String key, int page, int pagesize) throws Exception {
		return sdao.TimTheoTuKhoa(key, page, pagesize);
	}
	
	//lay tong sach theo tu khoa tim kiem 
	public int layTongSachTheoTuKhoa(String key) throws Exception {
		return sdao.layTongSachTheoTuKhoa(key);
	}

	public ArrayList<sach> Tim(String key) throws Exception {
		ArrayList<sach> tam = new ArrayList<sach>();
		//ds = getsach();
		for (sach s : ds) {
			//boolean ktraLoai = (maloai == null || maloai.isEmpty() || s.getMaloai().equals(maloai));
			if (s.getTensach().toLowerCase().trim().contains(key.toLowerCase().trim())
					|| s.getTacgia().toLowerCase().trim().contains(key.toLowerCase().trim())) {
				tam.add(s);
			}
		}
		return tam;
	}
	public sach getProductById(String id) throws Exception {
	        ds = getsach(); 
	        for (sach s : ds) {
	            if (s.getMasach().equals(id)) { 
	                return s; 
	            }
	        }
	        return null;
	}
	public ArrayList<sach> removeProductById(ArrayList<sach> ds, String id) {
	    for (int i = 0; i < ds.size(); i++) {
	        sach s = ds.get(i);
	        if (s.getMasach().equals(id)) {
	            ds.remove(i);
	            break;
	        }
	    }
	    return ds;
	}

	public int themSach(sach s) throws Exception {
		Connection conn = dbHelper.getConnection();
		String sql = "INSERT INTO sach (masach, tensach, tacgia, gia, soluong, anh, maloai) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString(1, s.getMasach());
		cmd.setString(2, s.getTensach());
		cmd.setString(3, s.getTacgia());
		cmd.setLong(4, s.getGia());
		cmd.setLong(5, s.getSoluong());
		cmd.setString(6, s.getAnh());
		cmd.setString(7, s.getMaloai());

		int kq = cmd.executeUpdate();
		cmd.close();
		conn.close();
		return kq;
	}

	public int suaSach(sach s) throws Exception {
		Connection conn = dbHelper.getConnection();
		String sql = "UPDATE sach SET tensach=?, tacgia=?, gia=?, soluong=?, anh=?, maloai=? WHERE masach=?";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString(1, s.getTensach());
		cmd.setString(2, s.getTacgia());
		cmd.setLong(3, s.getGia());
		cmd.setLong(4, s.getSoluong());
		cmd.setString(5, s.getAnh());
		cmd.setString(6, s.getMaloai());
		cmd.setString(7, s.getMasach());

		int kq = cmd.executeUpdate();
		cmd.close();
		conn.close();
		return kq;
	}

	public int xoaSach(String masach) throws Exception {
		Connection conn = dbHelper.getConnection();
		String sql = "DELETE FROM sach WHERE masach=?";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString(1, masach);

		int kq = cmd.executeUpdate();
		cmd.close();
		conn.close();
		return kq;
	}
}
