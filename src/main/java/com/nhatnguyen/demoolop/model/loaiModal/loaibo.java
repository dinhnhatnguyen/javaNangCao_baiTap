package com.nhatnguyen.demoolop.model.loaiModal;

import java.util.ArrayList;

public class loaibo {
	loaidao ldao = new loaidao();
	public ArrayList<loai> getloai() throws Exception{
		return ldao.getloai();
	}
}
