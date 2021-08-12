package com.example.udlogistic.model;

import java.io.Serializable;

public class KhachHang implements Serializable {
    String maKH,tenKhachHang,SDT,diaChi;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKhachHang, String SDT, String diaChi) {
        this.maKH = maKH;
        this.tenKhachHang = tenKhachHang;
        this.SDT = SDT;
        this.diaChi = diaChi;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
