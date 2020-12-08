package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.All;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataRetrofitProduct {


    @GET("/json")
    Call<List<All>> getAll();

    @GET("/jsons")
    Call<List<All>> getAlls();

}
