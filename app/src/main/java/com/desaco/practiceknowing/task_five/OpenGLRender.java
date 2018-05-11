package com.desaco.practiceknowing.task_five;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by desaco on 2018/4/18.
 */

public class OpenGLRender implements GLSurfaceView.Renderer {

    private float[] mTriangleArray = {
            0f, 1f, 0f,
            -1f, -1f, 0f,
            1f, -1f, 0f
    };
    private FloatBuffer mTriangleBuffer;


    private float[] mColorArray = {
            1f, 0f, 0f, 1f,     //红
            0f, 1f, 0f, 1f,     //绿
            0f, 0f, 1f, 1f      //蓝
    };
    private FloatBuffer mColorBuffer;

    //正方形的四个顶点
    private FloatBuffer quateBuffer;
    private float[] mQuateArray = {
            -1f, -1f, 0f,
            1f, -1f, 0f,
            -1f, 1f, 0f,
            1f, 1f, 0f,
    };

    @Override
    public void onDrawFrame(GL10 gl) {
        // TODO Auto-generated method stub
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //使用数组作为颜色
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);

        //绘制小三角形
        gl.glLoadIdentity();
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);//数组指向三角形顶点buffer
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
//      gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glFinish();

        //绘制正方形
        gl.glLoadIdentity();
        gl.glTranslatef(1.5f, 0.0f, -6.0f);
//      gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, quateBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        gl.glFinish();


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        // TODO Auto-generated method stub
        gl.glViewport(0, 0, w, h);

        float ratio = (float) w / h;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // TODO Auto-generated method stub
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        mTriangleBuffer = BufferUtil.floatToBuffer(mTriangleArray);
        mColorBuffer = BufferUtil.floatToBuffer(mColorArray);
        quateBuffer = BufferUtil.floatToBuffer(mQuateArray);
    }
}
