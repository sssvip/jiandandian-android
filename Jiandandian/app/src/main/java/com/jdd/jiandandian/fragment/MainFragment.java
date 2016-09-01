package com.jdd.jiandandian.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.jdd.jiandandian.R;
import com.jdd.jiandandian.adapter.OrderAdapter;

import java.util.ArrayList;

/**
 * Created by David on 2016/6/23.
 */
public class MainFragment extends Fragment {
    ListView lv = null;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order, null);
        //初始化获取控件
        findViews(view);
        return view;
    }

    private void findViews(View view) {
        lv = (ListView) view.findViewById(R.id.order_content);
        //设置订单标题
        TextView title = (TextView) view.findViewById(R.id.actionBar_center_text);
        title.setText("首页");
        ArrayList<String> list = new ArrayList<String>();
        list.add("首页测试");
        list.add("首页测试");
        list.add("首页测试");
        list.add("首页测试");
        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), list);
        //ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.order_item, R.id.shop_name, list);
        lv.setAdapter(orderAdapter);
    }

}
