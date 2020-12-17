package com.example.appbanhangonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Testproduct {
    @SerializedName("hinh_anh")
    @Expose
    private List<HinhAnh> hinhAnh = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("tenSanPham")
    @Expose
    private String tenSanPham;
    @SerializedName("nganhHang")
    @Expose
    private String nganhHang;
    @SerializedName("gia")
    @Expose
    private Integer gia;
    @SerializedName("soLuong")
    @Expose
    private Integer soLuong;
    @SerializedName("moTa")
    @Expose
    private String moTa;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<HinhAnh> getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(List<HinhAnh> hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getNganhHang() {
        return nganhHang;
    }

    public void setNganhHang(String nganhHang) {
        this.nganhHang = nganhHang;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
