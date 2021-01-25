package com.example.cuahangfood.model;

public class Oder {
    public int madonhang;
    public Integer sodienthoai;
    public String email;
    public String diachinguoimua;
    public String tennguoimua;
    public int status;


    public Oder(int madonhang, Integer sodienthoai, String email, String diachinguoimua, String tennguoimua, int status) {
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

    public Integer getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(Integer sodienthoai) {
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
