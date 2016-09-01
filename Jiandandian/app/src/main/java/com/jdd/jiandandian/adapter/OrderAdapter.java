package com.jdd.jiandandian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jdd.jiandandian.R;

import java.util.List;

/**
 * Created by David on 2016/7/18.
 */
public class OrderAdapter extends BaseAdapter {

    private List<String> list;
    private LayoutInflater layoutInflater;
    private Context context;


    public OrderAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.order_item, null);
        }
        TextView shop_name = (TextView) convertView.findViewById(R.id.shop_name);
        //评价
        //TextView evaluate_status = (TextView) convertView.findViewById(R.id.evaluate_status);
        shop_name.setText(list.get(position));
        //evaluate_status.setText(list.get(position));
        return convertView;
    }
}
