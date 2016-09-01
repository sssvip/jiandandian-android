package com.jdd.jiandandian.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdd.jiandandian.model.User;
import com.jdd.jiandandian.util.ConnectUtil;
import com.jdd.jiandandian.util.LogUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.IOException;

/**
 * Created by David on 2016/6/21.
 */
public class UserDao {
    private String result = "default status_code";
    private User user;
    private int code;

    public UserDao() {
        //test
    }

    public String register(User user) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams();
        params.put("user", json);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(ConnectUtil.projectUrl + "user/add.do", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //super.onSuccess(statusCode, headers, responseBody);
                LogUtil.i("code:" + statusCode + "content:" + new String(responseBody));
                result = new String(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //super.onFailure(statusCode, headers, responseBody, error);
                LogUtil.i("code:" + statusCode + "content:" + new String(responseBody));
                result = new String(responseBody);
            }
        });
        return result;
    }

    public User login(String username, String password) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);
        asyncHttpClient.post(ConnectUtil.projectUrl + "user/login.do", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                super.onSuccess(statusCode, headers, responseBody);
                //LogUtil.i("code:" + statusCode + "content:" + new String(responseBody));
                if (statusCode == 200) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        user = mapper.readValue(new String(responseBody), User.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return user;
    }
}
