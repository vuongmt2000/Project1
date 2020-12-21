package com.example.cuahangfood.model;

public class Oder {
    private int madonhang;
    private int sodienthoai;
    private String email;
    private String diachinguoimua;
    private String tennguoimua;
    private int status;
    public Oder (){

    }
    public Oder(int madonhang, int sodienthoai, String email, String diachinguoimua, String tennguoimua, int status) {
        this.madonhang = madonhang;
        this.sodienthoai = sodienthoai;
        this.email = email;
        this.diachinguoimua = diachinguoimua;
        this.tennguoimua = tennguoimua;
        this.status = status;
    }

    public int getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(int madonhang) {
        this.madonhang = madonhang;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
