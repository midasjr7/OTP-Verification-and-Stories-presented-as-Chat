package com.midas.junior.msglegion;

public class Message {
        String msg;
        boolean bubble;
        String time;

    public Message(String msg, boolean bubble,String time) {
        this.msg = msg;
        this.bubble = bubble;
        this.time=time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isBubble() {
        return bubble;
    }

    public void setBubble(boolean bubble) {
        this.bubble = bubble;
    }
}
