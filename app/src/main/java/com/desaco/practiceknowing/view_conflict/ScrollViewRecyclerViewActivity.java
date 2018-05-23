package com.desaco.practiceknowing.view_conflict;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.desaco.practiceknowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/5/23.
 */

public class ScrollViewRecyclerViewActivity extends Activity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview_recyclerview);
        mContext = this;

        initView();
    }

    private void initView() {
        RecyclerView rv = (RecyclerView) findViewById(R.id.rcycler_scroll);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rv.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);
        //
        RecyclerAdapter adapter = new RecyclerAdapter(mContext);
        rv.setAdapter(adapter);

        List<SimpleBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SimpleBean bean = new SimpleBean();
            bean.setTitle(i + ".标题--");
            bean.setMsg(i + ".消息内容====");
            list.add(bean);
        }
        adapter.directPutList(list);
    }
}
