package com.example.udlogistic.model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    String maNV,tenNV,diaChi,SoDT,boPhan,chucVu;

    public NhanVien(String maNV, String tenNV, String diaChi, String soDT, String boPhan, String chucVu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        SoDT = soDT;
        this.boPhan = boPhan;
        this.chucVu = chucVu;
    }

    public NhanVien() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String soDT) {
        SoDT = soDT;
    }

    public String getBoPhan() {
        return boPhan;
    }

    public void setBoPhan(String boPhan) {
        this.boPhan = boPhan;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
