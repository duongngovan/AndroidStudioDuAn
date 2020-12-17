package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataRetrofitUser {

    @POST("/registerUser")
    Call<UserModel> add(@Body UserModel userModel);

    @POST("/loginUserAndroid")
    Call<UserModel> login(@Query("email") String username, @Query("password") String pass);

    @POST("/capNhatThongTinUserAndroid")
    Call<UserModel> capNhat(@Query("idUser")String id,@Query("hoVaTen") String hoVaTen, @Query("soDienThoai") String soDienThoai, @Query("diaChi")String diachi);

}
