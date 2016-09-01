package com.jdd.jiandandian.model;

public class Shop implements java.io.Serializable {

    private Integer shop_id;
    private String shop_name;
    private String shop_type;
    private String shop_img;
    private String shop_address;
    private Double shop_locationX;
    private Double shop_locationY;
    private Integer user_id;

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public Double getShop_locationX() {
        return shop_locationX;
    }

    public void setShop_locationX(Double shop_locationX) {
        this.shop_locationX = shop_locationX;
    }

    public Double getShop_locationY() {
        return shop_locationY;
    }

    public void setShop_locationY(Double shop_locationY) {
        this.shop_locationY = shop_locationY;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Shop [shop_id=" + shop_id + ", shop_name=" + shop_name
                + ", shop_type=" + shop_type + ", shop_img=" + shop_img
                + ", shop_address=" + shop_address + ", shop_locationX="
                + shop_locationX + ", shop_locationY=" + shop_locationY
                + ", user_id=" + user_id + "]";
    }

}