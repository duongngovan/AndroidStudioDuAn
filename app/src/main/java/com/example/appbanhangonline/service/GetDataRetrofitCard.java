package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataRetrofitCard {

    @GET("/cart/all")
    Call<List<Cart>> getAll();

    @POST("/cart/add")
    Call<Cart> add(@Body Cart cart);


}
