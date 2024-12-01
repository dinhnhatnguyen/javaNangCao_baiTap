package com.nhatnguyen.demoolop.model.chitiethoadonModal;

public class chitiethoadonbo {
	chitiethoadonbo cthddao = new chitiethoadonbo();
	public int themChiTietHoaDon(String MaSach, long SoLuongMua, long MaHoaDon) throws Exception{
		return cthddao.themChiTietHoaDon(MaSach, SoLuongMua, MaHoaDon);
	}
}
