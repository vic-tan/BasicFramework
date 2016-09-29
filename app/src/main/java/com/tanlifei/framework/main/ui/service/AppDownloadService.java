package com.tanlifei.framework.main.ui.service;

import android.app.IntentService;
import android.content.Intent;

import com.tanlifei.framework.main.bean.AppUpdateBean;
import com.tanlifei.support.constants.fixed.GlobalConstants;
import com.tanlifei.support.http.FileCallBack;
import com.tlf.basic.support.okhttp.OkHttpUtils;
import com.tlf.basic.utils.Logger;

import java.io.File;


/**
 * app 版本升级
 * Created by tanlifei on 16/2/22.
 */
public class AppDownloadService extends IntentService {


    public AppDownloadService() {
        super("AppDownloadService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        appDownload((AppUpdateBean) intent.getParcelableExtra("bean"));
    }


    boolean isStart = true;

    /**
     * 下载app
     *
     * @param updateBean
     */
    public void appDownload(final AppUpdateBean updateBean) {
        Logger.d("---start download ---");
        final Intent intent = new Intent(AppDownloadService.this, AppUpdateBroadcastReceiver.class);//注册下载通知栏广播监听者
        intent.setAction(AppUpdateBroadcastReceiver.APP_UPDATE_ACTION);//广播action 标识
        OkHttpUtils.get().url(updateBean.getUrl()).build().execute(new FileCallBack(GlobalConstants.DOWNLOAD_PATH, updateBean.getName() + ".apk") {
            @Override
            public void inProgress(float progress, long total) {
                if (isStart) {//准备下载
                    intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG, AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG_START);
                    isStart = false;
                } else {//正在下载
                    intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG, AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG_PROGRESS);
                }
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TOTAL, total);
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_PROGRESS, progress);
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_NAME, updateBean.getName());
                sendBroadcast(intent);//发送下载信息广播
            }

            @Override
            public void onResponse(File response) {
                //下载完成
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG, AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG_DONE);
                intent.putExtra(AppUpdateBroadcastReceiver.APP_INSTALL_URL, GlobalConstants.DOWNLOAD_PATH + "/" + updateBean.getName() + ".apk");
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_NAME, updateBean.getName());
                sendBroadcast(intent);
            }
        });
    }


}
