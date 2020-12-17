package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.Wishlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataRetrofitWishList {
    @GET("/hienThiSanPhamYeuThichAndroid")
    Call<List<Wishlist>> getAll(@Query("idUser")String id);

    @POST("/sanPhamYeuThichAndroid")
    Call<String> add(@Query("idUser") String idUser,@Query("idSanPham") String idSanPham);
}
