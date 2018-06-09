//
// Created by desaco on 2018/6/9.
// https://blog.csdn.net/xinchen200/article/details/25333047
#include <jni.h>
#include <stdio.h>
#include <android/log.h>

#define LOG_TAG "desaco"

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_byteArray2JniM1
        (JNIEnv *env, jobject obj, jbyteArray buffer, jint len) {
    unsigned char array[128];
    //拷贝数组
    (*env)->GetByteArrayRegion(env, buffer, 0, len, array);
    int i = 0;
    for (i = 0; i < 128; i++) {
        array[i] = i;
    }
    LOGD("JniM1 RESLUT = %d",len);
}

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_byteArray2JniM2
        (JNIEnv *env, jobject obj, jbyteArray buffer, jint len) {
    //拷贝数组
    unsigned char *pBuffer = (*env)->GetByteArrayElements(env, buffer, NULL);
    if (pBuffer == NULL) {
        printf("GetBooleanArrayElements failed");
    }
    int i = 0;
    for (i = 0; i < 128; i++) {
        pBuffer[i] = i;
    }
    (*env)->ReleaseByteArrayElements(env, buffer,pBuffer,  0);
    LOGD("JniM2 RESLUT = %d",len);
}

JNIEXPORT jbyteArray JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_jniByte2Java
        (JNIEnv *env, jobject obj) {
    //, jbyteArray buffer, jint len
    unsigned char array[128];
    //拷贝数组
//    (*env)->GetByteArrayRegion(env, buffer, 0, len, array);
    int i = 0;
    for (i = 0; i < 128; i++) {
        array[i] = i;
    }
    //分配byteArray
    jbyteArray jba = (*env)->NewByteArray(env, 128);
    (*env)->SetByteArrayRegion(env, jba, 0, 128, array);
    return jba;
}