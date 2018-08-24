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

public class MoonActivity extends Activity {
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
                Intent intent = new Intent(MoonActivity.this, OnActivityResultActivity.class);
                String passString = "月球来的消息:Hello,我是月球的Lucy,非常欢迎你来月球";
                intent.putExtra("FromMoon", passString);
                intent.putExtra("bt_click", "backEarth");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(EarthMessage);

        Button finishBt = (Button)findViewById(R.id.finish_activity);
        finishBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
