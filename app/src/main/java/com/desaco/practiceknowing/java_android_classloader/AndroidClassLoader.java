package com.desaco.practiceknowing.java_android_classloader;

import android.content.Context;
import android.util.Log;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by desaco on 2018/6/10.
 * Android中类加载器有BootClassLoader,URLClassLoader,PathClassLoader,DexClassLoader,BaseDexClassLoader等都最终继承自java.lang.ClassLoader。
 * BootClassLoader是Android平台上所有ClassLoader的最终parent,这个内部类是包内可见
 * PathClassLoader和DexClassLoader都继承自BaseDexClassLoader,其中的主要逻辑都是在BaseDexClassLoader完成的。
 * <p>
 * PathClassLoader 只能加载dex文件；;只能加载系统中已经安装过的apk；
 * DexClassLoader 可以加载jar/apk/dex，可以从SD卡中加载未安装的apk；
 * URLClassLoader 可以加载java中的jar，但是由于dalvik不能直接识别jar，所以此方法在android中无法使用，尽管还有这个类
 */

public class AndroidClassLoader {
    public void androidClassLoad(Context context) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Log.d("desaco pathClassLoader", "classLoader : " + pathClassLoader + "\n" +
                "parent : " + pathClassLoader.getParent() + "\n" +
                "grandParent : " + pathClassLoader.getParent().getParent() + "\n" +
                "system classloader : " + ClassLoader.getSystemClassLoader() + "\n" +
                "system parent : " + ClassLoader.getSystemClassLoader().getParent());

        DexClassLoader dexClassLoader = (DexClassLoader) context.getClassLoader();
        Log.d("desaco dexClassLoader", "classLoader : " + dexClassLoader + "\n" +
                "parent : " + dexClassLoader.getParent() + "\n" +
                "grandParent : " + dexClassLoader.getParent().getParent() + "\n" +
                "system classloader : " + ClassLoader.getSystemClassLoader() + "\n" +
                "system parent : " + ClassLoader.getSystemClassLoader().getParent());
    }
}
