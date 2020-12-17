package com.example.appbanhangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class    Items {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("idSanPham")
    @Expose
    private Testproduct testproduct;

    @SerializedName("soLuongMua")
    @Expose
    private Integer soLuongMua;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Testproduct getTestproduct() {
        return testproduct;
    }

    public void setTestproduct(Testproduct testproduct) {
        this.testproduct = testproduct;
    }

    public Integer getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(Integer soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
}
