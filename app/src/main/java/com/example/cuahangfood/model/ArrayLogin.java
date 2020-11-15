package com.example.cuahangfood.model;

public class ArrayLogin {
    public  int id;
    public String taikhoan;
    public String matkhau;
    public int status;
    public ArrayLogin(int id, String taikhoan, String matkhau, int status) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




}
