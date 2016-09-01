package com.jdd.jiandandian.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_nickname;
    private String user_phone;
    private Integer user_type;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_name=" + user_name
                + ", user_password=" + user_password + ", user_nickname="
                + user_nickname + ", user_phone=" + user_phone + ", user_type="
                + user_type + "]";
    }

}