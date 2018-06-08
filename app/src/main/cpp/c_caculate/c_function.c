//
// Created by desaco on 2018/6/8.
//

void native_catchException(JNIEnv *env, jobject obj)
{
    jthrowable exc;
    jclass cls = (*env)->GetObjectClass(env, obj);
    jmethodID mid =(*env)->GetMethodID(env, cls, "callbackException", "()V");
    if (mid == NULL) {
        return;
    }
    (*env)->CallVoidMethod(env, obj, mid);
    exc = (*env)->ExceptionOccurred(env);
    if (exc) {
        jclass newExcCls;
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        newExcCls = (*env)->FindClass(env,"java/lang/IllegalArgumentException");
        if (newExcCls == NULL) {
            /* Unable to find the exception class, give up. */
            return;
        }
        (*env)->ThrowNew(env, newExcCls, "thrown from C code");
    }
}

void
JNU_ThrowByName(JNIEnv *env, const char *name, const char *msg)
{
    jclass cls = (*env)->FindClass(env, name);
    /* if cls is NULL, an exception has already been thrown */
    if (cls != NULL) {
        (*env)->ThrowNew(env, cls, msg);
    }
    /* free the local ref */
    (*env)->DeleteLocalRef(env, cls);
}