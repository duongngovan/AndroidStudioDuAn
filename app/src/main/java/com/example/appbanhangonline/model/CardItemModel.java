package com.example.appbanhangonline.model;

public class CardItemModel {

    public static final int ADDRESS = 0;
    public static final int CART_ITEM = 1;
    public static final int TOTAL_AMOUNT = 2;

    private int type;

    ////// cart item
    private String image_product;
    private String name_product;
    private String giam_gia;
    private int price_product;
    private int so_luong;

    public CardItemModel(String image_product, String name_product, String giam_gia, int price_product, int so_luong) {
        this.image_product = image_product;
        this.name_product = name_product;
        this.giam_gia = giam_gia;
        this.price_product = price_product;
        this.so_luong = so_luong;
    }

    public CardItemModel(int type, String name_product, String giam_gia, int price_product, int so_luong) {
        this.type = type;
        this.name_product = name_product;
        this.giam_gia = giam_gia;
        this.price_product = price_product;
        this.so_luong = so_luong;
    }

    public CardItemModel(int type, String image_product, String name_product, String giam_gia, int price_product, int so_luong) {
        this.type = type;
        this.image_product = image_product;
        this.name_product = name_product;
        this.giam_gia = giam_gia;
        this.price_product = price_product;
        this.so_luong = so_luong;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getGiam_gia() {
        return giam_gia;
    }

    public void setGiam_gia(String giam_gia) {
        this.giam_gia = giam_gia;
    }

    public int getPrice_product() {
        return price_product;
    }

    public void setPrice_product(int price_product) {
        this.price_product = price_product;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    //
    private int sumAmount;
    private String totalItemPrice;

    private String savedAmount;
    private String totalAmount;


    public CardItemModel(int type, int totalItems, String totalItemPrice,  String savedAmount, String totalAmount) {
        this.type = type;
        this.sumAmount = totalItems;
        this.totalItemPrice = totalItemPrice;

        this.savedAmount = savedAmount;
        this.totalAmount = totalAmount;
    }

    public int getTotalItems() {
        return sumAmount;
    }

    public void setTotalItems(int totalItems) {
        this.sumAmount = totalItems;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }



    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    // dia chi
    private String name;
    private String phone_number;
    private String address;

    public CardItemModel(int type, String name, String phone_number, String address) {
        this.type = type;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
