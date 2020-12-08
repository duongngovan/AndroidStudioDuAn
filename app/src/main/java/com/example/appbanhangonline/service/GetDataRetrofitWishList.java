package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.Wishlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataRetrofitWishList {
    @GET("/wishlist/all")
    Call<List<Wishlist>> getAll();

    @POST("/wishlist/add")
    Call<Wishlist> add(@Body Wishlist cart);
}
