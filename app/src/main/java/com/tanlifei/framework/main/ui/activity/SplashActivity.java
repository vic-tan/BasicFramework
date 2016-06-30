package com.tanlifei.framework.main.ui.activity;

import android.os.Handler;
import android.os.Message;

import com.tanlifei.common.bean.ActBean;
import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.framework.R;
import com.tanlifei.framework.main.presenter.SplashPresenter;
import com.tanlifei.framework.main.presenter.impl.SplashPresenterImpl;
import com.tanlifei.framework.main.ui.view.SplashView;
import com.tanlifei.support.utils.StartActUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 * 启动界面
 * Created by tanlifei on 16/1/19.
 */
@Fullscreen //全屏
@EActivity(R.layout.main_activity_splash)
public class SplashActivity extends BaseActivity implements SplashView {
    public static final String TAG = SplashActivity.class.getSimpleName();
    public static final int HANDLE_WHAT_ZERO = 0;
    public static final int HANDLE_WHAT_ONE = 1;
    public static final int HANDLE_WHAT_TWO = 2;
    public static final int DELAYED = 3000;
    public static final boolean IS_HAS_LOADING_DATA = false;//是否有加载数据页的过程
    private SplashPresenter presenter;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLE_WHAT_ZERO://第一次打开应用,进入引导页
                    start(GuideActivity.TAG, GuideActivity_.class);//进入引导页
                    break;
                case HANDLE_WHAT_ONE://进入正在加载数据页
                    start(LoadingActivity.TAG, LoadingActivity.class);//进入加载资源数据页
                    break;
                case HANDLE_WHAT_TWO://没有正在加载数据页时,直接进入首页
                    start(HomeActivity.TAG, HomeActivity_.class);//进入首页
                    break;
            }
        }
    };

    @AfterViews
    void init() {
        presenter = new SplashPresenterImpl(this, this);
        presenter.delayedStart(handler);
    }

    private void start(String stackActKey,Class clazz){
        StartActUtils.start(new ActBean(mContext, stackActKey, clazz));
        StartActUtils.finish(new ActBean(mContext, TAG));
    }
}
