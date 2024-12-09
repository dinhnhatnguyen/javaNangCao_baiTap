package com.nhatnguyen.demoolop.model.chitiethoadonModal;

public class chitiethoadon {
    private Long machitiethd;
    private String masach;
    private String tensach;
    private Long soluongmua;
    private Long mahoadon;
    private Long gia;
    private String anh;


    public chitiethoadon() {
        super();
    }
    public chitiethoadon(Long machitiethd, String masach, String tensach, Long soluongmua, Long mahoadon, Long gia, String anh) {
        super();
        this.machitiethd = machitiethd;
        this.masach = masach;
        this.tensach = tensach;
        this.soluongmua = soluongmua;
        this.mahoadon = mahoadon;
        this.gia = gia;
        this.anh = anh;
    }
    public Long getMachitiethd() {
        return machitiethd;
    }
    public void setMachitiethd(Long machitiethd) {
        this.machitiethd = machitiethd;
    }
    public String getMasach() {
        return masach;
    }
    public void setMasach(String masach) {
        this.masach = masach;
    }
    public String getTensach() {
        return tensach;
    }
    public void setTensach(String tensach) {
        this.tensach = tensach;
    }
    public Long getSoluongmua() {
        return soluongmua;
    }
    public void setSoluongmua(Long soluongmua) {
        this.soluongmua = soluongmua;
    }
    public Long getMahoadon() {
        return mahoadon;
    }
    public void setMahoadon(Long mahoadon) {
        this.mahoadon = mahoadon;
    }
    public Long getGia() {
        return gia;
    }
    public void setGia(Long gia) {
        this.gia = gia;
    }
    public String getAnh() {
        return anh;
    }
    public void setAnh(String anh) {
        this.anh = anh;
    }

}
