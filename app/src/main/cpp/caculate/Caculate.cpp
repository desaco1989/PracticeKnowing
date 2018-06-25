//
// Created by desaco on 2018/5/29.
//javah -jni com.desaco.practiceknowing.native_method.JniInvokeJava
//
# include "com_desaco_practiceknowing_native_method_Caculate.h";
#include <jni.h>

#include "../c_caculate/common.h"
#include <android/log.h>

#define LOG_TAG "desaco"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

point_params params;//定义全局的数据结构struct

void setV() {

    params.x = 100;
    params.y = 200;

//    point_params *c;
//    c->x = 10;
//    c->y = 20;
}
/*定义*/
//point_params c;
/*声明*/
//extern point_params c;

/*定义*/
//struct test *c;
/*声明*/
//extern struct test *c;

/*成员赋值*/
//c->a = 1;
//c->b = 2;

void getV() {
    point_params params2;
    LOGD("Caculate Result = %d", params.x);
    LOGD("Caculate Result = %d", params.y);
}

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_add
        (JNIEnv *env, jobject obj, jint num1, jint num2) {

    setV();
    getV();

    return num2 + num1;
}

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_subtract
        (JNIEnv *env, jobject obj, jint num1, jint num2) {
    return num2 - num1;
}

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_multiply
        (JNIEnv *env, jobject obj, jint num1, jint num2) {
    return num2 * num1;

}

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_divide
        (JNIEnv *env, jobject obj, jint num1, jint num2) {
    return num2 / num1;
}

void getPoint(){
    // C/C++【初级】之一 指针(*)、取地址(&)、解引用(*)与引用(&)的概念
    int val3 = 100;
    int &value3 = val3;   // 定义一个【引用】
    int *ptr3 = &val3;   // 定义一个【指针】
    int *&refp3 = ptr3;    //将一个【指针引用】和一个指针绑定起来，那么refp就是一个【指针引用 】
}