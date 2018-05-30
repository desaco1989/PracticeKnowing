//
// Created by desaco on 2018/5/29.
//javah -jni com.desaco.practiceknowing.native_method.JniInvokeJava
//
# include "com_desaco_practiceknowing_native_method_Caculate.h";
#include <jni.h>

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_add
        (JNIEnv *env,  jobject obj,jint num1, jint num2){
    return num2 + num1;
}

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_subtract
        (JNIEnv *env, jobject obj, jint num1, jint num2){
    return num2 - num1;
}

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_multiply
        (JNIEnv *env, jobject obj, jint num1, jint num2){
    return num2 *  num1;

}

JNIEXPORT jint JNICALL Java_com_desaco_practiceknowing_native_1method_Caculate_divide
        (JNIEnv *env, jobject obj, jint num1, jint num2){
    return num2 /  num1;
}