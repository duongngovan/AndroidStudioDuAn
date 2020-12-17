package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.Watcheds;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataRetrofitWatched {
    @GET("/hienThiSanPhamDaXemAndroid")
    Call<List<Watcheds>> getAll(@Query("idUser") String id);

    @POST("/sanPhamDaXemAndroid")
    Call<Watcheds> add(@Query("idUser") String idUser, @Query("idSanPham") String idSanPham);



}
