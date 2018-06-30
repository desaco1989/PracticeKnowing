package com.desaco.practiceknowing.animation;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.desaco.practiceknowing.R;

/**
 * Created by desaco on 2018/6/29.
 * https://blog.csdn.net/qq_23077365/article/details/51697621
 * <p>
 * imageView一定设置 scaleType = “matrix”
 * matrix 作用与 imageView的src
 */

public class MatrixAnimatorActivity extends Activity implements View.OnClickListener {

    EditText etRotate;
    EditText etScale;
    EditText etTransX;
    EditText etTransY;

    Button btnRotate;
    Button btnScale;
    Button btnTrans;
    Button btnBack;

    ImageView image;
    private Matrix matrix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_animator);

        initView();
        initData();
    }

    private void initView() {
        etRotate = (EditText) findViewById(R.id.et_rotate);
        etScale = (EditText) findViewById(R.id.et_scale);
        etTransX = (EditText) findViewById(R.id.et_transX);
        etTransY = (EditText) findViewById(R.id.et_transY);

        btnRotate = (Button) findViewById(R.id.btn_rotate);
        btnRotate.setOnClickListener(this);

        btnScale = (Button) findViewById(R.id.btn_scale);
        btnScale.setOnClickListener(this);

        btnTrans = (Button) findViewById(R.id.btn_trans);
        btnTrans.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        image = (ImageView) findViewById(R.id.image);
    }

    private void initData() {
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_rotate:
                if (matrix == null) {
                    matrix = new Matrix();
                }
                float rotate = Float.parseFloat(etRotate.getText().toString());
                matrix.postRotate(rotate);
                image.setImageMatrix(matrix);
                break;
            case R.id.btn_scale:
                if (matrix == null) {
                    matrix = new Matrix();
                }
                float scale = Float.parseFloat(etScale.getText().toString());
                matrix.postScale(scale, scale);
                image.setImageMatrix(matrix);
                break;
            case R.id.btn_trans:
                if (matrix == null) {
                    matrix = new Matrix();
                }
                float transX = Float.parseFloat(etTransX.getText().toString());
                float transY = Float.parseFloat(etTransY.getText().toString());
                matrix.postTranslate(transX, transY);
                image.setImageMatrix(matrix);
                break;
            case R.id.btn_back:
                if (matrix == null) {
                    matrix = new Matrix();
                }
                matrix.reset();
                image.setImageMatrix(matrix);
                break;
        }
    }

}
