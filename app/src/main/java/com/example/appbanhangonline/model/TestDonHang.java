package com.example.appbanhangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestDonHang {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("idHoaDon")
    @Expose
    private String idHoaDon;

    @SerializedName("idUser")
    @Expose
    private UserModel userModel;



    @SerializedName("sanPham")
    @Expose
    private List<Testproduct> testproduct;

    @SerializedName("tongTien")
    @Expose
    private Integer tongTien;

    @SerializedName("trangThai")
    @Expose
    private String trangThai;

    @SerializedName("ngayMuaHang")
    @Expose
    private String ngayMuaHang;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<Testproduct> getTestproduct() {
        return testproduct;
    }

    public void setTestproduct(List<Testproduct> testproduct) {
        this.testproduct = testproduct;
    }

    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayMuaHang() {
        return ngayMuaHang;
    }

    public void setNgayMuaHang(String ngayMuaHang) {
        this.ngayMuaHang = ngayMuaHang;
    }
}
