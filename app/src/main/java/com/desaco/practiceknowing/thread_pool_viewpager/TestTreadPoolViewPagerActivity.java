package com.desaco.practiceknowing.thread_pool_viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.desaco.practiceknowing.R;
import com.desaco.practiceknowing.fragment.VideoSubFragment1;
import com.desaco.practiceknowing.fragment.VideoSubFragment2;
import com.desaco.practiceknowing.fragment.VideoSubFragment3;

import java.util.ArrayList;

/**
 * Created by desaco on 2018/5/9.
 */

public class TestTreadPoolViewPagerActivity extends FragmentActivity {
    //com.desaco.practiceknowing.thread_pool.TestTreadPoolViewPagerActivity

    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);

        initView();
        initData();
    }
    private void initView(){
        //viewpager
        viewPager = (ViewPager)findViewById(R.id.viewpager);
    }

    public void initData() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new VideoSubFragment1());
        fragments.add(new VideoSubFragment2());
        fragments.add(new VideoSubFragment3());
//        FragmentManager manager = getSupportFragmentManager();
        vpAdapter = new VpAdapter(getSupportFragmentManager() , fragments);//getChildFragmentManager()   getFragmentManager() getSupportFragmentManager()
        viewPager.setAdapter(vpAdapter);
        viewPager.setOffscreenPageLimit(2);//设置缓存fragment个数//TODO
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private class VpAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments;

        public VpAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
