package com.jdd.jiandandian.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by David on 2016/5/28.
 */
public class IntentUtil {
    private static Context lastContext;//上一个Context
    private static Intent intent = new Intent();//初始化意图对象

    public static void turn(Context context, Class currentClass) {
        lastContext = context;
        intent.setClass(context, currentClass);
        context.startActivity(intent);
    }

    public static void back() {
        intent.setClass(lastContext, lastContext.getClass());
        lastContext.startActivity(intent);
    }
}
