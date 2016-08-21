package com.tanlifei.framework.main.ui.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.support.okhttp.OkHttpUtils;
import com.support.utils.Logger;
import com.tanlifei.common.bean.params.NotifyParams;
import com.tanlifei.framework.R;
import com.tanlifei.framework.main.bean.AppUpdateBean;
import com.tanlifei.support.constants.fixed.GlobalConstants;
import com.tanlifei.support.http.FileCallBack;
import com.tanlifei.support.utils.NotifyUtils;

import java.io.File;


/**
 * app 版本升级
 * Created by tanlifei on 16/2/22.
 */
public class AppDownloadService extends Service {
    PendingIntent rightPendIntent;
    NotifyUtils notify;
    NotifyParams params;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        appDownload((AppUpdateBean) intent.getParcelableExtra("bean"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 下载app
     *
     * @param updateBean
     */
    public void appDownload(AppUpdateBean updateBean) {
        notify_progress(false,0,0);
        OkHttpUtils.get().url(updateBean.getUrl()).build().execute(new FileCallBack(GlobalConstants.DOWNLOAD_PATH, updateBean.getName()) {
            @Override
            public void inProgress(float progress, long total) {
                Logger.d("mydownload ---", total + "----->" + progress);
                updateProgress(false,progress,total);
            }

            @Override
            public void onResponse(File response) {
                Logger.d("mydownload ---", "onResponse");
                updateProgress(true,0 ,0);
            }
        });
    }

    /**
     * 通知栏列表item样式
     *
     */
    private void updateProgress(boolean isDone, float progress, long total) {
        if (null == params) {
            params = new NotifyParams();
            params.setSmallIcon(R.mipmap.ic_launcher);
            params.setPendingIntent(rightPendIntent);
            params.setTicker("正在下载新版本");
            params.setTitle("Android 6.0.1 下载");
            params.setContent("正在下载中");
            params.setVibrate(false);
            params.setLights(false);
            params.setSound(false);
        }
        notify.notifyDownloadProgress(params, isDone, total, progress);

    }

    /**
     * 动态通知栏样式
     */
    private void notify_progress(boolean isDone, float progress, long total) {
        notify = new NotifyUtils(this, 100001);
        int smallIcon = R.mipmap.ic_launcher;
        String ticker = "您有一条新通知";
        NotifyParams params = new NotifyParams();
        params.setPendingIntent(rightPendIntent);
        params.setSmallIcon(smallIcon);
        params.setTicker(ticker);
        params.setTitle("正在下载新版本");
        params.setContent("正在下载中");
        params.setVibrate(true);
        params.setLights(true);
        params.setSound(true);
        notify.notifyDownloadProgress(params, isDone, total, progress);
    }

}
