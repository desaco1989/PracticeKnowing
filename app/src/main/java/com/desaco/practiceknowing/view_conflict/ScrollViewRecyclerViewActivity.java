package com.desaco.practiceknowing.view_conflict;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.desaco.practiceknowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/5/23.
 * 类似 HeaderView 的部分 + RecyclerView列表部分，布局是垂直方向
 * 场景是：有一部分原生的控件，( >=)占满一整屏，然后下面放一个RecyclerView列表;即使加了ScrollView，也会卡顿
 * <p>
 * https://blog.csdn.net/coralline_xss/article/details/72887136
 */

public class ScrollViewRecyclerViewActivity extends Activity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_recyclerview);
        mContext = this;

        initView();
        initData1();
//        initData2();
    }

    RecyclerAdapter adapter;

    private void initData1() {
//        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        //最直接的方式是将布局管理器中判断可滑动的方法，直接返回false
        LinearLayoutManager manager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };

        manager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new RecyclerAdapter(mContext);
        adapter.setOnClickEvent(new RecyclerAdapter.IClickEvent() {
            @Override
            public void onClickWhere(SimpleBean bean, String clickType) {
                bean.setSubScribe(true);
                Log.e("desaco", "Position=" + bean.getPosition());
                adapter.updateItem(bean.getPosition(), bean);

            }
        });
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

        adapter.directPutList(list);
    }

    private void initData2() {
        LinearLayoutManager manager2 = new LinearLayoutManager(mContext);
        manager2.setOrientation(OrientationHelper.VERTICAL);
        rv2.setLayoutManager(manager2);
        RecyclerAdapter adapter2 = new RecyclerAdapter(mContext);
        rv2.setAdapter(adapter2);

        adapter2.directPutList(list);
    }

    RecyclerView rv;
    RecyclerView rv2;
    List<SimpleBean> list;

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rcycler_scroll);
        //
//        rv2 = (RecyclerView) findViewById(R.id.rcycler_scroll2);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            SimpleBean bean = new SimpleBean();
            if (i % 2 == 0) {
                bean.setSubScribe(true);//已订阅
            } else {
                bean.setSubScribe(false);
            }
            bean.setTitle(i + ",,,标题--");
            bean.setMsg(i + ";;;消息内容====");
            list.add(bean);
        }
    }
}
