package com.jdd.jiandandian.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdd.jiandandian.R;
import com.jdd.jiandandian.adapter.OrderAdapter;
import com.jdd.jiandandian.view.MyListView;

import java.util.ArrayList;

/**
 * Created by David on 2016/6/23.
 */
public class OrderFragment extends Fragment {
    MyListView lv = null;

    public OrderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order, null);
        //初始化获取控件
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        lv = (MyListView) view.findViewById(R.id.order_content);
        //设置订单标题
        TextView title = (TextView) view.findViewById(R.id.actionBar_center_text);
        title.setText("我的订单");
        ArrayList<String> list = new ArrayList<String>();
        list.add("曾鹏家常菜");
        list.add("买多馅饼");
        list.add("沙县小吃");
        list.add("曾鹏家常菜");
        list.add("买多馅饼");
        list.add("沙县小吃");
        list.add("曾鹏家常菜");
        list.add("买多馅饼");
        list.add("沙县小吃");
        list.add("沙县小吃");
        list.add("曾鹏家常菜");
        list.add("买多馅饼");
        list.add("沙县小吃");
        list.add("沙县小吃");
        list.add("曾鹏家常菜");
        list.add("买多馅饼");
        list.add("沙县小吃");
        list.add("沙县小吃");
        list.add("曾鹏家常菜");
        list.add("买多馅饼");
        list.add("沙县小吃");
        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), list);
        lv.setAdapter(orderAdapter);
    }

}
