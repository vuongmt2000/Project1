package com.example.cuahangfood.model;

public class Product_Type {
    public int Id;
    public String Name_Product_Type;
    public String Image_Product_Type;

    public Product_Type(int id, String name_Product_Type, String image_Product_Type){
        Id = id;
        Name_Product_Type = name_Product_Type;
        Image_Product_Type = image_Product_Type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName_Product_Type() {
        return Name_Product_Type;
    }

    public void setName_Product_Type(String name_Product_Type) {
        Name_Product_Type = name_Product_Type;
    }

    public String getImage_Product_Type() {
        return Image_Product_Type;
    }

    public void setImage_Product_Type(String image_Product_Type) {
        Image_Product_Type = image_Product_Type;
    }
}
