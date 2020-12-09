package com.example.cuahangfood.model;

public class Oder {
    private int madonhang;
    private int masanpham;
    private String tensanpham;
    private int soluongsanpham;
    private int giasanpham;
    private int sodienthoai;
    private String email;
    private String diachinguoimua;
    private String tennguoimua;

    public Oder(int madonhang, int masanpham, String tensanpham, int soluongsanpham, int giasanpham, int sodienthoai, String email, String diachinguoimua, String tennguoimua) {
        this.madonhang = madonhang;
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.soluongsanpham = soluongsanpham;
        this.giasanpham = giasanpham;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachinguoimua = diachinguoimua;
        this.tennguoimua = tennguoimua;
    }

    public int getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(int madonhang) {
        this.madonhang = madonhang;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSoluongsanpham() {
        return soluongsanpham;
    }

    public void setSoluongsanpham(int soluongsanpham) {
        this.soluongsanpham = soluongsanpham;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachinguoimua() {
        return diachinguoimua;
    }

    public void setDiachinguoimua(String diachinguoimua) {
        this.diachinguoimua = diachinguoimua;
    }

    public String getTennguoimua() {
        return tennguoimua;
    }

    public void setTennguoimua(String tennguoimua) {
        this.tennguoimua = tennguoimua;
    }
}
