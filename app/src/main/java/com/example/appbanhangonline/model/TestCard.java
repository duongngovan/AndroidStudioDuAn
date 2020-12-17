package com.example.appbanhangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestCard {

    @SerializedName("idUser")
    @Expose
    private String idUser;

    @SerializedName("idSanPham")
    @Expose
    private String idSanPham;

    @SerializedName("soLuongMua")
    @Expose
    private Integer soLuongMua;


    public TestCard() {
    }

    public TestCard(String idUser, String idSanPham, Integer soLuongMua) {
        this.idUser = idUser;
        this.idSanPham = idSanPham;
        this.soLuongMua = soLuongMua;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(Integer soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
}
