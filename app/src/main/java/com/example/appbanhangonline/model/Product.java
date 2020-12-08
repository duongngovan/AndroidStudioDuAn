package com.example.appbanhangonline.model;

public class Product {
    private int image;
    private String title;
    private float ratting;
    private int price;
    private int amount;
    private String giam_gia;

    public Product(int image, String title, float ratting,int price, int amount) {
        this.image = image;
        this.title = title;
        this.ratting = ratting;
        this.price = price;
        this.amount = amount;
    }

    public Product(int image, String title, float ratting, int price, int amount, String giam_gia) {
        this.image = image;
        this.title = title;
        this.ratting = ratting;
        this.price = price;
        this.amount = amount;
        this.giam_gia = giam_gia;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRatting() {
        return ratting;
    }

    public void setRatting(float ratting) {
        this.ratting = ratting;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getGiam_gia() {
        return giam_gia;
    }

    public void setGiam_gia(String giam_gia) {
        this.giam_gia = giam_gia;
    }
}
