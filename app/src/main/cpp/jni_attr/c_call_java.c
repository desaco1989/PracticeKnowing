// Created by desaco on 2018/5/30.
//cd app/src/main/java
//javah -jni com.desaco.practiceknowing.native_method.JniInvokeJava
//
#include <jni.h>
#include <android/log.h>

#define LOG_TAG "desaco"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_callbackmethod
        (JNIEnv *env, jobject obj) {
    //先获取字节码对象  jclass      (*FindClass)(JNIEnv*, const char*);
    jclass jclazz = (*env)->FindClass(env, "com/desaco/practiceknowing/native_method/CcallJava");
    //获取method对象   jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID methodID = (*env)->GetMethodID(env, jclazz, "helloFromJava", "()V");
    //通过字节码文件创建一个object对象（可选，方法中已经传递一个object），如果需要调用的方法与本地方法不在同一个文件夹则需要新创建object
    //jobject     (*AllocObject)(JNIEnv*, jclass);
    //通过对象调用方法 (调用java的是空方法，不用填写参数) void   (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
    (*env)->CallVoidMethod(env, obj, methodID);
}

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_callbackIntmethod
        (JNIEnv *env, jobject obj) {
    //先获取字节码对象  jclass      (*FindClass)(JNIEnv*, const char*);
    jclass clzz = (*env)->FindClass(env, "com/desaco/practiceknowing/native_method/CcallJava");
    //获取method对象   jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID methodID = (*env)->GetMethodID(env, clzz, "add", "(II)I");

    int result = (*env)->CallIntMethod(env, obj, methodID, 3, 4);
    //logcat 打印相加返回的结果
    LOGD("RESLUT = %d", result);

}

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_callbackStringmethod
        (JNIEnv *env, jobject obj) {
    //先获取字节码对象  jclass      (*FindClass)(JNIEnv*, const char*);
    jclass clzz = (*env)->FindClass(env, "com/desaco/practiceknowing/native_method/CcallJava");
    //获取method对象   jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID methodID = (*env)->GetMethodID(env, clzz, "printString", "(Ljava/lang/String;)V");
    //将要传递的字符串先转换成jstring类型 ，然后在传递给java方法
    int result = (*env)->NewStringUTF(env, "hello form C/C++ ");
    (*env)->CallVoidMethod(env, obj, methodID, result);
}

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_callStaticmethod
        (JNIEnv *env, jobject obj) {

    jclass clzz = (*env)->FindClass(env, "com/desaco/practiceknowing/native_method/CcallJava");
    jmethodID methodID = (*env)->GetStaticMethodID(env, clzz, "staticmethod",
                                                   "(Ljava/lang/String;)V");

    jstring str = (*env)->NewStringUTF(env, "C调用java");
    (*env)->CallStaticVoidMethod(env, clzz, methodID, str);
}