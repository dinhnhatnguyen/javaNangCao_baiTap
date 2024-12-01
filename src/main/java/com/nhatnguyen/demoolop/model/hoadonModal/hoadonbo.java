package com.nhatnguyen.demoolop.model.hoadonModal;

import java.util.Date;

public class hoadonbo {
	hoadondao hddao = new hoadondao();
	public int themHoaDon(long makh, Date NgayMua, boolean damua) throws Exception{
		return hddao.themHoaDon(makh, NgayMua, damua);
	}
	
	public long getMaxHoaDon() throws Exception{
		return hddao.getMaxHoaDon();
	}
}
