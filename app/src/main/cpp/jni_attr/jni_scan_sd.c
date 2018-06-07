//
// Created by desaco on 2018/6/7.
//
#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <sys/statfs.h>
#include <sys/types.h>
#include <com_desaco_practiceknowing_native_method_JniScanSdcard.h>
//#include <fcntl.h>


#define  LOG_TAG    "SCANER"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define ARRAY_LENGTH 1024
static int i = 0;

static JavaVM *gvm;
static jobject giface;
static const char *classPathName = "com/coder80/scaner/MainActivity";

static JNINativeMethod methods[] = {
        {"scanDir",        "(Ljava/lang/String;)V",                                (void *) Java_com_desaco_practiceknowing_native_1method_JniScanSdcard_scanDir},
        {"getPathArray",   "(Ljava/lang/String;)[Ljava/lang/String;",              (void *) Java_com_desaco_practiceknowing_native_1method_JniScanSdcard_getPathArray},
        {"getTracksArray", "(Ljava/lang/String;)[Lcom/coder80/scaner/Track_Info;", (void *) Java_com_desaco_practiceknowing_native_1method_JniScanSdcard_getTracksArray}
};

jint JNI_OnLoad(JavaVM *vm, void *reserved) {

    jclass clazz = NULL;
    JNIEnv *env = NULL;
    jmethodID constr = NULL;
    jobject obj = NULL;

    LOGE("JNI_OnLoad");
    gvm = vm;
    if ((*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        LOGE("GetEnv FAILED");
        return -1;
    }

    clazz = (*env)->FindClass(env, classPathName);
    if (!clazz) {
        LOGE("Registration unable to find class '%s'", classPathName);
        return -1;
    }
    constr = (*env)->GetMethodID(env, clazz, "<init>", "()V");
    if (!constr) {
        LOGE("Failed to get constructor");
        return -1;
    }
    obj = (*env)->NewObject(env, clazz, constr);
    if (!obj) {
        LOGE("Failed to create an interface object");
        return -1;
    }
    giface = (*env)->NewGlobalRef(env, obj);

    if ((*env)->RegisterNatives(env, clazz, methods,
                                sizeof(methods) / sizeof(methods[0])) < 0) {
        LOGE("Registration failed for '%s'", classPathName);
        return -1;
    }
    return JNI_VERSION_1_4;
}


jobjectArray JNICALL
Java_com_desaco_practiceknowing_native_1method_JniScanSdcard_getPathArray(JNIEnv *env, jobject obj,
                                                                          jstring jdirPath) {
    const char *dirPath = (*env)->GetStringUTFChars(env, jdirPath, NULL);
    jclass objClass = (*env)->FindClass(env, "java/lang/String");
    jobjectArray textsArray = (*env)->NewObjectArray(env, (jsize) ARRAY_LENGTH, objClass, 0);
    i = 0;
    scan_dir2(env, dirPath, textsArray);
    return textsArray;
}


void scan_dir2(JNIEnv *env, const char *directory, jobjectArray array) {
    DIR *dp;
    struct dirent *entry;
    struct stat statbuf;
    if ((dp = opendir(directory)) == NULL) {
        perror("opendir");
        return;
    }
    chdir(directory);
    jstring jstr;

    while ((entry = readdir(dp)) != NULL) {
        stat(entry->d_name, &statbuf);
        if (S_ISDIR(statbuf.st_mode)) {
            if ((strcmp(entry->d_name, ".") != 0) &&
                (strcmp(entry->d_name, "..") != 0) &&
                (entry->d_name[0] != '.')) {
                scan_dir2(env, entry->d_name, array);
            }
        } else {
            int size = strlen(entry->d_name);
            if (entry->d_name[0] != '.'  //隐藏文件
                && (statbuf.st_size / 1024) > 300  //大于300k，表示肯能有mp3文件(忽略 <300k的mp3)
                && strcmp((entry->d_name + (size - 4)), ".mp3") == 0) {
                char *parentPath = (char *) malloc(1024);
                char *absolutePath = (char *) malloc(1024);
                //首先获取工作路径
                getcwd(parentPath, 1024);
                //LOGE("parentPath = %s \n", parentPath);
                strcpy(absolutePath, parentPath);
                char *p = "/";
                absolutePath = strcat(absolutePath, p);
                absolutePath = strcat(absolutePath, entry->d_name);
                //statbuf.st_size,
//              LOGE("scan_dir(),file absolutePath = %s \n", absolutePath);
                jstr = (*env)->NewStringUTF(env, absolutePath);
                (*env)->SetObjectArrayElement(env, array, i, jstr);//必须放入jstring
                i++;
//              (*env)->ReleaseStringUTFChars(env,js, s);
//              LOGE("scan_dir2(),i = %d,file absolutePath = %s \n\n",i, absolutePath);
                free(parentPath);
                parentPath = NULL;
                free(absolutePath);
                absolutePath = NULL;
            }
        }
    }
    chdir("..");
    closedir(dp);
}


void Java_com_desaco_practiceknowing_native_1method_JniScanSdcard_scanDir(JNIEnv *env, jobject obj,
                                                                          jstring jdirPath) {
    const char *path = (*env)->GetStringUTFChars(env, jdirPath, NULL);
    LOGE("begin to call scan_dir() in the JNI,and path = %s \n", path);
    scan_dir(path);
}

void scan_dir(const char *directory) {
    DIR *dp;
    struct dirent *entry;
    struct stat statbuf;

    if ((dp = opendir(directory)) == NULL) {
        perror("opendir");
        return;
    }
    chdir(directory);
    //LOGE("pyb chdir directory = %s\n",directory);
    while ((entry = readdir(dp)) != NULL) {
        stat(entry->d_name, &statbuf);
        if (S_ISDIR(statbuf.st_mode)) {
            //printf("name = %s, size = %d\n", entry->d_name, (int)statbuf.st_size);
            if ((strcmp(entry->d_name, ".") != 0)
                && (strcmp(entry->d_name, "..") != 0)
                && (entry->d_name[0] != '.')) {
                scan_dir(entry->d_name);
            }
        } else {
            int size = strlen(entry->d_name);
            if (entry->d_name[0] != '.'
                && (statbuf.st_size / 1024) > 300  //大于300k，表示肯能有mp3文件(忽略 <300k的mp3)
                && strcmp(entry->d_name + (size - 4), ".mp3") == 0) {
                //LOGE("scan_dir(),file st_size = %d \n\n",(statbuf.st_size/1024));
                char *parentPath = (char *) malloc(1024);
                char *absolutePath = (char *) malloc(1024);
                //首先获取工作路径
                getcwd(parentPath, 1024);
                //LOGE("parentPath = %s \n", parentPath);
                strcpy(absolutePath, parentPath);
                char *p = "/";
                absolutePath = strcat(absolutePath, p);
                absolutePath = strcat(absolutePath, entry->d_name);
                //statbuf.st_size,
                LOGE("scan_dir(),file absolutePath = %s \n", absolutePath);
                free(parentPath);
                parentPath = NULL;
                free(absolutePath);
                absolutePath = NULL;
            }
        }
    }
    chdir("..");
    closedir(dp);
}

////displayName
//jfieldID disName;
////size
//jfieldID trackSize;
////ext
//jfieldID extName;
////filePath
//jfieldID path;
////parentPath
//jfieldID parent;
//
//jobject obj_main;

JNIEXPORT jobjectArray JNICALL
Java_com_desaco_practiceknowing_native_1method_JniScanSdcard_getTracksArray(JNIEnv *env,
                                                                            jobject obj,
                                                                            jstring jdirPath) {

    const char *dirPath = (*env)->GetStringUTFChars(env, jdirPath, NULL);

    jclass objectClass = (*env)->FindClass(env, "com/coder80/scaner/Track_Info");
    jobjectArray jobj_arr = (*env)->NewObjectArray(env, (jsize) ARRAY_LENGTH, objectClass, 0);
//    obj_main = obj; //TODO
    LOGE("in JNI getTracksArray(),dirPath = %s \n", dirPath);
    //获取Java对象
    /*
     * 或者 同样可以获取Java对象
     * jclass objectClass = env->GetObjectClass(jobject);
     * */
    //displayName
//  disName = (*env)->GetFieldID(env,objectClass,"displayName","Ljava/lang/String;");
////    //size
////    trackSize = (*env)->GetFieldID(env,objectClass,"size"," L");
//    //ext
//    extName = (*env)->GetFieldID(env,objectClass,"ext","Ljava/lang/String;");
//    //filePath
//    path = (*env)->GetFieldID(env,objectClass,"filePath","Ljava/lang/String;");
//    //parentPath
//    parent = (*env)->GetFieldID(env,objectClass,"parentPath","Ljava/lang/String;");
    i = 0;

    scan_dir3(env, dirPath, jobj_arr);
    return jobj_arr;
}

jmethodID JavaMid = NULL;
JNIEnv *jniEnvPlaying = NULL;
/*
 * para obj: Track_Info object
 * */
//void scan_dir3(JNIEnv *env,jobject obj,const char *directory,jobjectArray list)
void scan_dir3(JNIEnv *env, const char *directory, jobjectArray list) {
    DIR *dp;
    struct dirent *entry;
    struct stat statbuf;
    if ((dp = opendir(directory)) == NULL) {
        perror("opendir");
        return;
    }
    chdir(directory);
    jstring jstr;

    while ((entry = readdir(dp)) != NULL) {
        stat(entry->d_name, &statbuf);
        if (S_ISDIR(statbuf.st_mode)) {
            if ((strcmp(entry->d_name, ".") != 0) &&
                (strcmp(entry->d_name, "..") != 0) &&
                (entry->d_name[0] != '.')) {
                scan_dir3(env, entry->d_name, list);
            }
        } else {
            int size = strlen(entry->d_name);
            if (entry->d_name[0] != '.'  //隐藏文件
                && (statbuf.st_size / 1024) > 300  //大于300k，表示肯能有mp3文件(忽略 <300k的mp3)
                && strcmp((entry->d_name + (size - 4)), ".mp3") == 0) {
                char *parentPath = (char *) malloc(1024);
                char *absolutePath = (char *) malloc(1024);
                //首先获取工作路径
                getcwd(parentPath, 1024);
                //LOGE("parentPath = %s \n", parentPath);
                strcpy(absolutePath, parentPath);
                char *p = "/";
                absolutePath = strcat(absolutePath, p);
                absolutePath = strcat(absolutePath, entry->d_name);

//              jstring jstrDis = (*env)->NewStringUTF(env, entry->d_name);
//              (*env)->SetObjectField(env, obj, disName, jstrDis);
//
////                (*env)->SetLongField(env, obj, trackSize, statbuf.st_size/1024);
//
//              jstring jstrExt = (*env)->NewStringUTF(env,"mp3");
//              (*env)->SetObjectField(env, obj, extName, jstrExt);
//
//              jstring jstrPath = (*env)->NewStringUTF(env,absolutePath);
//              (*env)->SetObjectField(env, obj, path, jstrPath);
//
//              jstring jstrParentPath = (*env)->NewStringUTF(env,absolutePath);
//              (*env)->SetObjectField(env, obj, parent, jstrParentPath);

                jclass classTrackInfo = (*env)->FindClass(env, "com/coder80/scaner/Track_Info");
                // 获取Track_Info类的构造函数ID
                jmethodID midInit = (*env)->GetMethodID(env, classTrackInfo, "<init>", "()V");
                //构造Track_Info对象
                jobject objTrackInfo = (*env)->NewObject(env, classTrackInfo, midInit);

                /*查找java中的setDisplayName方法的ID，
                 * @para:obj Track_Info类的对象
                 * @para:setDisplayName为Java中的函数名称
                 * @(Ljava/lang/String;)V 表示String类型的参数。返回值V代表void
                 * */
                JavaMid = (*env)->GetMethodID(env, classTrackInfo, "setDisplayName",
                                              "(Ljava/lang/String;)V");
                if (JavaMid == NULL) {
                    LOGE("pyb setDisplayName() fun not found!");
                    return;
                }
                jstring jstrDis = (*env)->NewStringUTF(env, entry->d_name);
                //执行setDisplayName方法   jstrDis --> displayName
                (*env)->CallVoidMethod(env, objTrackInfo, JavaMid, jstrDis);
                JavaMid = NULL;

                //************************************************
                //查找java中的setSize方法的ID,J -----> long
                JavaMid = (*env)->GetMethodID(env, classTrackInfo, "setSize", "(J)V");
                if (JavaMid == NULL) {
                    LOGE("pyb setSize() fun not found!");
                    return;
                }
//              jstring jstrDis = (*env)->NewStringUTF(env,entry->d_name);
//              //执行setSize方法   statbuf.st_size --> size
                (*env)->CallVoidMethod(env, objTrackInfo, JavaMid, statbuf.st_size);
                JavaMid = NULL;
                //end

                //查找java中的setExt方法的ID，
                JavaMid = (*env)->GetMethodID(env, classTrackInfo, "setExt",
                                              "(Ljava/lang/String;)V");
                if (JavaMid == NULL) {
                    LOGE("pyb setExt() fun not found!");
                    return;
                }
                jstring jstrExt = (*env)->NewStringUTF(env, "mp3");
                //执行setExt方法   jstrExt --> ext
                (*env)->CallVoidMethod(env, objTrackInfo, JavaMid, jstrExt);
                JavaMid = NULL;

                //查找java中的setFilePath方法的ID，
                JavaMid = (*env)->GetMethodID(env, classTrackInfo, "setFilePath",
                                              "(Ljava/lang/String;)V");
                if (JavaMid == NULL) {
                    LOGE("setFilePath() fun not found!");
                    return;
                }
                jstring jstrPath = (*env)->NewStringUTF(env, absolutePath);
                //执行setFilePath方法   jstrPath --> filePath
                (*env)->CallVoidMethod(env, objTrackInfo, JavaMid, jstrPath);
                JavaMid = NULL;

                //查找java中的setParentPath方法的ID，
                JavaMid = (*env)->GetMethodID(env, classTrackInfo, "setParentPath",
                                              "(Ljava/lang/String;)V");
                if (JavaMid == NULL) {
                    LOGE("setParentPath() fun not found!");
                    return;
                }
                jstring jstrParentPath = (*env)->NewStringUTF(env, parentPath);
                //执行setParentPath方法   jstrParentPath --> parentPath
                (*env)->CallVoidMethod(env, objTrackInfo, JavaMid, jstrParentPath);
                JavaMid = NULL;


                (*env)->SetObjectArrayElement(env, list, i, objTrackInfo);
//              LOGE("scan_dir3(),i = %d,file absolutePath = %s \n\n",i, absolutePath);
                i++;

                free(parentPath);
                parentPath = NULL;
                free(absolutePath);
                absolutePath = NULL;
            }
        }
    }
    chdir("..");
    closedir(dp);
}
