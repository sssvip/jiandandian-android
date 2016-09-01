package com.jdd.jiandandian.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by David on 2016/6/13.
 */
public class MyService extends Service {
    public static MyService service;

    public static MyService getService() {
        return service;
    }

    private final static String TAG = "tag2";

    //必须要实现的方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind方法被调用!");
        return null;
    }

    //Service被创建时调用
    @Override
    public void onCreate() {
        service = this;
        Log.i(TAG, "onCreate方法被调用!");
        super.onCreate();
    }

    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand方法被调用!");
        return super.onStartCommand(intent, flags, startId);
    }

    //Service被关闭之前回调
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestory方法被调用!");
        super.onDestroy();
    }
}
