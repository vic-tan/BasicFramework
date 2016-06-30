package com.tanlifei.framework.main.presenter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tanlifei on 16/5/12.
 */
public interface GuidePresenter {

    List<View> addGuideViews();

    void addGuideIndicatorViews(ViewGroup viewGroup);

    void gotoHomeView();

    ViewPager.OnPageChangeListener onPageChaneListener();

    PagerAdapter setPagerAdapter();

}
