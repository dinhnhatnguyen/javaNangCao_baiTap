package com.nhatnguyen.demoolop.model.sachModal;

import com.nhatnguyen.demoolop.model.Helper.dbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

public class sachbo {
	sachdao sdao = new sachdao();
	ArrayList<sach> ds;

	public boolean checkQuantityAvailable(String masach, long quantity) throws Exception {
		return sdao.checkQuantityAvailable(masach, quantity);
	}

	public boolean updateQuantity(String masach, long quantity) throws Exception {
		return sdao.updateQuantity(masach, quantity);
	}
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
		Connection conn = null;
		PreparedStatement checkCmd = null;
		PreparedStatement insertLoaiCmd = null;
		PreparedStatement insertSachCmd = null;
		ResultSet rs = null;
		String maloai = null;

		try {
			conn = dbHelper.getConnection();
			conn.setAutoCommit(false); // Bắt đầu transaction

			// Kiểm tra loại sách đã tồn tại chưa (so sánh theo tên loại)
			String checkLoai = "SELECT maloai FROM loai WHERE tenloai = ?";
			checkCmd = conn.prepareStatement(checkLoai);
			checkCmd.setString(1, s.getMaloai()); // Tạm thời dùng maloai để lưu tenloai từ form
			rs = checkCmd.executeQuery();

			if (rs.next()) {
				// Nếu loại sách đã tồn tại, lấy mã loại
				maloai = rs.getString("maloai");
			} else {
				// Nếu loại sách chưa tồn tại, tạo mã loại mới và thêm vào bảng loai
				maloai = generateMaLoai(s.getMaloai()); // Hàm tạo mã loại mới
				String insertLoai = "INSERT INTO loai (maloai, tenloai) VALUES (?, ?)";
				insertLoaiCmd = conn.prepareStatement(insertLoai);
				insertLoaiCmd.setString(1, maloai);
				insertLoaiCmd.setString(2, s.getMaloai()); // tenloai từ form
				insertLoaiCmd.executeUpdate();
			}

			// Thêm sách mới với mã loại đã có hoặc mới tạo
			String insertSach = "INSERT INTO sach (masach, tensach, tacgia, gia, soluong, anh, maloai) VALUES (?, ?, ?, ?, ?, ?, ?)";
			insertSachCmd = conn.prepareStatement(insertSach);
			insertSachCmd.setString(1, s.getMasach());
			insertSachCmd.setString(2, s.getTensach());
			insertSachCmd.setString(3, s.getTacgia());
			insertSachCmd.setLong(4, s.getGia());
			insertSachCmd.setLong(5, s.getSoluong());
			insertSachCmd.setString(6, s.getAnh());
			insertSachCmd.setString(7, maloai);

			int kq = insertSachCmd.executeUpdate();

			conn.commit(); // Commit transaction
			return kq;

		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback(); // Rollback nếu có lỗi
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw e;
		} finally {
			// Đóng tất cả các resource
			if (rs != null) rs.close();
			if (checkCmd != null) checkCmd.close();
			if (insertLoaiCmd != null) insertLoaiCmd.close();
			if (insertSachCmd != null) insertSachCmd.close();
			if (conn != null) {
				conn.setAutoCommit(true);
				conn.close();
			}
		}
	}
	private String generateMaLoai(String tenLoai) throws Exception {
		String tenLoaiKhongDau = removeAccents(tenLoai);
		String maLoai = tenLoaiKhongDau.substring(0, Math.min(tenLoaiKhongDau.length(), 5)).toUpperCase();

		Connection conn = dbHelper.getConnection();
		PreparedStatement checkCmd = null;
		ResultSet rs = null;

		try {
			String checkQuery = "SELECT 1 FROM loai WHERE maloai = ?";
			checkCmd = conn.prepareStatement(checkQuery);

			int counter = 0;
			while (true) {
				String candidateMaLoai = maLoai + (counter > 0 ? String.valueOf(counter) : "");
				checkCmd.setString(1, candidateMaLoai);
				rs = checkCmd.executeQuery();

				if (!rs.next()) {
					// Mã loại chưa tồn tại
					return candidateMaLoai;
				}
				counter++;
			}
		} finally {
			if (rs != null) rs.close();
			if (checkCmd != null) checkCmd.close();
			if (conn != null) conn.close();
		}
	}


	private String removeAccents(String input) {
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		return normalized.replaceAll("\\p{M}", "").trim(); // Bỏ dấu và cắt khoảng trắng
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



	public boolean checkQuantityAvailable(String masach, int requestedQuantity) throws Exception {
		Connection cn = dbHelper.getConnection();
		String sql = "SELECT soluong FROM sach WHERE masach = ?";
		PreparedStatement cmd = cn.prepareStatement(sql);
		cmd.setString(1, masach);
		ResultSet rs = cmd.executeQuery();

		boolean available = false;
		if (rs.next()) {
			int currentQuantity = rs.getInt("soluong");
			available = currentQuantity >= requestedQuantity;
		}

		rs.close();
		cmd.close();
		cn.close();

		return available;
	}

	public void updateQuantity(String masach, int soldQuantity) throws Exception {
		Connection cn = dbHelper.getConnection();
		String sql = "UPDATE sach SET soluong = soluong - ? WHERE masach = ?";
		PreparedStatement cmd = cn.prepareStatement(sql);
		cmd.setInt(1, soldQuantity);
		cmd.setString(2, masach);
		cmd.executeUpdate();

		cmd.close();
		cn.close();
	}
}
