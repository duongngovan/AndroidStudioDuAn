package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.Watcheds;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataRetrofitWatched {
    @GET("/watch/all")
    Call<List<Watcheds>> getAll();

    @POST("/watch/add")
    Call<Watcheds> add(@Body Watcheds cart);

}
