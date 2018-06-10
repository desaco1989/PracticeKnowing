////
//// Created by desaco on 2018/6/10.
//// https://blog.csdn.net/panda1234lee/article/details/13503291
////
//
//#include<stdio.h>
//#include<stdlib.h>
//#include<unistd.h>
//#include<pthread.h>
//#include<string.h>
//#include<assert.h>
//
//#include<jni.h>
//#include<android/log.h>
//
//#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "jni_thread", __VA_ARGS__))
//#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, "jni_thread", __VA_ARGS__))
//#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, "jni_thread", __VA_ARGS__))
//
//// 线程数
//#define NUMTHREADS 5
//// 指定要注册的类
//
//#define JNIREG_CLASS "com/example/jni_thread_demo/JNI_ThreadActivity"
//// 全局变量
//JavaVM *g_jvm = NULL;
//jobject g_obj = NULL;
//
//void *thread_fun(void *arg) {
//    JNIEnv *env;
//    jclass cls;
//    jmethodID mid, mid1;
//
//    // Attach主线程
//    if ((*g_jvm)->AttachCurrentThread(g_jvm, &env, NULL) != JNI_OK) {
//        LOGE("%s: AttachCurrentThread() failed", __FUNCTION__);
//        return NULL;
//    }
//
//    // 找到对应的类
//    cls = (*env)->GetObjectClass(env, g_obj);
//    if (cls == NULL) {
//        LOGE("FindClass() Error ......");
//        goto error;
//    }
//    // 再获得类中的方法
//    mid = (*env)->GetStaticMethodID(env, cls, "fromJNI", "(I)V");
//    if (mid == NULL) {
//        LOGE("GetStaticMethodID() Error ......");
//        goto error;
//    }
//    // 最后调用java中的静态方法
//    (*env)->CallStaticVoidMethod(env, cls, mid, (int) arg);
//
//    //获得类中的“成员”方法
//    mid1 = (*env)->GetMethodID(env, cls, "From_JNI_Again", "(I)V");
//    if (mid == NULL) {
//        LOGE("GetMethodID() Error ......");
//        goto error;
//    }
//    // 最后调用类中“成员”方法
//    (*env)->CallVoidMethod(env, g_obj, mid1, (int) arg);
//
//    //错误处理代码
//    error:
//    //Detach主线程
//    if ((*g_jvm)->DetachCurrentThread(g_jvm) != JNI_OK) {
//        LOGE("%s: DetachCurrentThread() failed", __FUNCTION__);
//    }
//
//    pthread_exit(0);
//}
//
///*
// * Class:     com_example_jni_thread_demo_JNI_ThreadActivity
// * Method:    mainThread
// * Signature: ()V
// * 不用JNI_OnLoad时的复杂命名方式
// */
//JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_mainThread
//        (JNIEnv *env, jobject obj) {
//    int i;
//    pthread_t pt[NUMTHREADS];
//
//    for (i = 0; i < NUMTHREADS; i++) {
//        // 创建线程，并指明调用的函数
//        pthread_create(&pt[i], NULL, &thread_fun, (void *) i);
//    }
//}
//
///*
// * Class:     com_example_jni_thread_demo_JNI_ThreadActivity
// * Method:    setJNIEnv
// * Signature: ()V
// * 不用JNI_OnLoad时的复杂命名方式
// */
//
//JNIEXPORT void JNICALL Java_com_desaco_practiceknowing_native_1method_CcallJava_setJNIEnv
//        (JNIEnv *env, jobject obj) {
//    // 保存全局JVM以便在子线程中使用
//    (*env)->GetJavaVM(env, &g_jvm);
//    // 不能直接赋值(g_obj = ojb)
//    g_obj = (*env)->NewGlobalRef(env, obj);
//}
//
//JNIEXPORT void JNICALL
//native_mainThread(JNIEnv *env, /*jclass clazz*/ jobject obj)// 使用jclass和jobject都可以
//{
//    LOGI("native_mainThread");
//    int i;
//    pthread_t pt[NUMTHREADS];
//
//    for (i = 0; i < NUMTHREADS; i++) {
//        // 创建线程，并指明调用的函数，注意只接收一个参数i作为thread_fun的参数，后面会介绍怎么传多个参数
//        pthread_create(&pt[i], NULL, &thread_fun, (void *) i);
//    }
//    LOGI("native_mainThread finish");
//}
//
//JNIEXPORT void JNICALL
//native_setJNIEnv(JNIEnv *env, /*jclass obj*/ jobject obj)// 使用jclass和jobject都可以
//{
//    LOGI("native_setJNIEnv");
//    // 保存全局JVM以便在子线程中使用
//    (*env)->GetJavaVM(env, &g_jvm);
//    // 不能直接赋值(g_obj = ojb)
//    g_obj = (*env)->NewGlobalRef(env, obj);
//    LOGI("native_setJNIEnv finish");
//}
//
///**
//* Table of methods associated with a single class.
//*/
//static JNINativeMethod gMethods[] =
//        {
//                {"mainThread", "()V", (void *) native_mainThread},   // 绑定：注意千万签名结尾不能加分号！！！！！！
//                {"setJNIEnv",  "()V", (void *) native_setJNIEnv},
//        };
//
///*
//* Register several native methods for one class.
//*/
//static int registerNativeMethods(JNIEnv *env, const char *className,
//                                 JNINativeMethod *gMethods, int numMethods) {
//    jclass clazz;
//    clazz = (*env)->FindClass(env, className);
//    if (clazz == NULL) {
//        return JNI_FALSE;
//    }
//    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
//        return JNI_FALSE;
//    }
//
//    return JNI_TRUE;
//}
//
///*
//* Register native methods for all classes we know about.
//*/
//static int registerNatives(JNIEnv *env) {
//    if (!registerNativeMethods(env, JNIREG_CLASS, gMethods,
//                               sizeof(gMethods) / sizeof(gMethods[0])))
//        return JNI_FALSE;
//
//    return JNI_TRUE;
//}
//
///*
//* Set some test stuff up.
//*
//* Returns the JNI version on success, -1 on failure.
//*/
//JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
//    JNIEnv *env = NULL;
//    jint result = -1;
//
//    if ((*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_4) != JNI_OK) {
//        LOGE("GetEnv failed!");
//        return -1;
//    }
//    //===========================================
//    assert(env != NULL);
//
//    if (!registerNatives(env)) {// 注册本地方法
//        return -1;
//    }
//    //===========================================
//    /* success -- return valid version number */
//    result = JNI_VERSION_1_4;
//
//    return result;
//}
