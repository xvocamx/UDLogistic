package com.example.udlogistic.model;

import java.io.Serializable;

public class DoiXeVanTai implements Serializable {

    String maUI, soXe, soContainer, donGia, khachHang, ngayDiGiao, noiLayCong, noiDongHang;

    public DoiXeVanTai() {
    }

    public DoiXeVanTai(String maUI, String soXe, String soContainer, String donGia, String khachHang, String ngayDiGiao, String noiLayCong, String noiDongHang) {
        this.maUI = maUI;
        this.soXe = soXe;
        this.soContainer = soContainer;
        this.donGia = donGia;
        this.khachHang = khachHang;
        this.ngayDiGiao = ngayDiGiao;
        this.noiLayCong = noiLayCong;
        this.noiDongHang = noiDongHang;
    }

    public String getMaUI() {
        return maUI;
    }

    public void setMaUI(String maUI) {
        this.maUI = maUI;
    }

    public String getSoXe() {
        return soXe;
    }

    public void setSoXe(String soXe) {
        this.soXe = soXe;
    }

    public String getSoContainer() {
        return soContainer;
    }

    public void setSoContainer(String soContainer) {
        this.soContainer = soContainer;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getNgayDiGiao() {
        return ngayDiGiao;
    }

    public void setNgayDiGiao(String ngayDiGiao) {
        this.ngayDiGiao = ngayDiGiao;
    }

    public String getNoiLayCong() {
        return noiLayCong;
    }

    public void setNoiLayCong(String noiLayCong) {
        this.noiLayCong = noiLayCong;
    }

    public String getNoiDongHang() {
        return noiDongHang;
    }

    public void setNoiDongHang(String noiDongHang) {
        this.noiDongHang = noiDongHang;
    }
}
