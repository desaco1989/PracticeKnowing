package com.desaco.practiceknowing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/4/16.
 */

public class VideoSubFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);

        initView(view);
        initData();

        return view;

    }
    private void initView(View view){
        TextView showTv = (TextView)view.findViewById(R.id.show_tv);
        showTv.setText("second fragment 222.");
        //excute_runnable
        Button excuteBt = (Button)view.findViewById(R.id.excute_runnable);
        excuteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData(){

    }
}
