package com.desaco.practiceknowing.banner;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.desaco.practiceknowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/6/6.
 * <p>
 * RecyclerView可以添加头部和尾部: https://github.com/Bigkoo/Android-ConvenientBanner
 */

public class RecyclerViewWithHeaderFootActivity extends Activity {
    //com.desaco.practiceknowing.banner.RecyclerViewWithHeaderFootActivity

    private MyAdapter adapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_header_footer);
        initView();
    }

    /**
     * 简单的初始化RecycerView，以及设置适配器
     */
    private void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.list_recycler);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<String>();
        for(int i=0;i<=10;i++){
            data.add("-- 内容,"+i);
        }
        adapter = new MyAdapter(data, this);
        mRecyclerView.setAdapter(adapter);

        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer_layout, null));
        adapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_header_layout, null));
    }


}
