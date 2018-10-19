//package com.desaco.practiceknowing.activity.activity_4_fragment;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//
////import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.app.Fragment;
////FragmentManager
//import android.app.FragmentManager;
////FragmentPagerAdapter
//import android.app.Adapter;
//import com.desaco.practiceknowing.R;
//
//import java.util.List;
//
///**
// * Created by desaco on 2018/10/19.
// */
//
////Activity
//public class ScalableActivity extends Activity {
//
//    private ViewPager mViewPager;
//
//    private MyAppFragment fragment1;
//    private MyAppFragment fragment2;
//    private MyAppFragment fragment3;
//    private List<Fragment> fragmentList;
//
//    private MyViewPagerAdapter mViewPagerAdapter;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment_layout);
//
//        initViewAndData();
//
//
//        //
//    }
//    private void initViewAndData(){
//        mViewPager = (ViewPager) findViewById(R.id.test_viewpager);
//
////        fragment1 = MyFragment.getInstance("第111个Fragment");
////        fragment2 = MyFragment.getInstance("第222个Fragment");
////        fragment3 = MyFragment.getInstance("第333个Fragment");
//        fragmentList.add(fragment1);
//        fragmentList.add(fragment2);
//        fragmentList.add(fragment3);
//
//        //Activity
//        android.app.FragmentManager manager = getFragmentManager();
////        FragmenActivity android.support.v4.app.FragmentManager manager1 = getSupportFragmentManager();
//        mViewPagerAdapter = new MyViewPagerAdapter(manager,fragmentList);
//        mViewPager.setAdapter(mViewPagerAdapter);
//    }
//
//    //PagerAdapter FragmentPagerAdapter
//    public class MyViewPagerAdapter extends PagerAdapter {
//        List<Fragment> list;
//
//        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
//            super(fm);
//            this.list = list;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Fragment getItem(int arg0) {
//            return list.get(arg0);
//        }
//    }
//
//}
