package com.jdd.jiandandian.util;

import android.app.Dialog;
import android.content.Context;

import com.jdd.jiandandian.R;

/**
 * Created by David on 2016/6/23.
 */
public class LoadingUtil {
    private Context context;
    private static Dialog dialog;
    private static MyDialogView mydialogview;

    /**
     * 首先判断网络显示情况，如果有网络
     *
     * @param context
     */
    public static void show(Context context) {
        if (ConnectUtil.isNetworkConnected(context)) {
            dialog = new Dialog(context, R.style.dialog_noback);
            //set toumingdu
       /* WindowManager.LayoutParams winlp = dialog.getWindow()
                .getAttributes();
        winlp.alpha = 0.1f; // 0.0-1.0
        dialog.getWindow().setAttributes(winlp);*/
            dialog.setContentView(R.layout.loading);
            dialog.setCancelable(false);
            dialog.show();
        } else {
            if (IsNull.isNull(mydialogview)) {
                mydialogview = new MyDialogView(context);
            }
            mydialogview.setContentValue("网络异常，请检查！");
            mydialogview.show();
        }
    }

    public static void dismiss() {
        dialog.dismiss();
    }
}
