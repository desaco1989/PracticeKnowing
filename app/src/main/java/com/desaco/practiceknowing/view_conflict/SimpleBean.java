package com.desaco.practiceknowing.view_conflict;

/**
 * Created by desaco on 2018/5/23.
 */

public class SimpleBean {
    private String title;
    private String msg;
    private boolean isSubScribe;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSubScribe() {
        return isSubScribe;
    }

    public void setSubScribe(boolean subScribe) {
        isSubScribe = subScribe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
