package com.example.appbanhangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestCardModel {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("idUser")
    @Expose
    private UserModel userModel;

    @SerializedName("items")
    @Expose
    private List<Items> items = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
