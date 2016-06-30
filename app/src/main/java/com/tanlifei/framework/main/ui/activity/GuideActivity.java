package com.tanlifei.framework.main.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.framework.R;
import com.tanlifei.framework.main.presenter.GuidePresenter;
import com.tanlifei.framework.main.presenter.impl.GuidePresenterImpl;
import com.tanlifei.framework.main.ui.view.GuideView;

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
public class GuideActivity extends BaseActivity implements GuideView {

    public static final  String TAG = GuideActivity.class.getSimpleName();
    @ViewById(R.id.guide_pager)
    ViewPager guidePager;
    @ViewById(R.id.guide_dots_container)
    LinearLayout guideDotsContainer;
    private GuidePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void init() {
        presenter = new GuidePresenterImpl(this, this);
        initializeViews();
        presenter.addGuideIndicatorViews(guideDotsContainer);
        guidePager.setOnPageChangeListener(presenter.onPageChaneListener());
    }


    @Override
    public ViewGroup guideDotsContainerView() {
        return guideDotsContainer;
    }

    @Override
    public void initializeViews() {
        guidePager.setAdapter(presenter.setPagerAdapter());
    }

    /**
     * 退出App
     */
    @Override
    public void onBackPressed() {
        exitApp();
    }


}
