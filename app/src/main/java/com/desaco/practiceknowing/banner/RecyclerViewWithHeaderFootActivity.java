package com.desaco.practiceknowing.banner;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.desaco.practiceknowing.R;
import com.desaco.practiceknowing.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/6/6.
 * <p>
 * RecyclerView可以添加头部和尾部: https://github.com/Bigkoo/Android-ConvenientBanner
 * 监听RecyclerView的滚动
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
        mRecyclerView = (RecyclerView) findViewById(R.id.list_recycler);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        List<String> data = new ArrayList<String>();
        for (int i = 0; i <= 10; i++) {
            data.add("-- 内容," + i);
        }
        adapter = new MyAdapter(data, this);
        mRecyclerView.setAdapter(adapter);

        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer_layout, null));
        adapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_header_layout, null));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == recyclerView.SCROLL_STATE_IDLE) {//正在滚动

                } else if (newState == recyclerView.SCROLL_STATE_DRAGGING) {//正在被外部拖拽,一般为用户正在用手指滚动

                } else if (newState == recyclerView.SCROLL_STATE_SETTLING) {//自动滚动开始

                }

                LogUtil.e("desaco", "recyclerView dy=");
            }

            //TODO recyclerView添加一个滑动监听器
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LogUtil.e("desaco", "recyclerView dy=" + dy);

//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                adapterNowPos = l.findFirstVisibleItemPosition();
//                allItems = l.getItemCount();
//                String s = adapterNowPos + 1 + "/" + allItems;
//                comic_pic_state.setText(s);//设置图片的数量
//                pic_state_top.setText(s);


            }
        });
    }

    //正在滚动
    public static final int SCROLL_STATE_IDLE = 0;

    //正在被外部拖拽,一般为用户正在用手指滚动
    public static final int SCROLL_STATE_DRAGGING = 1;

    //自动滚动开始
    public static final int SCROLL_STATE_SETTLING = 2;

}
