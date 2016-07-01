package com.tanlifei.common.bean;

import android.content.Context;

/**
 * Created by tanlifei on 16/6/30.
 */
public class ActBean {
    private Context context;
    private Class<?> clazz;//要跳转activity的类名

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }



    public ActBean(Context context) {
        this.context = context;
    }

    public ActBean(Context context,  Class<?> clazz) {
        this.context = context;
        this.clazz = clazz;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
