package com.desaco.practiceknowing.activity.eventbus;

/**
 * Created by desaco on 2018/10/18.
 */

public class BackgroundMessage {
    private String message;

    public BackgroundMessage(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
