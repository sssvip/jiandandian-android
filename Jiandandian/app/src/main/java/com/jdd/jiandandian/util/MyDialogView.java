package com.jdd.jiandandian.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdd.jiandandian.R;

/**
 * Created by David on 2016/6/21.
 */
public class MyDialogView {
    private LinearLayout linearLayout;
    private TextView title;
    private TextView content;
    private Button confim;
    private Button cancle;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    public MyDialogView(Context context) {
        this.context = context;
        //初始化弹窗
        builder = new AlertDialog.Builder(context);
        dialog = builder.create();
        dialog.setCancelable(false);
        this.linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog, null);
        title = (TextView) linearLayout.findViewById(R.id.dialog_tv_title);
        content = (TextView) linearLayout.findViewById(R.id.dialog_tv_content);
        confim = (Button) linearLayout.findViewById(R.id.dialog_btn_confim);
        cancle = (Button) linearLayout.findViewById(R.id.dialog_btn_cancle);
        //将自定义视图添加进弹窗
        dialog.setView(linearLayout);
        //默认取消按钮不显示
        cancle.setVisibility(View.GONE);

        //为确认和取消按钮添加默认--取消弹窗--效果
        confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public Button getConfim() {
        return confim;
    }

    public Button getCancle() {
        return cancle;
    }

    public void setTitleValue(String tempTitle) {
        title.setText(tempTitle);
    }

    public void setContentValue(String tempContent) {
        content.setText(tempContent);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
