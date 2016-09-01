package com.jdd.jiandandian.model;

/**
 * CookbookSellcount entity. @author MyEclipse Persistence Tools
 */

public class CookbookSellcount implements java.io.Serializable {

    private Integer cookbookSellcount_id;
    private Integer order_id;
    private Integer cookbook_id;

    public Integer getCookbookSellcount_id() {
        return cookbookSellcount_id;
    }

    public void setCookbookSellcount_id(Integer cookbookSellcount_id) {
        this.cookbookSellcount_id = cookbookSellcount_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getCookbook_id() {
        return cookbook_id;
    }

    public void setCookbook_id(Integer cookbook_id) {
        this.cookbook_id = cookbook_id;
    }

    @Override
    public String toString() {
        return "CookbookSellcount [cookbookSellcount_id="
                + cookbookSellcount_id + ", order_id=" + order_id
                + ", cookbook_id=" + cookbook_id + "]";
    }

}