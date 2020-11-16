package com.example.cuahangfood.model;

public class GioHangSP {
    public int idsp;
    public String tensp;
    public long giasP;
    public String hinhanhSP;
    public int soluongSP;

    public GioHangSP(int idsp, String tensp, long giasP, String hinhanhSP, int soluongSP) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasP = giasP;
        this.hinhanhSP = hinhanhSP;
        this.soluongSP = soluongSP;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasP() {
        return giasP;
    }

    public void setGiasP(long giasP) {
        this.giasP = giasP;
    }

    public String getHinhanhSP() {
        return hinhanhSP;
    }

    public void setHinhanhSP(String hinhanhSP) {
        this.hinhanhSP = hinhanhSP;
    }

    public int getSoluongSP() {
        return soluongSP;
    }

    public void setSoluongSP(int soluongSP) {
        this.soluongSP = soluongSP;
    }
}
