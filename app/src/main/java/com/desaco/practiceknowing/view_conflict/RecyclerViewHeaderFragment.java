package com.desaco.practiceknowing.view_conflict;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desaco.practiceknowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/7/23.
 *
 * 添加头部View，并刷新Item
 */

public class RecyclerViewHeaderFragment extends Fragment {

    private Context mContext;
    String videoType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_scrollview_recyclerview, null);
        mContext = getActivity();
        Bundle bundle = getArguments();
//        videoType = bundle.getString("videoType");

        rv = (RecyclerView) v.findViewById(R.id.rcycler_scroll);
        rv.setVisibility(View.VISIBLE);

        initView();
        initData1();
//        initData2();
        return v;
    }

    PgcSubscribeHeaderAdapter adapter;

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
        adapter = new PgcSubscribeHeaderAdapter(getContext(),"");
        adapter.setOnClickEvent(new PgcSubscribeHeaderAdapter.IClickEvent() {
            @Override
            public void onClickWhere(SimpleBean bean, String clickType) {
                bean.setSubScribe(true);
                Log.e("desaco", "Position=" + bean.getPosition());
                adapter.updateItem(bean.getPosition(), bean);

            }
        });
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);

//        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer_layout,null));
        //TODO 添加头部  item_simple   header_layout
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_layout,null);
        adapter.addHeaderView(view);



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
