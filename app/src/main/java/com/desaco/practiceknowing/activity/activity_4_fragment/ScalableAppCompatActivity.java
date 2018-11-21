package com.desaco.practiceknowing.activity.activity_4_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.desaco.practiceknowing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/10/19.
 *
 * AppCompatActivity
 */
public class ScalableAppCompatActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private MyV4Fragment fragment1;
    private MyV4Fragment fragment2;
    private MyV4Fragment fragment3;
    private List<Fragment> fragmentList;

    private MyViewPagerAdapter mViewPagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fragment_layout);

        TextView tv = (TextView)findViewById(R.id.f_tv);
        tv.setText("我是顶部，ViewPager + Fragment。");

        initViewAndData();
    }

    private void initViewAndData() {
        mViewPager = (ViewPager) findViewById(R.id.test_viewpager);

        fragment1 = MyV4Fragment.getInstance("第111个Fragment");
        fragment2 = MyV4Fragment.getInstance("第222个Fragment");
        fragment3 = MyV4Fragment.getInstance("第333个Fragment");

        fragmentList = new ArrayList<>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

        mViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
    }
}
