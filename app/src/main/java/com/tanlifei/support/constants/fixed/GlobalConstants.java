package com.tanlifei.support.constants.fixed;


import android.os.Environment;

import java.io.File;

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
    public static final String CRASH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+ "basic_framework/crash";

    /**
     * 针对全局图片缓存路径
     */
    public static final String IMAGES_CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "basic_framework/images/cache/";

    /**
     * 文件下载保存路径
     */
    public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "basic_framework/download/";

    /**
     * 缓存在app 目录下的 路径
     */
    public static final String APP_CACHE_NAME = "appCache";

    /**
     * 数据库名字
     **/
    public static final String DB_NAME = "basic_framework_db";


    //region Description dialog

    /**
     *  平常 dialog 点屏幕大小的百分比
     */
    public static final float DIALOG_NORMAL_DIALOG_SCALE = 0.79f;

    /**
     * 平常 mRefreshList dialog 点屏幕大小的百分比
     */
    public static final float DIALOG_NORMAL_LIST_DIALOG_SCALE = 0.7f;

    /**
     * 下弹出的 dialog 点屏幕大小的百分比
     */
    public static final float DIALOG_ACTION_SHEET_DIALOG_SCALE = 0.95f;

    /**
     * dialog wondows 内容行间距大小
     */
    public static final float DIALOG_WONDWON_CONTENT_LINE_SPACING = 1.3f;
    //endregion


}


