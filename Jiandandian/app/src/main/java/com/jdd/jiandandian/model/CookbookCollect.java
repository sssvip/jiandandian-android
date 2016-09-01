package com.jdd.jiandandian.model;

public class CookbookCollect implements java.io.Serializable {

    @Override
    public String toString() {
        return "CookbookCollect [collect_id=" + collect_id + ", user_id="
                + user_id + ", cookbook_id=" + cookbook_id + "]";
    }

    private Integer collect_id;
    private Integer user_id;
    private Integer cookbook_id;

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

    public Integer getCookbook_id() {
        return cookbook_id;
    }

    public void setCookbook_id(Integer cookbook_id) {
        this.cookbook_id = cookbook_id;
    }

}