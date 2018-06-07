package com.desaco.practiceknowing.native_method;

import java.io.Serializable;

/**
 * Created by desaco on 2018/6/7.
 */

public class Track_Info implements Serializable {

    private String displayName;
    private long size;
    private String ext;//后缀名称,eg.mp3
    private String filePath;
    private String parentPath;


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
//      Log.e("Track_Info.java", "setSize() called in JNI, size = " + size);
        this.size = size;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}
