package com.jdd.jiandandian.model;

/**
 * ShopCollect entity. @author MyEclipse Persistence Tools
 */

public class ShopCollect implements java.io.Serializable {

    private Integer collect_id;
    private Integer user_id;
    private Integer shop_id;

    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    @Override
    public String toString() {
        return "ShopCollect [collect_id=" + collect_id + ", user_id=" + user_id
                + ", shop_id=" + shop_id + "]";
    }

}