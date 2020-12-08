package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataRetrofitUser {

    @POST("/user/add")
    Call<UserModel> add(@Body UserModel userModel);

    @GET("/user/login")
    Call<List<UserModel>> login(@Query("email") String username, @Query("password") String pass);
}
