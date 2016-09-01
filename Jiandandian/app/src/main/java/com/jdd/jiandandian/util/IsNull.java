package com.jdd.jiandandian.util;

/*
 *@author:David admin@dxscx.com
 *@Version: 1.0
 *@Description:  What do you want to do?
 *@Time:2016-5-17  下午2:33:42
 */
public class IsNull {
    public static boolean isNull(Object object) {
        if (object == null || "".equals(object)) {
            return true;
        } else {
            return false;
        }
    }
}
