package com.example.tuannaph35325_assgnment_gdht;


import java.io.Serializable;

public class NhanVienModels implements Serializable {
    private String maNhanVien;
    private String hoVaTen;
    private String phongBan;

    public NhanVienModels(String maNhanVien, String hoVaTen, String phongBan) {
        this.maNhanVien = maNhanVien;
        this.hoVaTen = hoVaTen;
        this.phongBan = phongBan;
    }
    public NhanVienModels(){}

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }
}
