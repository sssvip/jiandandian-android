package com.jdd.jiandandian.model;

public class Cuisine implements java.io.Serializable {

    private Integer cuisine_id;
    private String cuisine_name;

    public Integer getCuisine_id() {
        return cuisine_id;
    }

    public void setCuisine_id(Integer cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    public String getCuisine_name() {
        return cuisine_name;
    }

    public void setCuisine_name(String cuisine_name) {
        this.cuisine_name = cuisine_name;
    }

    @Override
    public String toString() {
        return "Cuisine [cuisine_id=" + cuisine_id + ", cuisine_name="
                + cuisine_name + "]";
    }

}