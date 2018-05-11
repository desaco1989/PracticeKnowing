package com.desaco.practiceknowing.task_five;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by desaco on 2018/4/18.
 * https://blog.csdn.net/yanzi1225627/article/details/30096181/
 * OpenGL 绘制一个三角形
 */

public class DrawingGraphicsActivity extends Activity {
    //.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new OpenGLRender());
        setContentView(glSurfaceView);
        //setContentView(R.layout.activity_main);

    }
}
