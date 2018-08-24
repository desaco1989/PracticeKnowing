package com.desaco.practiceknowing.activity.start_activity_for_result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/8/24.
 */

public class MarsActivity extends Activity {
    private Button button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moon_mar_activity);
        Intent EarthIntent = getIntent();
        String EarthMessage = EarthIntent.getStringExtra("FromEarth");
        button = (Button) findViewById(R.id.button);
        button.setText("返回地球");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarsActivity.this, OnActivityResultActivity.class);
                String passString = "火星来的消息:Hello,我是火星的Jack，非常高兴你能来火星";
                intent.putExtra("FromMars", passString);
                intent.putExtra("bt_click", "backEarth");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(EarthMessage);

        Button finishBt = (Button) findViewById(R.id.finish_activity);
        finishBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
