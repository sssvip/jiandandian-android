package com.jdd.jiandandian.model;

public class Order implements java.io.Serializable {

    private Integer order_id;
    private Integer user_id;
    private Integer shop_id;
    private Integer table_shop_id;
    private String eat_time;
    private Integer order_status;
    private Double order_money;
    private String order_notice;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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

    public Integer getTable_shop_id() {
        return table_shop_id;
    }

    public void setTable_shop_id(Integer table_shop_id) {
        this.table_shop_id = table_shop_id;
    }

    public String getEat_time() {
        return eat_time;
    }

    public void setEat_time(String eat_time) {
        this.eat_time = eat_time;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Double getOrder_money() {
        return order_money;
    }

    public void setOrder_money(Double order_money) {
        this.order_money = order_money;
    }

    public String getOrder_notice() {
        return order_notice;
    }

    public void setOrder_notice(String order_notice) {
        this.order_notice = order_notice;
    }

    @Override
    public String toString() {
        return "Order [order_id=" + order_id + ", user_id=" + user_id
                + ", shop_id=" + shop_id + ", table_shop_id=" + table_shop_id
                + ", eat_time=" + eat_time + ", order_status=" + order_status
                + ", order_money=" + order_money + ", order_notice="
                + order_notice + "]";
    }

}