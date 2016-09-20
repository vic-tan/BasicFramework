package com.tanlifei.demo.eventbus;

/**
 * Created by tanlifei on 16/9/20.
 */
public class FirstEvent {
    private String mMsg;
    public FirstEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
