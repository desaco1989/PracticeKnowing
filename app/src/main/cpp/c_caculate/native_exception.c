//
// Created by desaco on 2018/6/8.
//https://www.jianshu.com/p/bfd50109d74d
//

#include <jni.h>
#include <stdio.h>

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_testExceptionM1
(JNIEnv *env, jobject obj){
    jclass jcls = (*env)->GetObjectClass(env,obj);
    jfieldID jId = (*env)->GetFieldID(env,jcls, "key1", "Ljava/lang/String;");

    //此处抛出的异常，Java可以通过Throwable来捕获
    jthrowable err = (*env)->ExceptionOccurred(env);
    if (err != NULL){
        //手动清空异常信息，保证Java代码能够继续执行
        (*env)->ExceptionClear(env);
        //提供补救措施，例如获取另外一个属性
        jId = (*env)->GetFieldID(env, jcls, "key", "Ljava/lang/String;");
    }

    printf("C can run , this will print");

    //这里竟然还可以继续执行
//    jstring key =  (*env)->GetObjectField(env, obj, jId);
    //遇到这句话的时候，C程序Crash了
//    char* c_str = (*env)->GetStringUTFChars(env, key, NULL);

    printf("C could not run , this will not print");

}

JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_testExceptionM2
(JNIEnv *env, jobject obj){
    jclass cls = (*env)->GetObjectClass(env,obj);
    jfieldID fid = (*env)->GetFieldID(env,cls, "key1", "Ljava/lang/String;");

    jthrowable err = (*env)->ExceptionOccurred(env);
    if(err != NULL){
        (*env)->ExceptionClear(env);
        fid = (*env)->GetFieldID(env,cls,"key","Ljava/lang/String");
    }

//    jstring key = (*env)->GetObjectField(env, obj, fid);
//    char* c_str = (*env)->GetStringUTFChars(env, key, NULL);

    //参数不正确，程序员自己抛出异常，可以在Java中捕获
//    if (_stricmp(c_str,"efg") != 0){
        jclass err_clz = (*env)->FindClass(env, "java/lang/IllegalArgumentException");
        (*env)->ThrowNew(env, err_clz, "key value is invalid!");
//    }

}


