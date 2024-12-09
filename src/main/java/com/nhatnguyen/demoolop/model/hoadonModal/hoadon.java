package com.nhatnguyen.demoolop.model.hoadonModal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class hoadon {
    private Long mahoadon;
    private Long makhachhang;
    private String hotenkhach;
    private Date ngaymua;
    private boolean trangthai;
    public hoadon(Long mahoadon, Long makhachhang, String hotenkhach, Date ngaymua, boolean trangthai) {
        super();
        this.mahoadon = mahoadon;
        this.makhachhang = makhachhang;
        this.ngaymua = ngaymua;
        this.trangthai = trangthai;
        this.hotenkhach = hotenkhach;
    }
    public hoadon() {
        super();
    }

    public Long getMahoadon() {
        return mahoadon;
    }
    public void setMahoadon(Long mahoadon) {
        this.mahoadon = mahoadon;
    }
    public Long getMakhachhang() {
        return makhachhang;
    }
    public void setMakh(Long makhachhang) {
        this.makhachhang = makhachhang;
    }
    public Date getNgaymua() {
        return ngaymua;
    }
    public void setNgaymua(Date ngaymua) {
        this.ngaymua = ngaymua;
    }
    public boolean isTrangthai() {
        return trangthai;
    }
    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    public String getHotenkhach() {
        return hotenkhach;
    }
    public void setHotenkhach(String hotenkhach) {
        this.hotenkhach = hotenkhach;
    }
}
