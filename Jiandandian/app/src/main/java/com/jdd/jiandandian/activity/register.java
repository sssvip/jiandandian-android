package com.jdd.jiandandian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdd.jiandandian.R;
import com.jdd.jiandandian.model.User;
import com.jdd.jiandandian.util.ConnectUtil;
import com.jdd.jiandandian.util.EditTextUtil;
import com.jdd.jiandandian.util.IntentUtil;
import com.jdd.jiandandian.util.IsNull;
import com.jdd.jiandandian.util.LoadingUtil;
import com.jdd.jiandandian.util.MyDialogView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

/**
 * Created by David on 2016/6/21.
 */


@EActivity(R.layout.register)
public class register extends Activity {
    @ViewById
    EditText et_username, et_password, et_password_confim, et_nickname;
    @ViewById
    Button btn_register;
    //actionBar controls init
    @ViewById
    LinearLayout actionBar_left;
    @ViewById
    TextView actionBar_right;
    MyDialogView myDialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void myAfterViews() {
        actionBar_right.setVisibility(View.GONE);
    }

    @Click({R.id.btn_register, R.id.actionBar_left})
    public void myClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (myDialogView == null) {
                    myDialogView = new MyDialogView(this);
                }
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String password_comfim = et_password_confim.getText().toString().trim();
                String nickname = et_nickname.getText().toString().trim();
                if (IsNull.isNull(username)) {
                    myDialogView.setContentValue("用户名不能为空！");
                    EditTextUtil.getFocusOpenKeyBoard(this, et_username);
                    myDialogView.show();
                } else if (IsNull.isNull(password)) {
                    myDialogView.setContentValue("密码不能为空！");
                    EditTextUtil.getFocusOpenKeyBoard(this, et_password);
                    myDialogView.show();
                } else if (IsNull.isNull(nickname)) {
                    myDialogView.setContentValue("昵称不能为空！");
                    EditTextUtil.getFocusOpenKeyBoard(this, et_nickname);
                    myDialogView.show();
                } else if (IsNull.isNull(password_comfim)) {
                    myDialogView.setContentValue("请确认密码！");
                    EditTextUtil.getFocusOpenKeyBoard(this, et_password_confim);
                    myDialogView.show();
                } else if (!password.equals(password_comfim)) {
                    myDialogView.setContentValue("两次密码输入不一致！");
                    EditTextUtil.getFocusOpenKeyBoard(this, et_password);
                    myDialogView.show();
                } else {
                    User u = new User();
                    u.setUser_name(username);
                    u.setUser_password(password);
                    u.setUser_nickname(nickname);
                    ObjectMapper mapper = new ObjectMapper();
                    String json = null;
                    try {
                        json = mapper.writeValueAsString(u);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    RequestParams params = new RequestParams();
                    params.put("user", json);
                    LoadingUtil.show(this);
                    if (ConnectUtil.isNetworkConnected(this)) {
                        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                        asyncHttpClient.post(ConnectUtil.projectUrl + "user/add.do", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                String register_result = new String(responseBody);
                                if (register_result.equals("success")) {

                                    register_result = "恭喜您注册成功！马上跳转...";
                                    myDialogView.getConfim().setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            IntentUtil.turn(register.this, login_.class);
                                            //ActivityUtil.close(register.this)
                                        }
                                    });
                                } else if (register_result.equalsIgnoreCase("user exsisted")) {
                                    register_result = "用户名已存在！";
                                } else {
                                    register_result = "网络连接超时，注册异常...";
                                }
                                myDialogView.setContentValue(register_result);
                                LoadingUtil.dismiss();
                                myDialogView.show();
                                //super.onSuccess(statusCode, headers, responseBody);
                                //LogUtil.i("code:" + statusCode + "content:" + new String(responseBody));
                                //result = new String(responseBody);
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                String register_result = new String(responseBody);
                                if (register_result.equalsIgnoreCase("user exsisted")) {
                                    register_result = "用户名已存在！";
                                } else {
                                    register_result = "注册异常...";
                                }
                                myDialogView.setContentValue(register_result);
                                myDialogView.show();
                            }
                        });
                    }
                }
                break;
            case R.id.actionBar_left:
                this.finish();
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
            } else if (x2 - x1 > 300) {
                //Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
        return super.onTouchEvent(event);
    }
}
