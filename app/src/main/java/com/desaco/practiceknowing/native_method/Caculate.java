package com.desaco.practiceknowing.native_method;

/**
 * Created by desaco on 2018/5/29.
 * Add, subtract, multiply and divide，四则运算
 */

public class Caculate {
    private Caculate() {

    }

    static {
        System.loadLibrary("caculate-lib");
    }

    private static Caculate singleInstance;

    public static Caculate getInstance() {
        if (singleInstance == null) {
            synchronized (Caculate.class) {
                singleInstance = new Caculate();
                return singleInstance;
            }
        }
        return singleInstance;
    }

    public native int add(int num1, int num2);

    public native int subtract(int num1, int num2);

    public native int multiply(int num1, int num2);

    public native int divide(int num1, int num2);

}
