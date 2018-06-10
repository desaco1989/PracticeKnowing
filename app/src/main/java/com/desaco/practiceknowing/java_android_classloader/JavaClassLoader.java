package com.desaco.practiceknowing.java_android_classloader;

/**
 * Created by desaco on 2018/6/10.
 * Java自带加载器：Bootstrap ClassLoader，Extention ClassLoader，system class loader；自定义Java 中的类加载器 ClassLoader一般覆盖findClass()方法
 */

public class JavaClassLoader {
    public void javaClassLoad() {
        //获取系统、应用类加载器
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统/应用类加载器：" + appClassLoader);

        //获取系统、应用类加载器的父类加载器，得到扩展类加载器
        ClassLoader extcClassLoader = appClassLoader.getParent();
        System.out.println("扩展类加载器" + extcClassLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));

        //获取扩展类加载器的父加载器，但因根类加载器并不是用Java实现的所以不能获取
        System.out.println("扩展类的父类加载器：" + extcClassLoader.getParent());
    }
}
