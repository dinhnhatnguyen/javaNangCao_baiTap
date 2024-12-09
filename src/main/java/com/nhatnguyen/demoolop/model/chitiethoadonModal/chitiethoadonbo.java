//package com.nhatnguyen.demoolop.model.chitiethoadonModal;
//
//public class chitiethoadonbo {
//	chitiethoadondao cthddao = new chitiethoadondao();
//	public int themChiTietHoaDon(String MaSach, long SoLuongMua, long MaHoaDon) throws Exception{
//		return cthddao.themChiTietHoaDon(MaSach, SoLuongMua, MaHoaDon);
//	}
//}

package com.nhatnguyen.demoolop.model.chitiethoadonModal;

import com.nhatnguyen.demoolop.model.hoadonModal.hoadon;

import java.util.ArrayList;

public class chitiethoadonbo {
	chitiethoadondao cthddao = new chitiethoadondao();

	public int themChiTietHoaDon(String MaSach, long SoLuongMua, long MaHoaDon) throws Exception {
		return cthddao.themChiTietHoaDon(MaSach, SoLuongMua, MaHoaDon);
	}



	public ArrayList<chitiethoadon> getListChiTietTheoMaHD(Long mahoadon) throws Exception {
		return cthddao.getListChiTietTheoMaHD(mahoadon);
	}
	public boolean checkOrderDetail(long maHoaDon) throws Exception {
		return cthddao.checkOrderDetail(maHoaDon);
	}


}