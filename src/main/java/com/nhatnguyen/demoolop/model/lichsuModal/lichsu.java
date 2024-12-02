package com.nhatnguyen.demoolop.model.lichsuModal;

import java.util.Date;

public class lichsu {
	private long mahoadon;
	private String hoten;
	private String tensach;
	private long SoLuongMua;
	private long gia;
	private long ThanhTien;
	private Date NgayMua;
	private boolean damua;
	private long makh;
	public lichsu() {
		super();
	}
	public lichsu(long mahoadon,String hoten, String tensach, long soLuongMua, long gia, long thanhTien, Date ngayMua,
			boolean damua, long makh) {
		this.mahoadon = mahoadon;
		this.hoten = hoten;
		this.tensach = tensach;
		SoLuongMua = soLuongMua;
		this.gia = gia;
		ThanhTien = thanhTien;
		NgayMua = ngayMua;
		this.damua = damua;
		this.makh = makh;
	}
	public long getMahoadon() {return mahoadon;}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getTensach() {
		return tensach;
	}
	public void setTensach(String tensach) {
		this.tensach = tensach;
	}
	public long getSoLuongMua() {
		return SoLuongMua;
	}
	public void setSoLuongMua(long soLuongMua) {
		SoLuongMua = soLuongMua;
	}
	public long getGia() {
		return gia;
	}
	public void setGia(long gia) {
		this.gia = gia;
	}
	public long getThanhTien() {
		return ThanhTien;
	}
	public void setThanhTien(long thanhTien) {
		ThanhTien = thanhTien;
	}
	public Date getNgayMua() {
		return NgayMua;
	}
	public void setNgayMua(Date ngayMua) {
		NgayMua = ngayMua;
	}
	public boolean isDamua() {
		return damua;
	}
	public void setDamua(boolean damua) {
		this.damua = damua;
	}
	public long getMakh() {
		return makh;
	}
	public void setMakh(long makh) {
		this.makh = makh;
	}
	
	
	
	
}
