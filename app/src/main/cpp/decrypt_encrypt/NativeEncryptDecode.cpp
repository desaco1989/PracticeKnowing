//
// Created by desaco on 2018/5/29.
//

#include <jni.h>
# include "com_desaco_practiceknowing_native_method_NativeEncryptDecode.h";
//#include <stdio.h>

JNIEXPORT jstring JNICALL Java_com_desaco_practiceknowing_native_1method_NativeEncryptDecode_encrypt
        (JNIEnv *env, jobject obj, jstring decodeData){

    return decodeData;

}

JNIEXPORT jstring JNICALL Java_com_desaco_practiceknowing_native_1method_NativeEncryptDecode_decode
        (JNIEnv *env, jobject obj, jstring encryptData){

    return encryptData;
}


