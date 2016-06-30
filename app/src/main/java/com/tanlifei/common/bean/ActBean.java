package com.tanlifei.common.bean;

import android.content.Context;

/**
 * Created by tanlifei on 16/6/30.
 */
public class ActBean {
    private Context context;
    private String stackActKey;//保存所有找开的activity 在栈中的key值 请保证维一，最好用类名
    private String activityAbsolutePath;//activity全路径

    public ActBean(Context context, String stackActKey) {
        this.context = context;
        this.stackActKey = stackActKey;
    }

    public ActBean(Context context, String stackActKey, Class<?> clazz) {
        this.context = context;
        this.stackActKey = stackActKey;
        this.clazz = clazz;
    }

    public ActBean(Context context, String stackActKey, String activityAbsolutePath) {
        this.context = context;
        this.stackActKey = stackActKey;
        this.activityAbsolutePath = activityAbsolutePath;
    }


    private Class<?> clazz;//要跳转activity的类名

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getActivityAbsolutePath() {
        return activityAbsolutePath;
    }

    public void setActivityAbsolutePath(String activityAbsolutePath) {
        this.activityAbsolutePath = activityAbsolutePath;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getStackActKey() {
        return stackActKey;
    }

    public void setStackActKey(String stackActKey) {
        this.stackActKey = stackActKey;
    }
}
