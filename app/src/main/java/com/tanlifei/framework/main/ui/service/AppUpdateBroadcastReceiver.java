package com.tanlifei.framework.main.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tanlifei.common.bean.params.NotifyParams;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.AppUtils;
import com.tanlifei.support.utils.NotifyUtils;
import com.tanlifei.support.utils.ResUtils;
import com.tanlifei.support.utils.StringUtils;

/**
 * Created by tanlifei on 16/8/23.
 */
public class AppUpdateBroadcastReceiver extends BroadcastReceiver {
    public final static String APP_UPDATE_ACTION = "android.com.tanlifei.framework.broadcastreceiver.action.APPUPDATEACTION";
    public final static String APP_DOWNLOAD_TOTAL = "app_download_total";
    public final static String APP_DOWNLOAD_PROGRESS = "app_download_progress";
    public final static String APP_INSTALL_URL = "app_install_url";
    public final static String APP_DOWNLOAD_TAG = "app_download_tag";
    public final static String APP_DOWNLOAD_TAG_START = "app_download_tag_start";
    public final static String APP_DOWNLOAD_TAG_PROGRESS = "app_download_tag_progress";
    public final static String APP_DOWNLOAD_TAG_DONE = "app_download_tag_done";
    public final static String APP_DOWNLOAD_NAME = "app_download_name";
    private final static int notfiy_colde = 100001;
    private NotifyUtils notify;
    private NotifyParams params;
    private Context mContext;
    private String tag;

    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        tag = intent.getStringExtra(APP_DOWNLOAD_TAG);
        if (StringUtils.isEquals(APP_DOWNLOAD_TAG_START, tag)) {//开始更新
            initNotifyInfo(0, 0);
        } else if (StringUtils.isEquals(APP_DOWNLOAD_TAG_PROGRESS, tag)) {//正在更新
            updateProgress(false, intent.getFloatExtra(APP_DOWNLOAD_PROGRESS, 0), intent.getLongExtra(APP_DOWNLOAD_TOTAL, 0), intent.getStringExtra(APP_DOWNLOAD_NAME));
        } else if (StringUtils.isEquals(APP_DOWNLOAD_TAG_DONE, tag)) {//更新完成
            updateProgress(true, 0, 0, intent.getStringExtra(APP_DOWNLOAD_NAME));
            AppUtils.installNormal(mContext, intent.getStringExtra(APP_INSTALL_URL));//安装
        }
    }

    /**
     * 通知栏列表item样式
     */
    private void updateProgress(boolean isDone, float progress, long total, String name) {
        if (null == params) {
            params = new NotifyParams();
            params.setSmallIcon(R.mipmap.ic_launcher);
            params.setTicker(ResUtils.getStr(R.string.app_update_downloading_title));
            params.setTitle(name + ResUtils.getStr(R.string.app_update_downloading_title));
            params.setContent(ResUtils.getStr(R.string.app_update_downloading_desc));
            params.setVibrate(false);
            params.setLights(false);
            params.setSound(false);
        }
        if (null == notify) {
            notify = new NotifyUtils(mContext, notfiy_colde);
        }
        notify.notifyDownloadProgress(params, isDone, total, progress);

    }

    /**
     * 动态通知栏样式
     */
    private void initNotifyInfo(float progress, long total) {
        notify = new NotifyUtils(mContext, notfiy_colde);
        int smallIcon = R.mipmap.ic_launcher;
        String ticker = ResUtils.getStr(R.string.app_update_notify);
        NotifyParams np = new NotifyParams();
        np.setSmallIcon(smallIcon);
        np.setTicker(ticker);
        np.setTitle(ResUtils.getStr(R.string.app_update_downloading_title));
        np.setContent(ResUtils.getStr(R.string.app_update_downloading_desc));
        np.setVibrate(true);
        np.setLights(true);
        np.setSound(true);
        notify.notifyDownloadProgress(np, false, total, progress);
    }

}
