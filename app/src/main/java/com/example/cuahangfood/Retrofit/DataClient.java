package com.example.cuahangfood.Retrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    @Multipart
    @POST("uploadhinhanh.php")
    Call<String> UploadPhoto(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("addNewProduct.php")
    Call<String> InsertData(@Field("tensanpham")  String tensanpham
                            , @Field("giatrisanpham") String giasanpham
                            ,@Field("anhsanpham")  String anhsanpham
                            ,@Field("gioithieusanpham") String gioithieusanpham
                            ,@Field("idsanpham")  String idsanpham);
}
