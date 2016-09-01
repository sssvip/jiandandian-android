package com.jdd.jiandandian.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdd.jiandandian.R;
import com.jdd.jiandandian.fragment.LoginByPhone;
import com.jdd.jiandandian.fragment.LoginByUsername;
import com.jdd.jiandandian.util.IntentUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.login)
public class login extends Activity {
    @ViewById
    TextView phone_login, username_login;

    @ViewById
    FrameLayout login_content;
    //获取actionBar
    @ViewById
    LinearLayout actionBar_left;
    @ViewById
    TextView actionBar_right;


    private FragmentManager fragmentManager;
    private Fragment loginByPhone, loginByUsername;

    private static login myActivity;
    private int code_time = 20;

    public static login getMyActivity() {
        return myActivity;
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                loginByPhone = fragmentManager.findFragmentByTag("loginByPhone");
                TextView tv_get_code = (TextView) loginByPhone.getActivity().findViewById(R.id.tv_get_code);
                tv_get_code.setText(code_time + "s重新获取");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        loginByPhone = new LoginByPhone();
        loginByUsername = new LoginByUsername();
        fragmentTransaction.add(R.id.login_content, loginByUsername, "loginByUsername");
        fragmentTransaction.add(R.id.login_content, loginByPhone, "loginByPhone");
        fragmentTransaction.show(loginByPhone);
        fragmentTransaction.commit();
        myActivity = this;
    }

    @AfterViews
    public void views() {
        phone_login.setBackgroundResource(R.color.white);
        username_login.setBackgroundResource(R.color.gray);
    }

    @Click({R.id.phone_login, R.id.username_login, R.id.actionBar_left, R.id.actionBar_right})
    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.username_login:
                loginByUsername();
                break;
            case R.id.phone_login:
                loginByPhone();
                break;
            case R.id.actionBar_left:
                IntentUtil.turn(this, main_.class);
                //this.finish();
                break;
            case R.id.actionBar_right:
                IntentUtil.turn(this, register_.class);
                // finish();
                break;
        }
    }

    private float x1, y1, x2, y2;//y1,y2起始坐标，y3,y4离开坐标

    //滑动事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if (y1 - y2 > 300) {
                //Toast.makeText(MainActivity.this, "向上滑"+(y1-y2)+"", Toast.LENGTH_SHORT).show();
            } else if (y2 - y1 > 300) {
                //Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if (x1 - x2 > 300) {
                //Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                loginByPhone();
            } else if (x2 - x1 > 300) {
                //Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                loginByUsername();
            }
        }
        return super.onTouchEvent(event);
    }

    private void loginByPhone() {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        phone_login.setBackgroundResource(R.color.white);
        username_login.setBackgroundResource(R.color.gray);
        fTransaction.hide(loginByUsername);
        fTransaction.show(loginByPhone);
        fTransaction.commit();
    }

    private void loginByUsername() {

        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        username_login.setBackgroundResource(R.color.white);
        phone_login.setBackgroundResource(R.color.gray);
        fTransaction.hide(loginByPhone);
        fTransaction.show(loginByUsername);
        fTransaction.commit();
    }
}
