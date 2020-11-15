package com.example.cuahangfood.model;

import java.io.Serializable;

public class Product   implements Serializable {
        public  int ID;
        public String Tensanpham;
        public Integer Giasanpham;
        public String Hinhanhsanpham;
        public String Motosanpham;
        public int IDSanpham;

    public Product(int ID, String tensanpham, Integer giasanpham, String hinhanhsanpham, String motosanpham, int IDSanpham) {
        this.ID = ID;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Motosanpham = motosanpham;
        this.IDSanpham = IDSanpham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public Integer getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotosanpham() {
        return Motosanpham;
    }

    public void setMotosanpham(String motosanpham) {
        Motosanpham = motosanpham;
    }

    public int getIDSanpham() {
        return IDSanpham;
    }

    public void setIDSanpham(int IDSanpham) {
        this.IDSanpham = IDSanpham;
    }
}
