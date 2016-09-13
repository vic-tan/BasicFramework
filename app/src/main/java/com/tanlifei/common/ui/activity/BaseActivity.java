package com.tanlifei.common.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.base.autolayout.AutoLayoutActivity;
import com.base.utils.ActivityManager;
import com.base.utils.StartActUtils;
import com.base.utils.StatusUtils;
import com.base.utils.ToastUtils;
import com.tanlifei.framework.R;
import com.tanlifei.framework.main.ui.service.AppDownloadService;
import com.tanlifei.framework.main.ui.service.CheckAppUpdateService;
import com.tanlifei.support.utils.ResUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tanlifei on 15/12/17.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        baseRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityManager.getActivityManager().addActivity(this);
        StatusUtils.setTranslucentStatus(this,R.color.common_actionbar_bg_color);
    }




    /**
     * 显示横竖屏
     * ActivityInfo.SCREEN_ORIENTATION_PORTRAIT 只能竖屏
     * -1，可以横竖屏
     */
    protected void baseRequestedOrientation(int requestedOrientation) {
        if (requestedOrientation == -1) {
            return;
        } else {
            setRequestedOrientation(requestedOrientation);
        }
    }


    /**
     * 返回操作 子类可以覆盖此方法做特殊业务
     */
    protected void actionBack() {
        StartActUtils.finish(mContext);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getActivityManager().finishActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        actionBack();
    }

    private Boolean isExit = false;

    /**
     * 退出App
     */
    protected void exitApp() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            ToastUtils.show(mContext, ResUtils.getStr(R.string.app_exit));
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            stopService(new Intent(mContext, CheckAppUpdateService.class));//查检升级服务
            stopService(new Intent(mContext, AppDownloadService.class));//下载app升级服务
            //finish所有页面和kill app
            ActivityManager.getActivityManager().appExit(this);
        }
    }


}
