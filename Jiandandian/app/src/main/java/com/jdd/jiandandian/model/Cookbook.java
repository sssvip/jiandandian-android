package com.jdd.jiandandian.model;

/**
 * Cookbook entity. @author MyEclipse Persistence Tools
 */

public class Cookbook implements java.io.Serializable {
    private Integer cookbook_id;
    private Integer shop_id;
    private Integer cuisine_id;
    private String cookbook_name;
    private Integer cookbook_price;
    private String cookbook_img;

    public Integer getCookbook_id() {
        return cookbook_id;
    }

    public void setCookbook_id(Integer cookbook_id) {
        this.cookbook_id = cookbook_id;
    }

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public Integer getCuisine_id() {
        return cuisine_id;
    }

    public void setCuisine_id(Integer cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    public String getCookbook_name() {
        return cookbook_name;
    }

    public void setCookbook_name(String cookbook_name) {
        this.cookbook_name = cookbook_name;
    }

    public Integer getCookbook_price() {
        return cookbook_price;
    }

    public void setCookbook_price(Integer cookbook_price) {
        this.cookbook_price = cookbook_price;
    }

    public String getCookbook_img() {
        return cookbook_img;
    }

    public void setCookbook_img(String cookbook_img) {
        this.cookbook_img = cookbook_img;
    }

    @Override
    public String toString() {
        return "Cookbook [cookbook_id=" + cookbook_id + ", shop_id=" + shop_id
                + ", cuisine_id=" + cuisine_id + ", cookbook_name="
                + cookbook_name + ", cookbook_price=" + cookbook_price
                + ", cookbook_img=" + cookbook_img + "]";
    }

}