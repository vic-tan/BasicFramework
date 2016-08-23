package com.tanlifei.framework.main.ui.service;

import android.app.IntentService;
import android.content.Intent;

import com.support.okhttp.OkHttpUtils;
import com.tanlifei.framework.main.bean.AppUpdateBean;
import com.tanlifei.support.constants.fixed.GlobalConstants;
import com.tanlifei.support.http.FileCallBack;

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


        final Intent intent = new Intent(AppDownloadService.this, AppUpdateBroadcastReceiver.class);
        intent.setAction(AppUpdateBroadcastReceiver.APP_UPDATE_ACTION);
        OkHttpUtils.get().url(updateBean.getUrl()).build().execute(new FileCallBack(GlobalConstants.DOWNLOAD_PATH, updateBean.getName() + ".apk") {
            @Override
            public void inProgress(float progress, long total) {
                // updateProgress(false, progress, total, updateBean);
                if (isStart) {
                    intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG, AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG_START);
                    isStart = false;
                } else {
                    intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG, AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG_PROGRESS);
                }
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TOTAL, total);
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_PROGRESS, progress);
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_NAME,updateBean.getName());
                sendBroadcast(intent);
            }

            @Override
            public void onResponse(File response) {
                //updateProgress(true, 0, 0, updateBean);
                // AppUtils.installNormal(AppDownloadService.this, GlobalConstants.DOWNLOAD_PATH + "/" + updateBean.getName() + ".apk");
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG, AppUpdateBroadcastReceiver.APP_DOWNLOAD_TAG_DONE);
                intent.putExtra(AppUpdateBroadcastReceiver.APP_INSTALL_URL, GlobalConstants.DOWNLOAD_PATH + "/" + updateBean.getName() + ".apk");
                intent.putExtra(AppUpdateBroadcastReceiver.APP_DOWNLOAD_NAME,updateBean.getName());
                sendBroadcast(intent);
            }
        });
    }


}
