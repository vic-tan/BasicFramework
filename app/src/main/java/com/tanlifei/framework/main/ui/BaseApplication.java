package com.tanlifei.framework.main.ui;


import android.app.Application;
import android.content.Context;

import com.tanlifei.support.constants.fixed.GlobalConstants;
import com.tanlifei.support.constants.fixed.OnOffConstants;
import com.tanlifei.support.constants.level.OnOffLevel;
import com.tanlifei.support.exception.CrashHandler;
import com.tanlifei.support.okhttp.OkHttpUtils;
import com.tanlifei.support.utils.FileUtils;

import java.util.concurrent.TimeUnit;

/**
 * 全局Application
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class BaseApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initCreateFolders();//创建文件夹
        setCrashHandler();//打开全局未捕获异常
        //initImageLoader();//初始化图片加载缓存 ImageLoader基本配置
        //inittDatabase();//创建数据表
        initOKhttp();//初始化Okhttp*/
    }

    /**
     * 初始化Okhttp
     */
    private void initOKhttp() {
        OkHttpUtils.getInstance().debug("OkHttpUtils", true).setConnectTimeout(15000, TimeUnit.MILLISECONDS);
        OkHttpUtils.getInstance().setCertificates();
    }

    /**
     * 初始化图片加载缓存 ImageLoader基本配置
     *//*
    private void initImageLoader() {
        FanImageLoader.init(appContext, GlobalConstants.IMAGES_CACHE_PATH);
    }

    */

    /**
     * 设置是否开启全局未捕获异常
     */
    private void setCrashHandler() {
        if (OnOffConstants.UNCAUGHT_EX_LEVEL == OnOffLevel.OFF) {//不写入
            return;
        }
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());// 注册crashHandler
    }

    //创建数据表
    /*private void inittDatabase() {
        Connector.getDatabase();//litepal创建数据表
    }

    /**
     * 创建文件夹
     */
    private void initCreateFolders() {
        FileUtils.makeFolders(GlobalConstants.CRASH_PATH);//针对全局未捕获异常，保存到本志文件路径
        FileUtils.makeFolders(GlobalConstants.DOWNLOAD_PATH);//文件下载保存路径
        FileUtils.makeFolders(GlobalConstants.IMAGES_CACHE_PATH);//针对全局图片缓存路径
    }
}
