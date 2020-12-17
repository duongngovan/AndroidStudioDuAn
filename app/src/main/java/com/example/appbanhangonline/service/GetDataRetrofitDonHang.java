package com.example.appbanhangonline.service;

import com.example.appbanhangonline.model.DonHangChiTiet;
import com.example.appbanhangonline.model.Items;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataRetrofitDonHang {

    @POST("/donHangChiTietUserAndroid")
    Call<List<DonHangChiTiet>> donHangChiTiet(@Query("idHoaDon") String hodon);

    @POST("/donHangChiTietsUserAndroid")
    Call<List<Items>> donHangChiTiets(@Query("idHoaDon") String hodon);

    @POST("/sanPhamDaXem")
    Call<List<Items>>  sanPhamDaXem(@Query("idUser") String idUser,@Query("idSanPham") String idSanPham);

    @POST("/sanPhamYeuThich")
    Call<List<Items>>  sanPhamYeuThich(@Query("idUser") String idUser,@Query("idSanPham") String idSanPham);

}
