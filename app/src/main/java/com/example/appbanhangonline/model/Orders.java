package com.example.appbanhangonline.model;

public class Orders {

    public static final int TOTAL_ORDERS_ITEM = 0;

    public static final int TOTAL_ORDERS_ADDRESS = 1;


    private int type;

    // orders_cart_item;
    private String image_product;
    private String name_product;
    private String giam_gia;
    private int price_product;
    private int so_luong;

    public Orders(int type, String image_product, String name_product, String giam_gia, int price_product, int so_luong) {
        this.type = type;
        this.image_product = image_product;
        this.name_product = name_product;
        this.giam_gia = giam_gia;
        this.price_product = price_product;
        this.so_luong = so_luong;
    }

    //
    private String name_khach_hang;
    private String address_khach_hang;
    private String phone_khach_hang;

    public Orders(int type, String name_khach_hang, String address_khach_hang, String phone_khach_hang) {
        this.type = type;
        this.name_khach_hang = name_khach_hang;
        this.address_khach_hang = address_khach_hang;
        this.phone_khach_hang = phone_khach_hang;
    }

    //


}
