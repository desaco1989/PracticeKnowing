package com.desaco.practiceknowing.activity.eventbus;

/**
 * Created by desaco on 2018/10/18.
 */

public class PostMessage {
    private String message;

    public PostMessage(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
