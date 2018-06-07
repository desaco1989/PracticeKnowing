package com.desaco.practiceknowing.tab_architecture;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desaco.practiceknowing.R;

/**
 * TODO App主页下面的四个Tab
 */
public class FourTabActivity extends FragmentActivity implements View.OnClickListener {
    //

    private FragmentManager fragmentManager;

    private FirstNavFragment mFirstFragment;
    private SecondNavFragment mSecondFragment;
    private ThirdNavFragment mThirdFragment;
    private ForthNavFragment mForthFragment;

    private Context mContext;

    private LinearLayout mContentLayout;

    private LinearLayout mFirstNavLayout;
    private ImageView mFirstNavImgv;
    private TextView mFirstNavTv;

    private LinearLayout mSecondNavLayout;
    private ImageView mSecondNavImgv;
    private TextView mSecondNavTv;

    private LinearLayout mThirdNavLayout;
    private ImageView mThirdNavImgv;
    private TextView mThirdNavTv;

    private LinearLayout mForthNavLayout;
    private ImageView mForthNavImgv;
    private TextView mForthNavTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_tab);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        //rl_content
        mFirstNavLayout = (LinearLayout) findViewById(R.id.ll_first);
        mFirstNavLayout.setOnClickListener(this);
        mFirstNavImgv = (ImageView) findViewById(R.id.iv_first);
        mFirstNavTv = (TextView) findViewById(R.id.tv_first);

        mSecondNavLayout = (LinearLayout) findViewById(R.id.ll_video);
        mSecondNavLayout.setOnClickListener(this);
        mSecondNavImgv = (ImageView) findViewById(R.id.iv_video);
        mSecondNavTv = (TextView) findViewById(R.id.tv_video);

        mThirdNavLayout = (LinearLayout) findViewById(R.id.ll_find);
        mThirdNavLayout.setOnClickListener(this);
        mThirdNavImgv = (ImageView) findViewById(R.id.iv_find);
        mThirdNavTv = (TextView) findViewById(R.id.tv_find);

        mForthNavLayout = (LinearLayout) findViewById(R.id.ll_mine);
        mForthNavLayout.setOnClickListener(this);
        mForthNavImgv = (ImageView) findViewById(R.id.iv_mine);
        mForthNavTv = (TextView) findViewById(R.id.tv_mine);
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
        showFragment(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_first:
                showFragment(0);
                break;
            case R.id.ll_video:
                showFragment(1);
                break;
            case R.id.ll_find:
                showFragment(2);
                break;
            case R.id.ll_mine:
                showFragment(3);
                break;
        }
    }

    public void showFragment(int position) {
        freshIcon(position);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (position) {
            case 0:
                if (mFirstFragment == null) {
                    mFirstFragment = new FirstNavFragment();
                    transaction.add(R.id.rl_content, mFirstFragment);
                } else {
                    transaction.show(mFirstFragment);
//                    firstFragment.notifyDataSetChanged();
                }
//
//                StatServiceUtils.getInstance().customEventStat(this, "click", "FirstFragment_首页tab");
                break;
            case 1:
                if (mSecondFragment == null) {
                    mSecondFragment = new SecondNavFragment();
                    transaction.add(R.id.rl_content, mSecondFragment);
                } else {
                    transaction.show(mSecondFragment);
//                    mSecondFragment.notifyTitleBackground();
                }
//
//                StatServiceUtils.getInstance().customEventStat(this, "click", "VideoFragment_视屏tab");
                break;
            case 2:
                if (mThirdFragment == null) {
                    mThirdFragment = new ThirdNavFragment();
                    transaction.add(R.id.rl_content, mThirdFragment);
                } else {
                    transaction.show(mThirdFragment);
                }
//
//                StatServiceUtils.getInstance().customEventStat(this, "click", "FindFragment_服务tab");
                break;
            case 3:
                if (mForthFragment == null) {
                    mForthFragment = new ForthNavFragment();
                    transaction.add(R.id.rl_content, mForthFragment);
                } else {
                    transaction.show(mForthFragment);
                }

//                StatServiceUtils.getInstance().customEventStat(this, "click", "MineFragment_我tab");
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();

    }

    public void hideFragments(FragmentTransaction transaction) {

        if (mFirstFragment != null) {
            transaction.hide(mFirstFragment);
        }
        if (mSecondFragment != null) {
            transaction.hide(mSecondFragment);
        }
        if (mThirdFragment != null) {
            transaction.hide(mThirdFragment);
        }
        if (mForthFragment != null) {
            transaction.hide(mForthFragment);
        }
    }

    public void freshIcon(int position) {
//        if (PreferencesManager.getInstance(mContext).get(ConstantAttr.isDayNight, false)) {
//            ivFirst.setBackgroundResource(R.mipmap.icon_tab_shouye_normal_night);
//            ivVideo.setBackgroundResource(R.mipmap.icon_tab_shipin_normal_night);
//            ivFind.setBackgroundResource(R.mipmap.icon_tab_faxian_normal_night);
//            ivMine.setBackgroundResource(R.mipmap.icon_tab_wo_normal_night);
//        } else {
//            ivFirst.setBackgroundResource(R.mipmap.icon_tab_shouye_normal);
//            ivVideo.setBackgroundResource(R.mipmap.icon_tab_shipin_normal);
//            ivFind.setBackgroundResource(R.mipmap.icon_tab_faxian_normal);
//            ivMine.setBackgroundResource(R.mipmap.icon_tab_wo_normal);
//        }
//        tvFirst.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));
//        tvVideo.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));
//        tvFind.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));
//        tvMine.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));

        mFirstNavImgv.setBackgroundResource(R.mipmap.icon_tab_shouye_normal);
        mSecondNavImgv.setBackgroundResource(R.mipmap.icon_tab_shipin_normal);
        mThirdNavImgv.setBackgroundResource(R.mipmap.icon_tab_faxian_normal);
        mForthNavImgv.setBackgroundResource(R.mipmap.icon_tab_wo_normal);

        mFirstNavTv.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));
        mSecondNavTv.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));
        mThirdNavTv.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));
        mForthNavTv.setTextColor(mContext.getResources().getColor(R.color.gray_999ea4));

        if (position == 0) {
            mFirstNavImgv.setBackgroundResource(R.mipmap.icon_tab_shouye_pressed);
            mFirstNavTv.setTextColor(mContext.getResources().getColor(R.color.color_d32f24));
        } else if (position == 1) {
            mSecondNavImgv.setBackgroundResource(R.mipmap.icon_tab_shipin_pressed);
            mSecondNavTv.setTextColor(mContext.getResources().getColor(R.color.color_d32f24));
        } else if (position == 2) {
            mThirdNavImgv.setBackgroundResource(R.mipmap.icon_tab_faxian_pressed);
            mThirdNavTv.setTextColor(mContext.getResources().getColor(R.color.color_d32f24));
        } else if (position == 3) {
            mForthNavImgv.setBackgroundResource(R.mipmap.icon_tab_wo_pressed);
            mForthNavTv.setTextColor(mContext.getResources().getColor(R.color.color_d32f24));
        }
    }

}
