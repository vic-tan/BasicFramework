package com.tanlifei.demo.evenbean;

/**
 * Created by tanlifei on 16/9/20.
 */
public class FirstEvent extends BeanEventParams {
    private String mMsg;

    public FirstEvent(String msg, int tag) {
        setTag(tag);
        this.mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }

}
