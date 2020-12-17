package com.example.appbanhangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wishlist {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("idUser")
    @Expose
    private String idUser;

    @SerializedName("idSanPham")
    @Expose
    private Testproduct testproduct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Testproduct getTestproduct() {
        return testproduct;
    }

    public void setTestproduct(Testproduct testproduct) {
        this.testproduct = testproduct;
    }
}
