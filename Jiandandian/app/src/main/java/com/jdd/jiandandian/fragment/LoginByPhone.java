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

import com.jdd.jiandandian.R;
import com.jdd.jiandandian.util.EditTextUtil;
import com.jdd.jiandandian.util.LogUtil;
import com.jdd.jiandandian.util.MessageUtil;
import com.jdd.jiandandian.util.MyDialogView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by David on 2016/6/21.
 */
public class LoginByPhone extends Fragment implements View.OnClickListener {
    private TextView tv_phone_clear, tv_code_clear, tv_get_code;
    private EditText et_phone, et_code;
    private Button login;
    private boolean btn_get_code = false;
    private List<String> phone_pre = new ArrayList<String>();
    private MyDialogView myDialogView;
    private final int CODE_TIME = 60;
    private int code_remain_time = 60;//验证码重新获取剩余时间（s）
    private boolean onetime = false;
    private int code_num;

    public LoginByPhone() {
        phone_pre.add("130");
        phone_pre.add("131");
        phone_pre.add("132");
        phone_pre.add("133");
        phone_pre.add("134");
        phone_pre.add("135");
        phone_pre.add("136");
        phone_pre.add("137");
        phone_pre.add("138");
        phone_pre.add("139");
        phone_pre.add("150");
        phone_pre.add("151");
        phone_pre.add("152");
        phone_pre.add("153");
        phone_pre.add("155");
        phone_pre.add("156");
        phone_pre.add("157");
        phone_pre.add("158");
        phone_pre.add("159");
        phone_pre.add("170");
        phone_pre.add("180");
        phone_pre.add("181");
        phone_pre.add("182");
        phone_pre.add("183");
        phone_pre.add("184");
        phone_pre.add("185");
        phone_pre.add("186");
        phone_pre.add("187");
        phone_pre.add("188");
        phone_pre.add("189");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginbyphone, null);
        findViews(view);
        return view;
    }

    //初始化控件
    private void findViews(View view) {
        tv_phone_clear = (TextView) view.findViewById(R.id.tv_phone_clear);
        tv_code_clear = (TextView) view.findViewById(R.id.tv_code_clear);
        tv_get_code = (TextView) view.findViewById(R.id.tv_get_code);
        et_phone = (EditText) view.findViewById(R.id.et_phone);

        et_code = (EditText) view.findViewById(R.id.et_code);
        login = (Button) view.findViewById(R.id.login);
        //为清楚图标添加监听时间
        tv_phone_clear.setOnClickListener(this);
        tv_code_clear.setOnClickListener(this);
        tv_get_code.setOnClickListener(this);
        login.setOnClickListener(this);
        //为编辑框添加改变监听事件
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tv_phone_clear.setVisibility(View.VISIBLE);
                } else {
                    tv_phone_clear.setVisibility(View.GONE);
                }
                if (s.length() >= 11) {
                    btn_get_code = true;
                    tv_get_code.setBackgroundResource(R.color.default_color);
                } else {
                    btn_get_code = false;
                    tv_get_code.setBackgroundResource(R.color.gray);
                }
            }
        });
        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    tv_code_clear.setVisibility(View.VISIBLE);
                } else {
                    tv_code_clear.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_code_clear:
                et_code.setText("");
                break;
            case R.id.tv_phone_clear:
                et_phone.setText("");
                break;
            case R.id.tv_get_code:
                String tempnum = et_phone.getText().toString().trim();
                if (btn_get_code) {
                    btn_get_code = false;
                    if (!phone_pre.contains(tempnum.subSequence(0, 3))) {
                        if (myDialogView == null) {
                            myDialogView = new MyDialogView(getActivity());
                        }
                        myDialogView.setContentValue("请输入正确的11位手机号码！");
                        myDialogView.show();
                        EditTextUtil.getFocus(et_phone);
                        btn_get_code = true;
                    } else {
                        if (myDialogView == null) {
                            myDialogView = new MyDialogView(getActivity());
                        }
                        myDialogView.setContentValue("已经发送验证码至" + tempnum + "，请注意查收!");
                        myDialogView.show();
                        EditTextUtil.getFocus(et_code);
                        while (code_num < 1000) {
                            code_num = new Random().nextInt(10000);
                        }
                        MessageUtil.sendCode(tempnum, code_num + "");
                        LogUtil.i("code:" + code_num);
                        myDialogView.getConfim().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                et_code.findFocus();
                                myDialogView.dismiss();
                            }
                        });
                        tv_phone_clear.setVisibility(View.GONE);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (code_remain_time > 0) {
                                    code_remain_time--;
                                    try {
                                        Thread.currentThread().sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv_get_code.setText(code_remain_time + "s后重新获取");
                                            btn_get_code = false;
                                            if (code_remain_time < 1) {
                                                tv_get_code.setText("重新获取");
                                            }
                                        }
                                    });
                                }
                                //线程完毕后重置为可点击状态，重置时间
                            }
                        }).start();
                        code_remain_time = CODE_TIME;
                        btn_get_code = true;
                    }
                }
                break;
            case R.id.login:
                String codeinput = et_code.getText().toString().trim();
                if (myDialogView == null) {
                    myDialogView = new MyDialogView(getActivity());
                }
                if (code_num == 0) {
                    myDialogView.setContentValue("请获取验证码！");
                    EditTextUtil.getFocusOpenKeyBoard(getActivity(), et_phone);
                } else if (codeinput == null || "".equals(codeinput)) {
                    myDialogView.setContentValue("请输入验证码！");
                    EditTextUtil.getFocusOpenKeyBoard(getActivity(), et_code);
                } else if (Integer.valueOf(codeinput) == code_num) {
                    myDialogView.setContentValue("验证码正确，登录成功！");
                } else {
                    myDialogView.setContentValue("验证码错误，请重新输入！");
                    EditTextUtil.getFocusOpenKeyBoard(getActivity(), et_code);
                }
                myDialogView.show();
                break;
        }
    }
}
