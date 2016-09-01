package com.jdd.jiandandian.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.jdd.jiandandian.R;
import com.jdd.jiandandian.fragment.MainFragment;
import com.jdd.jiandandian.fragment.OrderFragment;
import com.jdd.jiandandian.util.IntentUtil;
import com.jdd.jiandandian.util.MyDialogView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by David on 2016/6/20.
 */

@EActivity(R.layout.mian)
public class main extends Activity implements RadioGroup.OnCheckedChangeListener {
    @ViewById
    RadioGroup nav;
    private MyDialogView myDialogView;
    private FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Fragment main, order, my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        order = new OrderFragment();
        main = new MainFragment();
        fragmentTransaction.add(R.id.main_content, order, "order");
        fragmentTransaction.add(R.id.main_content, main, "main");
        fragmentTransaction.show(order);
        fragmentTransaction.hide(main);
        fragmentTransaction.commit();


    }

    @AfterViews
    public void add() {
        nav.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.nav_my:
                if (myDialogView == null) {
                    myDialogView = new MyDialogView(this);
                    myDialogView.show();
                }
                myDialogView.setContentValue("用户未登录，马上跳转登录注册！");
                myDialogView.getConfim().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtil.turn(main.this, login_.class);
                        //test
                    }
                });
                myDialogView.show();
                break;
            case R.id.nav_main:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(order);
                fragmentTransaction.show(main);
                fragmentTransaction.commit();
                break;
            case R.id.nav_order:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(main);
                fragmentTransaction.show(order);
                fragmentTransaction.commit();
                break;
        }
    }
}
