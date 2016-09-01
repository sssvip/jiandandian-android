package com.jdd.jiandandian.model;

/**
 * Table entity. @author MyEclipse Persistence Tools
 */

public class Table implements java.io.Serializable {

    private Integer table_id;
    private Integer table_status;
    private Integer table_count;
    private Integer shop_id;
    private Integer table_shop_id;

    public Integer getTable_id() {
        return table_id;
    }

    public void setTable_id(Integer table_id) {
        this.table_id = table_id;
    }

    public Integer getTable_status() {
        return table_status;
    }

    public void setTable_status(Integer table_status) {
        this.table_status = table_status;
    }

    public Integer getTable_count() {
        return table_count;
    }

    public void setTable_count(Integer table_count) {
        this.table_count = table_count;
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

    @Override
    public String toString() {
        return "Table [table_id=" + table_id + ", table_status=" + table_status
                + ", table_count=" + table_count + ", shop_id=" + shop_id
                + ", table_shop_id=" + table_shop_id + "]";
    }

}