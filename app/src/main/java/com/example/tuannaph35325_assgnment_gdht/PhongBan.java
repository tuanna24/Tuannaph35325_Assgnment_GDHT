package com.example.tuannaph35325_assgnment_gdht;

public class PhongBan {
    private  String nameOfPhongBan;
    private int img;

    public PhongBan(String nameOfPhongBan, int img) {
        this.nameOfPhongBan = nameOfPhongBan;
        this.img = img;
    }
    public PhongBan(){}

    public String getNameOfPhongBan() {
        return nameOfPhongBan;
    }

    public void setNameOfPhongBan(String nameOfPhongBan) {
        this.nameOfPhongBan = nameOfPhongBan;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
