package com.example.appbanhangonline.model;

public class MyOrders {
    private String user;
    private String data;
    private double tongTien;
    private boolean trangThai;

    public MyOrders(String user, String data, double tongTien, boolean trangThai) {
        this.user = user;
        this.data = data;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
