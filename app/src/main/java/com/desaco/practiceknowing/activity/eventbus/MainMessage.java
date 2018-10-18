package com.desaco.practiceknowing.activity.eventbus;

/**
 * Created by desaco on 2018/10/18.
 */

public class MainMessage {
    private String message;

    public MainMessage(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
