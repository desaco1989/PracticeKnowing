package com.desaco.practiceknowing.task_three;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.FrameLayout;

import com.desaco.practiceknowing.R;

import java.io.IOException;

/**
 * Created by desaco on 2018/4/18.
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/1213/2153.html
 * TextureView 来预览 Camera 数据
 */

public class TextureViewActivity extends Activity implements TextureView.SurfaceTextureListener {
    private TextureView myTexture;
    private Camera mCamera;

    //com.desaco.practiceknowing.task_three.TextureViewActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textureview_camera);
//        myTexture = new TextureView(this);
        myTexture = (TextureView) findViewById(R.id.textureView);
        myTexture.setSurfaceTextureListener(this);
//        setContentView(myTexture);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture arg0, int arg1,
                                          int arg2) {
        mCamera = Camera.open();
        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

        // (previewSize.width, previewSize.height, Gravity.CENTER)
//        myTexture.setLayoutParams(new FrameLayout.LayoutParams(
//                720, 1920, Gravity.CENTER));

        try {
            mCamera.setPreviewTexture(arg0);
        } catch (IOException t) {
        }
        mCamera.startPreview();
        myTexture.setAlpha(1.0f);
        myTexture.setRotation(90.0f);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,
                                            int arg2) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
        // TODO Auto-generated method stub
    }
}
