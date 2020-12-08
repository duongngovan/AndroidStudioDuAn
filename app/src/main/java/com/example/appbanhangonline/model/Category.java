package com.example.appbanhangonline.model;

public class Category {



    private int image;
    private String name;

    public Category(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //
    private int image_view;
    private String title;
    private float ratting;
    private String price;
    private int amount;

    public Category(int image_view, String title, float ratting, String price, int amount) {
        this.image_view = image_view;
        this.title = title;
        this.ratting = ratting;
        this.price = price;
        this.amount = amount;
    }

    public int getImage_view() {
        return image_view;
    }

    public void setImage_view(int image_view) {
        this.image_view = image_view;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
