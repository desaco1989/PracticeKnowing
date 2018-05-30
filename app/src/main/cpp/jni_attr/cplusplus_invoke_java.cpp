//
// Created by desaco on 2018/5/30.
//cd app/src/main/java
//javah -jni com.desaco.practiceknowing.native_method.JniInvokeJava
//

# include "com_desaco_practiceknowing_native_method_JniInvokeJava.h";
#include <jni.h>

#include<android/log.h>
#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

jobject getInstance(JNIEnv* env, jclass obj_class)
{
    jmethodID construction_id = env->GetMethodID(obj_class, "<init>", "()V");
    jobject obj = env->NewObject(obj_class, construction_id);
    return obj;
}

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_JniInvokeJava_reflect
        (JNIEnv *env, jobject obj){
    //1.获取对应的class
    //   jclass      (*FindClass)(JNIEnv*, const char*);  com\desaco\practiceknowing
    LOGI("JniInvokeJava enter");
    jclass clazz = (env)->FindClass( "com/desaco/practiceknowing/MainActivity");//MainActivity
    jobject order = getInstance(env, clazz);
    LOGI("JniInvokeJava clazz");
    //2.获取对应的方法
    //c语言是获取方法的id
    //   jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID jmID = (env)->GetMethodID(clazz, "showToastMsg",
                                         "(Ljava/lang/String;)V");
    LOGI("JniInvokeJava jmID");
    //3.执行方法
    // void (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
    //string  "这是c反射调用java方法"
    (env)->CallVoidMethod(order, jmID,
                           (env)->NewStringUTF("这是c反射调用java方法" ));
    LOGI("JniInvokeJava CallVoidMethod");
}
