package com.example.appbanhangonline.model;

public class SliderModel {
    private String banner;
    private int backgroundColor;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public SliderModel(String banner, int backgroundColor) {
        this.banner = banner;
        this.backgroundColor = backgroundColor;
    }
}
