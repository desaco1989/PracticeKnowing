package com.desaco.practiceknowing.activity.start_activity_for_result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/8/24.
 */

//
public class OnActivityResultActivity extends Activity {
    private Button button = null;
    private Button button1 = null;
    private TextView text = null;
    private static final int Mars = 0;
    private static final int Moon = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onactivityresult);
        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnActivityResultActivity.this, MarsActivity.class);
                String content = "地球来的消息:你好，我是来自地球上的小老鼠。我好想去你们的火星呀";
                intent.putExtra("FromEarth", content);
                startActivityForResult(intent, Mars);
            }
        });
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnActivityResultActivity.this, MoonActivity.class);
                String content = "地球来的消息:你好，我是来自地球上的小老鼠。我好想去你们月球";
                intent.putExtra("FromEarth", content);
                startActivityForResult(intent, Moon);
            }
        });
    }

    //MoonActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)//重写onActivityResult方法
    {
        if (data != null && data.getExtras() != null) {
            if("backEarth".equals(data.getExtras().getString("bt_click",""))){
                switch (requestCode) {
                    case Mars:
                        Log.e("desaco", "从 Mars 返回地球");
                        Bundle MarsBuddle = data.getExtras();
                        String MarsMessage = MarsBuddle.getString("FromMars");
                        text.setText(MarsMessage);
                        break;
                    case Moon:
                        Log.e("desaco", "从 Moon 返回地球");
                        Bundle MoonBuddle = data.getExtras();
                        String MoonMessage = MoonBuddle.getString("FromMoon");
                        text.setText(MoonMessage);
                        break;
                    default:
                        break;
                }
            }else{}
        }else{

        }

    }
}
