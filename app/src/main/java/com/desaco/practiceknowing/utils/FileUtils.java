package com.desaco.practiceknowing.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

/**
 * Created by desaco on 2018/5/24.
 */

public class FileUtils {
    private FileUtils() {
    }

    final static String sdCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();


    /**
     * 在SD卡上创建目录
     */
    public static File createSdFolder(String fileFolder) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File dirFile = new File(sdCardRoot + File.separator + fileFolder + File.separator);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            return dirFile;
        } else {
            return null;
        }

    }

    /**
     * 在SD卡上创建文件
     */
    public static File createSdFile(String fileName, String fileFolder) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(sdCardRoot + File.separator + fileFolder + File.separator + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } else {
            return null;
        }

    }

    /**
     * 写入数据到SD卡中,覆盖的形式
     */
    public static void writeInputStream2Sd(String fileFolder, String fileName, InputStream data) {
        File file = null;
        OutputStream output = null;
        try {

            if (null == createSdFolder(fileFolder)) {//创建目录
                throw new Exception("创建文件目录失败！");
            }
            file = createSdFile(fileName, fileFolder);  //创建文件
            if(null == file){
                throw new Exception("创建文件失败！");
            }
            output = new FileOutputStream(file);
            byte buffer[] = new byte[2 * 1024];          //每次写2K数据
            int temp;
            while ((temp = data.read(buffer)) != -1) {
                output.write(buffer, 0, temp);
            }
            output.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();    //关闭数据流操作
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void writeStr2Sd(String fileFolder, String fileName, String data) {

        File file = null;
        OutputStream out = null;

        try {
            if (null == createSdFolder(fileFolder)) {//创建目录
                throw new Exception("创建文件目录失败！");
            }
            file = createSdFile(fileName, fileFolder);  //创建文件
            if(null == file){
                throw new Exception("创建文件失败！");
            }

            out = new FileOutputStream(file);
            out.write(data.getBytes());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();    //关闭数据流操作
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //写数据,追加的形式
    public static void write(String content, String fileFolder, String fileName) {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //获取SD卡的目录
                File dirFile = new File(sdCardRoot + File.separator + fileFolder + File.separator);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                File file = new File(dirFile.toString() + File.separator + fileName);//TODO
                if (!file.exists()) {
                    file.createNewFile();
                }
                //以指定文件创建RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                //将文件记录指针移动到最后
                raf.seek(file.length());
                //输出文件内容
                raf.write(content.getBytes());
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读数据
    public  static String read(String fileFolder, String fileName) {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //获得SD卡对应的存储目录
//                File sdCardDir = Environment.getExternalStorageDirectory();
                File file = new File(sdCardRoot + File.separator + fileFolder + File.separator + fileName);
                if (!file.exists()) {
                    return "文件不存在！";
                }
                //获取指定文件对应的输入流
                FileInputStream fis = new FileInputStream(file);
                //将指定输入流包装成BufferReader
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder("");
                String line = null;
                //循环读取文件内容
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void stringTxt(String str) {
        try {
            FileWriter fw = new FileWriter("/sdcard/aaa" + "/cmd.txt");//SD卡中的路径
            fw.flush();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //写入私有文件中；android 私有文件夹
    public static void save(Context context, String inputText, String fileName) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取私有文件下的数据；android 私有文件夹
    public static String load(Context context, String fileName) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = context.openFileInput(fileName);//文件名
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
