package com.desaco.practiceknowing.native_method;

/**
 * Created by desaco on 2018/6/7.
 *
 * https://blog.csdn.net/coder80/article/details/8590137
 * https://blog.csdn.net/coder80/article/details/8591830
 * https://blog.csdn.net/coder80/article/details/8592474
 */

public class JniScanSdcard {
    public native void scanDir(String dirPath);
    public native String[] getPathArray(String dirPath);
    public native Track_Info[] getTracksArray(String dirPath);
}
