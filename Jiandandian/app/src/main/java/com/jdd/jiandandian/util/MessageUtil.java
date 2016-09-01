package com.jdd.jiandandian.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by David on 2016/6/20.
 */
public class MessageUtil {
    static String localurl = ConnectUtil.localurl;
    static String remoteurl = ConnectUtil.remoteurl;

    public static String sendCode(String phone, String code) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(remoteurl + "jiandandian/servlet/SendMessage?check=david&phone=" + phone + "&code=" + code + "", new AsyncHttpResponseHandler());
        return "success";
    }
}
