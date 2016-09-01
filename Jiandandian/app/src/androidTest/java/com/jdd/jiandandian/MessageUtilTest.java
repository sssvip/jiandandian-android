package com.jdd.jiandandian;

import android.test.AndroidTestCase;

import com.jdd.jiandandian.util.MessageUtil;

/**
 * Created by David on 2016/6/20.
 */
public class MessageUtilTest extends AndroidTestCase {

    public void testsend() {
        MessageUtil.sendCode("15388389933", "11558855");
    }
}
