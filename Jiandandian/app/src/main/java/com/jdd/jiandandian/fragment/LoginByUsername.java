package com.jdd.jiandandian.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdd.jiandandian.R;
import com.jdd.jiandandian.model.User;
import com.jdd.jiandandian.util.ConnectUtil;
import com.jdd.jiandandian.util.EditTextUtil;
import com.jdd.jiandandian.util.IsNull;
import com.jdd.jiandandian.util.LoadingUtil;
import com.jdd.jiandandian.util.MyDialogView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.IOException;

/**
 * Created by David on 2016/6/21.
 */
public class LoginByUsername extends Fragment implements View.OnClickListener {
    private Button btn_login;
    private EditText et_username, et_password;
    private TextView tv_username_clear, tv_password_clear;
    private MyDialogView myDialogView;

    public LoginByUsername() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginbyusername, null);
        myDialogView = new MyDialogView(getActivity());
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        btn_login = (Button) view.findViewById(R.id.btn_login);
        et_username = (EditText) view.findViewById(R.id.et_username);
        et_password = (EditText) view.findViewById(R.id.et_password);
        tv_username_clear = (TextView) view.findViewById(R.id.tv_username_clear);
        tv_password_clear = (TextView) view.findViewById(R.id.tv_password_clear);

        //设置监听事件
        btn_login.setOnClickListener(this);
        tv_username_clear.setOnClickListener(this);
        tv_password_clear.setOnClickListener(this);
        //添加输入框的变化事件
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tv_username_clear.setVisibility(View.VISIBLE);
                } else {
                    tv_username_clear.setVisibility(View.GONE);

                }
            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tv_password_clear.setVisibility(View.VISIBLE);
                } else {
                    tv_password_clear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (myDialogView == null) {
                    myDialogView = new MyDialogView(getActivity());
                }
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (IsNull.isNull(username)) {
                    myDialogView.setContentValue("用户名不能为空！");
                    EditTextUtil.getFocusOpenKeyBoard(getActivity(), et_username);
                    myDialogView.show();
                } else if (IsNull.isNull(password)) {
                    myDialogView.setContentValue("密码不能为空！");
                    EditTextUtil.getFocusOpenKeyBoard(getActivity(), et_password);
                    myDialogView.show();
                } else {
                    LoadingUtil.show(getActivity());
                    if (ConnectUtil.isNetworkConnected(getActivity())) {
                        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                        RequestParams params = new RequestParams();
                        params.put("username", username);
                        params.put("password", password);
                        asyncHttpClient.post(ConnectUtil.projectUrl + "user/login.do", params, new AsyncHttpResponseHandler() {

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                super.onSuccess(statusCode, headers, responseBody);
                                //LogUtil.i("code:" + statusCode + "content:" + new String(responseBody));
                                if (statusCode == 200) {
                                    ObjectMapper mapper = new ObjectMapper();
                                    try {
                                        User user = mapper.readValue(new String(responseBody), User.class);
                                        if (IsNull.isNull(user)) {
                                            myDialogView.setContentValue("用户名或密码错误！");
                                        } else {
                                            myDialogView.setContentValue(user.toString());
                                        }
                                        LoadingUtil.dismiss();
                                        myDialogView.show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.tv_username_clear:
                et_username.setText("");
                break;
            case R.id.tv_password_clear:
                et_password.setText("");
                break;
        }
    }
}
