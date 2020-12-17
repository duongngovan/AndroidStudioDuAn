package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.Cart;
import com.example.appbanhangonline.model.DonHangChiTiet;
import com.example.appbanhangonline.model.Items;
import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.model.TestCard;
import com.example.appbanhangonline.model.TestCardModel;
import com.example.appbanhangonline.model.TestDonHang;
import com.example.appbanhangonline.model.Testproduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataRetrofitCard {

    @GET("/cart/all")
    Call<List<Cart>> getAll();

    @POST("/cart/add")
    Call<Cart> add(@Body Cart cart);

    @POST("/themSanPhamVaoGioHangAndroid")
    Call<TestCard> adds(@Body TestCard testCard);

    @POST("/sanPhamTrongGioHangAndroid")
    Call<List<Items>> hienThi(@Query("idUser") String id);

    @POST("/thanhToanAndroid")
    Call<String> thanhthoan(@Query("idUser") String id);

    @GET("/donHangUserAndroid")
    Call<List<TestDonHang>> donHangUser(@Query("idUser") String id);

    @POST("/xoaSanPhamTrongGioHangAndroid")
    Call<List<String>> delte(@Query("idUser") String id, @Query("idSanPham") String id_sanpham);









}
