package com.desaco.practiceknowing.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by desaco on 2018/6/7.
 * https://blog.csdn.net/bear_huangzhen/article/details/46551433
 */

public class SharedPrefsUtil {
    /**
     * 向SharedPreferences中写入int类型数据
     *
     * @param context 上下文环境
     * @param key     键
     * @param value   值
     */
    public static void putValue(Context context, String key,
                                int value) {
        Editor sp = getEditor(context);
        sp.putInt(key, value);
        sp.commit();
    }

    /**
     * 向SharedPreferences中写入boolean类型的数据
     *
     * @param context 上下文环境
     * @param key     键
     * @param value   值
     */
    public static void putValue(Context context, String key,
                                boolean value) {
        Editor sp = getEditor(context);
        sp.putBoolean(key, value);
        sp.commit();
    }

    /**
     * 向SharedPreferences中写入String类型的数据
     *
     * @param context 上下文环境
     * @param key     键
     * @param value   值
     */
    public static void putValue(Context context, String key,
                                String value) {
        Editor sp = getEditor(context);
        sp.putString(key, value);
        sp.commit();
    }

    /**
     * 向SharedPreferences中写入float类型的数据
     *
     * @param context 上下文环境
     * @param key     键
     * @param value   值
     */
    public static void putValue(Context context, String key,
                                float value) {
        SharedPreferences.Editor sp = getEditor(context);
        sp.putFloat(key, value);
        sp.commit();
    }

    /**
     * 向SharedPreferences中写入long类型的数据
     *
     * @param context 上下文环境
     * @param key     键
     * @param value   值
     */
    public static void putValue(Context context, String key,
                                long value) {
        Editor sp = getEditor(context);
        sp.putLong(key, value);
        sp.commit();
    }

    /**
     * 从SharedPreferences中读取int类型的数据
     *
     * @param context  上下文环境
     * @param key      键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static int getValue(Context context, String key,
                               int defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        int value = sp.getInt(key, defValue);
        return value;
    }

    /**
     * 从SharedPreferences中读取boolean类型的数据
     *
     * @param context  上下文环境
     * @param key      键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static boolean getValue(Context context, String key,
                                   boolean defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    /**
     * 从SharedPreferences中读取String类型的数据
     *
     * @param context  上下文环境
     * @param key      键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static String getValue(Context context, String key,
                                  String defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        String value = sp.getString(key, defValue);
        return value;
    }

    /**
     * 从SharedPreferences中读取float类型的数据
     *
     * @param context  上下文环境
     * @param key      键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static float getValue(Context context, String key,
                                 float defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        float value = sp.getFloat(key, defValue);
        return value;
    }

    /**
     * 从SharedPreferences中读取long类型的数据
     *
     * @param context  上下文环境
     * @param key      键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    public static long getValue(Context context, String key,
                                long defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        long value = sp.getLong(key, defValue);
        return value;
    }

    //获取Editor实例
    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    //获取SharedPreferences实例
    private static SharedPreferences getSharedPreferences(Context context) {
        //对应的xml文件名称
        String fileName = "share_file";
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }
}
