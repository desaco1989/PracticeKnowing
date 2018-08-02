package com.desaco.practiceknowing.view_event_dispatch;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/8/2.
 * <p>
 * View属性动画在Y的平移
 *
 * https://blog.csdn.net/airsaid/article/details/52074566
 */

public class CustomViewGroupEventActivity extends Activity {

    //com.desaco.practiceknowing.view_event_dispatch.CustomViewGroupEventActivity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewgroup_event);

        inttView();
        initData(); //<!--iv1  ImageView-->
    }

    private RelativeLayout headerLayout;
    private RelativeLayout headerLayout2;

    private void inttView() {
//        <!--RelativeLayout header_view_layout-->
        RelativeLayout headerLayout = (RelativeLayout) findViewById(R.id.header_view_layout);
        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        //
        headerLayout2 = (RelativeLayout) findViewById(R.id.header_view_layout2);
//        headerLayout2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        headerLayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                playY();
                return true;
            }
        });


//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
//        layoutParams.leftMargin = 50;
//        textView.requestLayout();

        image1 = (ImageView)findViewById(R.id.image1);
    }
    private ImageView image1;
    private void play() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(headerLayout, "rotationX", 0, 360),//绕X轴翻转
                ObjectAnimator.ofFloat(headerLayout, "rotationY", 0, 180),//绕Y轴旋转
                ObjectAnimator.ofFloat(headerLayout, "rotation", 0, -90),//绕中心点逆时针旋转
                ObjectAnimator.ofFloat(headerLayout, "translationX", 0, 90),//X轴平移
                ObjectAnimator.ofFloat(headerLayout, "translationY", 0, 90),//y轴平移
                ObjectAnimator.ofFloat(headerLayout, "scaleX", 1, 1.5f),//X轴拉伸
                ObjectAnimator.ofFloat(headerLayout, "scaleY", 0, 0.5f),//Y轴从零拉伸
                ObjectAnimator.ofFloat(headerLayout, "alpha", 1, 0.25f, 1)//透明度
        );
        set.setDuration(2 * 1000).start();//时间
    }

    private void playY() {
//        ObjectAnimator translationX = new ObjectAnimator().ofFloat(headerLayout, "translationX", 0, 600f);
//        ObjectAnimator translationY = new ObjectAnimator().ofFloat(headerLayout, "translationY", 0, 0);
//        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
//        animatorSet.playTogether(translationX, translationY); //设置动画
//        animatorSet.setDuration(1000);  //设置动画时间
//        animatorSet.start(); //启动

        ObjectAnimator anim = ObjectAnimator.ofFloat(image1, "alpha", 1f, 0.1f, 1f, 0.5f, 1f);
        anim.setDuration(5000);// 动画持续时间
        anim.start();
    }
    //<!--iv1  ImageView-->
    private ImageView propertyIv;
    private void initData(){
        propertyIv = (ImageView)findViewById(R.id.iv1);
        propertyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propertyPlay(propertyIv);
            }
        });

    }

    private void propertyPlay(View view){
//        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360f);
//        rotateAnim.setDuration(3000);
//        rotateAnim.start();

//        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(view, "translationX",
//                view.getTranslationX(), -200.0f, view.getTranslationX());

        //ObjectAnimator.ofFloat(tv, “translationY”, 0, 200, -100,0)表示首先从0移动到正方向200的位置，然后再移动到负方向100的位置，最后移动到原点。
        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(view, "translationY",
                0, -50.0f,-200, -400);//view.getTranslationY(), -200.0f, view.getTranslationY());
        moveAnim.setDuration(3000);
        moveAnim.start();
    }
}
