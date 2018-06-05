package com.desaco.practiceknowing.native_method;

/**
 * Created by desaco on 2018/5/29.
 */

public class NativeEncryptDecode {

    private NativeEncryptDecode() {

    }

    static {
        System.loadLibrary("encryptDecode-lib");
    }

    private static NativeEncryptDecode singleInstance;

    public static NativeEncryptDecode getInstance() {
        if (singleInstance == null) {
            synchronized (NativeEncryptDecode.class) {
                singleInstance = new NativeEncryptDecode();
                return singleInstance;
            }
        }
        return singleInstance;
    }

    //TODO String与jstring互转
    public native String encrypt(String encryptData);

    public native String decode(String decodeData);


    //加密本地方法
    public native String encodeWithC(String text, int length);
    //解密本地方法
    public native String decodeWithC(String text, int length);
}
