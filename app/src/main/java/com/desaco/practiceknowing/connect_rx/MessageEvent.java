package com.desaco.practiceknowing.connect_rx;

/**
 * Created by desaco on 2018/5/9.
 */

public class MessageEvent {
    private Object message;

    /**
     *
     * @param message 消息实体
     * @param msgType 消息类型
     */
    public MessageEvent(Object message,String msgType) {
        this.message = message;

    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
