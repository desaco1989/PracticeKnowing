package com.desaco.practiceknowing.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by desaco on 2018/4/18.
 */

public class CommonUtils {
    private CommonUtils() {
    }

    public static String createFolderAndFilePath(String folderPath, String filePath) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // Environment.getExternalStorageState().toString()
            File folder = new File("/mnt/sdcard/" + folderPath);
            if (!folder.exists()) {
                //若不存在，创建目录，可以在应用启动的时候创建
                folder.mkdir();
            }
            File file = new File(folder.toString() + filePath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    Log.e("desaco","create success");
                    return file.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("desaco","create fail");
                    return null;
                }
            } else {
                Log.e("desaco","create success");
                return file.toString();
            }
        } else {
            Log.e("desaco","create fail");
            return null;
        }
    }

    public static void isExistFileFolder(String folderPath) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File folder = new File(Environment.getExternalStorageState().toString() + folderPath);
            if (!folder.exists()) {
                //若不存在，创建目录，可以在应用启动的时候创建
                folder.mkdirs();
            }
        }
    }

    public static void createFile(File newFile) {
        try {
            // 创建临时文件,注意这里的格式为.pcm
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //SD是否存在
    private static boolean isExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
