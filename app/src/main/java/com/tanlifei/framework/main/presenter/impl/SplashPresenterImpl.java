package com.tanlifei.framework.main.presenter.impl;

import android.content.Context;
import android.os.Handler;

import com.tanlifei.framework.main.presenter.ISplashPresenter;
import com.tanlifei.framework.main.ui.activity.SplashActivity;
import com.tanlifei.framework.main.ui.view.SplashView;
import com.tanlifei.support.utils.AppCacheUtils;


/**
 * Created by tanlifei on 16/5/12.
 */
public class SplashPresenterImpl implements ISplashPresenter {

    public static final String FIRST_LAUNCHER_APP_TAG = "first_splash_app_tag";//保存第一次启动app的key

    private SplashView launcher;
    private Context mContext;

    public SplashPresenterImpl(SplashView launcherView, Context mContext) {
        this.launcher = launcherView;
        this.mContext = mContext;
    }

    @Override
    public void delayedStart(Handler handler) {
        if (AppCacheUtils.getInstance(mContext).getBoolean(FIRST_LAUNCHER_APP_TAG, true)) {//第一次打开应用
            handler.sendMessageDelayed(handler.obtainMessage(SplashActivity.HANDLE_WHAT_ZERO), SplashActivity.DELAYED);
        } else {
            if (SplashActivity.IS_HAS_LOADING_DATA)//有加载数据页时过程
                handler.sendMessageDelayed(handler.obtainMessage(SplashActivity.HANDLE_WHAT_ONE), SplashActivity.DELAYED);
            else
                handler.sendMessageDelayed(handler.obtainMessage(SplashActivity.HANDLE_WHAT_TWO), SplashActivity.DELAYED);
        }
    }
}
