package com.desaco.practiceknowing.activity.activity_4_fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.desaco.practiceknowing.R;

import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;

/**
 * Created by desaco on 2018/10/19.
 */

public class MyV4Fragment extends Fragment {

    public static MyV4Fragment getInstance(String msg) {
        MyV4Fragment fragment = new MyV4Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("DATA", msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.my_fragment, null);

        Bundle bundle = getArguments();
        String data = bundle.getString("DATA");
        TextView showTv = (TextView) myView.findViewById(R.id.show_tv);
        showTv.setText(data);
        Log.e("desaco", "MyV4Fragment onCreateView");

        //
        SpannableString spannableString1 = new SpannableString("身份:表情,电视购物 萨嘎，嘎嘎而 噶噶关闭安管部阿热感二胡版安放的噶尔gear回合肥 发发发发发发  发发发有营养UU骨干一样");
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_back);
        //设置图片的尺寸
        drawable.setBounds(0, 0, 42, 50);
        ImageSpan imageSpan = new ImageSpan(drawable);
        //“表情”是占位置的，图片会把其替换掉，3和5是其索引位置，含头不含尾
//        spannableString1.setSpan(imageSpan, 3, 5 , SPAN_INCLUSIVE_EXCLUSIVE);
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString1.setSpan(imageSpan, spannableString1.length()-2, spannableString1.length(), SPAN_INCLUSIVE_EXCLUSIVE);
        TextView showTv2 = (TextView) myView.findViewById(R.id.show_tv2);
        showTv2.setText(spannableString1);

        return myView;
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(getContext(), "子线程显示", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }
}
