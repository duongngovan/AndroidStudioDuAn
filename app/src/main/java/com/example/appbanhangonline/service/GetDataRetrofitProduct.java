package com.example.appbanhangonline.service;

import com.example.appbanhangonline.adapter.TestProductAdapter;
import com.example.appbanhangonline.model.All;
import com.example.appbanhangonline.model.Testproduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataRetrofitProduct {


    @GET("/json")
    Call<List<All>> getAll();

    @GET("/jsons")
    Call<List<All>> getAlls();

    @GET("/sanPhamShopAndroid")
    Call<List<Testproduct>> getSanPham();

    @POST("/timKiemSanPhamAndroid")
    Call<List<Testproduct>> timSanPham(@Query("tenSanPham") String tenSanPham);

    @GET("/timKiemTheoNganhHangAndroid")
    Call<List<Testproduct>> timKiemTheoNganh(@Query("nganhHang") String nganhHang);

}
