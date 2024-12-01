package com.nhatnguyen.demoolop.model.lichsuModal;

import java.util.ArrayList;

public class lichsubo {
	lichsudao lsdao = new lichsudao();
	
	public ArrayList<lichsu> getLichSu(long makh, boolean damua) throws Exception {
		return lsdao.getLichSu(makh, damua);
	}
}
