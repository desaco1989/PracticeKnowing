package com.desaco.practiceknowing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/10/18.
 */

public class TestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_test_layout  activity_layout
        setContentView(R.layout.activity_test_layout);

    }



}
