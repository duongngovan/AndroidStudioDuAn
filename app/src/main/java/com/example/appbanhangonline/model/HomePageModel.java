package com.example.appbanhangonline.model;

import java.util.List;

public class HomePageModel {

    public static final int SLIDER = 0;
    public static final int STRIP_AD = 1;
    public static final int FLASH_SALE = 2;
    public static final int PRODUCT_HOT = 3;
    public static final int PRODUCT_WATCHED = 4;
    public static final int PRODUCT_ALL = 5;

    private int type;

    //SLIDER

    private List<SliderModel> sliderList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderList() {
        return sliderList;
    }

    public void setSliderList(List<SliderModel> sliderList) {
        this.sliderList = sliderList;
    }

    public HomePageModel(int type, List<SliderModel> sliderList) {
        this.type = type;
        this.sliderList = sliderList;
    }
    //
    ///////// Strip Ad
    private int resource;
    private String backgroundColor;

    public HomePageModel(int type, int resource, String backgroundColor) {
        this.type = type;
        this.backgroundColor = backgroundColor;
        this.resource = resource;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    ///////// Strip Ad

    //FLASH
    private List<Product> flashList;

    public List<Product> getFlashList() {
        return flashList;
    }

    public void setFlashList(List<Product> flashList) {
        this.flashList = flashList;
    }
    //product_hot
    private List<Product> hotList;

    public List<Product> getHotList() {
        return hotList;
    }

    public void setHotList(List<Product> hotList) {
        this.hotList = hotList;
    }
    //product_watched
    private List<Product> watchedList;

    public List<Product> getWatchedList() {
        return watchedList;
    }

    public void setWatchedList(List<Product> watchedList) {
        this.watchedList = watchedList;
    }
    // product all
    private List<Product> allList;

    public List<Product> getAllList() {
        return allList;
    }

    public void setAllList(List<Product> allList) {
        this.allList = allList;
    }

    //
    private int image;
    private String title;
    private float ratting;
    private String price;
    private int amount;
    private String giam_gia;

    public HomePageModel(int type, int image, String title, float ratting, String price, int amount, String giam_gia) {
        this.type = type;
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

    public String getGiam_gia() {
        return giam_gia;
    }

    public void setGiam_gia(String giam_gia) {
        this.giam_gia = giam_gia;
    }
}
