package com.tanlifei.framework.main.presenter.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.tanlifei.common.bean.ActBean;
import com.tanlifei.framework.main.presenter.SplashPresenter;
import com.tanlifei.framework.main.ui.activity.GuideActivity;
import com.tanlifei.framework.main.ui.activity.GuideActivity_;
import com.tanlifei.framework.main.ui.activity.HomeActivity;
import com.tanlifei.framework.main.ui.activity.LoadingActivity;
import com.tanlifei.framework.main.ui.view.SplashView;
import com.tanlifei.support.utils.SPUtils;
import com.tanlifei.support.utils.StartActUtils;


/**
 * Created by tanlifei on 16/5/12.
 */
public class SplashPresenterImpl implements SplashPresenter {

    public static final String FIRST_LAUNCHER_APP_TAG = "first_splash_app_tag";//保存第一次启动app的key
    private static final int HANDLE_WHAT_ZERO = 0;
    private static final int HANDLE_WHAT_ONE = 1;
    private static final int HANDLE_WHAT_TWO = 2;
    private static final int DELAYED = 1000;
    private static final boolean IS_HAS_LOADING_DATA = false;//是否有加载数据页的过程
    private SplashView launcher;
    private Context mContext;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLE_WHAT_ZERO://第一次打开应用,进入引导页
                    gotoGuideView();
                    break;
                case HANDLE_WHAT_ONE://进入正在加载数据页
                    gotoLoadingDataView();
                    break;
                case HANDLE_WHAT_TWO://没有正在加载数据页时,直接进入首页
                    gotoHomeView();
                    break;
            }
        }
    };


    public SplashPresenterImpl(SplashView launcherView, Context mContext) {
        this.launcher = launcherView;
        this.mContext = mContext;
    }

    @Override
    public void delayedStart() {
        if (SPUtils.getBoolean(FIRST_LAUNCHER_APP_TAG, true)) {//第一次打开应用
            handler.sendMessageDelayed(handler.obtainMessage(HANDLE_WHAT_ZERO), DELAYED);
        } else {
            if (IS_HAS_LOADING_DATA)//有加载数据页时过程
                handler.sendMessageDelayed(handler.obtainMessage(HANDLE_WHAT_ONE), DELAYED);
            else
                handler.sendMessageDelayed(handler.obtainMessage(HANDLE_WHAT_TWO), DELAYED);
        }
    }

    //不是第一次打开应用，进入首页
    public void gotoGuideView() {//进入引导页
         StartActUtils.start(new ActBean(mContext, GuideActivity.TAG,GuideActivity_.class));
         StartActUtils.finish(new ActBean(mContext,GuideActivity.TAG));
    }

    public void gotoLoadingDataView() {//进入加载资源数据页
        StartActUtils.start(new ActBean(mContext, LoadingActivity.TAG, LoadingActivity.class));
        StartActUtils.finish(new ActBean(mContext, LoadingActivity.TAG));
    }

    public void gotoHomeView() {//进入首页
         StartActUtils.start(new ActBean(mContext,HomeActivity.TAG, HomeActivity.class));
        StartActUtils.finish(new ActBean(mContext,HomeActivity.TAG));
    }


}
