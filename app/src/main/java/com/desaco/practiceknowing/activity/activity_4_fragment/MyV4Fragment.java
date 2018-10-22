package com.desaco.practiceknowing.activity.activity_4_fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.desaco.practiceknowing.R;

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
