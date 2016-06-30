package com.tanlifei.framework.main.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.tanlifei.common.bean.ActBean;
import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.framework.R;
import com.tanlifei.framework.main.GuideAdapter;
import com.tanlifei.framework.main.presenter.GuidePresenter;
import com.tanlifei.framework.main.presenter.impl.GuidePresenterImpl;
import com.tanlifei.framework.main.presenter.impl.SplashPresenterImpl;
import com.tanlifei.framework.main.ui.view.GuideView;
import com.tanlifei.support.utils.SPUtils;
import com.tanlifei.support.utils.StartActUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

/**
 * 第一次启动引导界面
 * Created by tanlifei on 16/1/19.
 */

@Fullscreen //全屏
@EActivity(R.layout.main_activity_guide)
public class GuideActivity extends BaseActivity implements GuideView,
        ViewPager.OnPageChangeListener, View.OnClickListener {

    public static final String TAG = GuideActivity.class.getSimpleName();
    @ViewById(R.id.guide_pager)
    ViewPager guidePager;
    @ViewById(R.id.guide_dots_container)
    LinearLayout guideDotsContainer;
    private GuidePresenter presenter;

    @AfterViews
    void init() {
        presenter = new GuidePresenterImpl(this, mContext);
        presenter.addGuideIndicatorViews(guideDotsContainer, this);
        guidePager.setAdapter(new GuideAdapter(presenter.addGuideViews(this)));
    }

    @Override
    public void onClick(View v) {
        SPUtils.putBoolean(SplashPresenterImpl.FIRST_LAUNCHER_APP_TAG, false);//设置为已打开过该应用了
        StartActUtils.start(new ActBean(mContext, HomeActivity.TAG, HomeActivity_.class));
        StartActUtils.finish(new ActBean(mContext, TAG));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < guideDotsContainer.getChildCount(); i++) {
            guideDotsContainer.getChildAt(i).setSelected(false);
        }
        guideDotsContainer.getChildAt(position).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 退出App
     */
    @Override
    public void onBackPressed() {
        exitApp();
    }


}
