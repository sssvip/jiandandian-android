package com.jdd.jiandandian.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.jdd.jiandandian.R;
import com.jdd.jiandandian.util.LogUtil;

/**
 * Created by David on 2016/8/6.
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {
    View refreshView;// 顶部布局文件；
    int refreshView_height = 0;
    Context context = null;
    RefreshBean refreshBean;

    public MyListView(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        refreshView = layoutInflater.inflate(R.layout.refresh, null);
        if (refreshBean == null) {
            refreshBean=new RefreshBean();
            refreshBean.refresh_notice = (TextView) refreshView.findViewById(R.id.refresh_notice);
            refreshBean.refresh_last_update_time = (TextView) refreshView.findViewById(R.id.refresh_last_updatetime);
        }
        //获取控件大小
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        refreshView.measure(w, h);
        refreshView_height = refreshView.getMeasuredHeight();
        int width = refreshView.getMeasuredWidth();
        this.setPadding(0, -refreshView_height, 0, 0);
        this.addHeaderView(refreshView);

    }

    /**
     * 设置header 布局 上边距；
     *
     * @param topPadding
     */
    private void topPadding(float topPadding) {
        refreshView.setPadding(refreshView.getPaddingLeft(), (int) topPadding,
                refreshView.getPaddingRight(), refreshView.getPaddingBottom());
        //refreshView.invalidate();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    int isFirst = 0;
    float startY = 0;
    float temp_height = 0;
    int scale = 5;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                LogUtil.i(startY + "");

                break;
            case MotionEvent.ACTION_MOVE:
                temp_height = ev.getY();
                float temp_diff = temp_height - startY;
                //下拉30 refresh下移===30/scale
                if (temp_diff>2.5*refreshView_height) {
                    refreshBean.refresh_notice.setText("已经刷新，松手显示！！");
                }
                if (temp_diff < scale * refreshView_height) {
                    topPadding((temp_height - startY) / 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                topPadding(0);
                refreshBean.refresh_notice.setText("下拉刷新！！");
                break;
        }
        return super.onTouchEvent(ev);
    }

    class RefreshBean {
        TextView refresh_notice;
        TextView refresh_last_update_time;
    }

}
