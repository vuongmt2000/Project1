package com.example.cuahangfood.Retrofit;

public class APIultil {
    public static final String Base_Url = "http://192.168.43.101:8080/server/";
    public static DataClient getData(){
        return  RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
