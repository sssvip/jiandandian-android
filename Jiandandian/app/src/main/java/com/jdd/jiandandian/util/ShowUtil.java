package com.jdd.jiandandian.util;

import android.view.View;

/**
 * Created by David on 2016/5/28.
 */
public class ShowUtil {
    public static void hideView(View v) {
        v.setVisibility(View.GONE);
    }

    public static void changeViewStatus(View v) {
        if (v.getVisibility() == View.GONE) {
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.GONE);
        }
    }

    public static void showView(View v) {
        v.setVisibility(View.VISIBLE);
    }
}
