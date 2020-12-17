package com.example.appbanhangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("hoVaTen")
    @Expose
    private String fullname;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("soDienThoai")
    @Expose
    private String phone;

    @SerializedName("diaChi")
    @Expose
    private String diaChi;

    public UserModel() {
    }

    public UserModel(String email, String fullname, String password, String phone, String diaChi) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
        this.diaChi = diaChi;
    }

    public UserModel(String email, String fullname, String phone, String password) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.phone = phone;
    }

    public UserModel(String email, String fullname, String password) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
