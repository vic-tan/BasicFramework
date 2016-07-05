package com.tanlifei.support.constants.fixed;


import android.os.Environment;

/**
 * 程序里全局引用常量，不确定所属于其它常量
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class GlobalConstants {


    /**
     * 针对全局未捕获异常，保存到本志文件路径
     */
    public static final String CRASH_PATH = Environment.getExternalStorageDirectory().toString() + "/basic_framework/crash/";

    /**
     * 针对全局图片缓存路径
     */
    public static final String IMAGES_CACHE_PATH = Environment.getExternalStorageDirectory().toString() + "/basic_framework/images/cache/";

    /**
     * 文件下载保存路径
     */
    public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().toString() + "/basic_framework/download/";

    /**
     * 数据库名字
     **/
    public static final String DB_NAME = "basic_framework_db";


}

