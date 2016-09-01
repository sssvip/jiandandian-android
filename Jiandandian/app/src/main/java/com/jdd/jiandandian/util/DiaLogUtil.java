package com.jdd.jiandandian.util;

import android.content.Context;

/**
 * Created by David on 2016/8/5.
 */
public class DiaLogUtil {
    static MyDialogView myDialogView;

    public static void showDialog(Context context, String message) {
        if (myDialogView == null) {
            myDialogView = new MyDialogView(context);
        }
        myDialogView.setContentValue(message);
        myDialogView.show();
    }
}
