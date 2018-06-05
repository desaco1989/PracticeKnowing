//
// Created by desaco on 2018/6/5.
//
#include <jni.h>
#include <String.h>
#include<stdlib.h>
#include<malloc.h>

//把java的字符串转换成c的字符串,使用反射
char *Jstring2CStr(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    //1:先找到字节码文件
    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "UTF-8");//GB2312
    //2:通过字节码文件找到方法ID
    jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
    //3:通过方法id，调用方法
    jbyteArray barr = (jbyteArray) (*env)->CallObjectMethod(env, jstr, mid,
                                                            strencode); // String .getByte("GB2312");
    //4:得到数据的长度
    jsize alen = (*env)->GetArrayLength(env, barr);
    //5：得到数据的首地址
    jbyte *ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
    //6:得到C语言的字符串
    if (alen > 0) {
        rtn = (char *) malloc(alen + 1);         //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    (*env)->ReleaseByteArrayElements(env, barr, ba, 0);  //
    return rtn;
}

/*
 * Class:     com_demo_passwd_MainActivity
 * Method:    encodeFromC
 * Signature: (Ljava/lang/String;I)Ljava/lang/String;
 * //

 */
JNIEXPORT jstring JNICALL
Java_com_desaco_practiceknowing_native_1method_NativeEncryptDecode_encodeWithC
        (JNIEnv *env, jobject obj, jstring passwd, jint length) {
    //1:将java的字符串转化为c语言
    char *cstr = Jstring2CStr(env, passwd);
    int i = 0;
    //2:给C语言字符加1
    for (i = 0; i < length; i++) {
        *(cstr + i) += 1;
    }
    //3:将c语言字符串转化为java字符串
    return (*env)->NewStringUTF(env, cstr);
}

/*
 * Class:     com_demo_passwd_MainActivity
 * Method:    decodeFromC
 * Signature: (Ljava/lang/String;I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_com_desaco_practiceknowing_native_1method_NativeEncryptDecode_decodeWithC
        (JNIEnv *env, jobject obj, jstring passwd, jint length) {
    //1:将java的字符串转化为c语言
    char *cstr = Jstring2CStr(env, passwd);
    int i = 0;
    //2:给C语言字符减1
    for (i = 0; i < length; i++) {
        *(cstr + i) -= 1;
    }
    //3:将c语言字符串转化为java字符串
    return (*env)->NewStringUTF(env, cstr);
}
