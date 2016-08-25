package com.tanlifei.framework.main.ui;


import android.app.Application;
import android.content.Context;

import com.support.galleryfinal.CoreConfig;
import com.support.galleryfinal.FunctionConfig;
import com.support.galleryfinal.GalleryFinal;
import com.support.galleryfinal.GalleryFinalImageLoader;
import com.support.galleryfinal.ThemeConfig;
import com.support.imageloader.FanImageLoader;
import com.support.okhttp.OkHttpUtils;
import com.support.utils.io.FileUtils;
import com.support.galleryfinal.UILImageLoader;
import com.tanlifei.support.constants.fixed.GlobalConstants;
import com.tanlifei.support.constants.fixed.OnOffConstants;
import com.tanlifei.support.constants.level.OnOffLevel;
import com.tanlifei.support.exception.CrashHandler;

import java.util.concurrent.TimeUnit;

/**
 * 全局Application
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class BaseApplication extends Application {

    public static Context appContext;
    public static FunctionConfig functionConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initCreateFolders();//创建文件夹
        setCrashHandler();//打开全局未捕获异常
        initImageLoader();//初始化图片加载缓存 ImageLoader基本配置
        initGalleryFinal();//初始化选择图片器
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
     */
    private void initImageLoader() {
        FanImageLoader.init(appContext, GlobalConstants.IMAGES_CACHE_PATH);


    }

    private void initGalleryFinal() {
        //配置功能
        functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                //.setForceCrop(true)//启动强制裁剪功能,一进入编辑页面就开启图片裁剪，不需要用户手动点击裁剪，此功能只针对单选操作
                //.setForceCropEdit(false)//在开启强制裁剪功能时是否可以对图片进行编辑（也就是是否显示旋转图标和拍照图标）
                //.setEnablePreview(false)//是否开启预览功能
                //.setEnablePreview(true)
                .build();

        //配置imageloader
        GalleryFinalImageLoader imageloader = new UILImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(appContext, imageloader, ThemeConfig.GREEN)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(true)//关闭动画
                .build();
        GalleryFinal.init(coreConfig);
    }


    /**
     * 设置是否开启全局未捕获异常
     */
    private void setCrashHandler() {
        if (OnOffConstants.UNCAUGHT_EX_LEVEL == OnOffLevel.OFF) {//不写入
            return;
        }
        CrashHandler.getInstance().init(this).setCrashSave(true)
                .setCrashSaveTargetFolder(GlobalConstants.CRASH_PATH);
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
