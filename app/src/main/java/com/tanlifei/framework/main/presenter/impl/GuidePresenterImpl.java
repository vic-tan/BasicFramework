package com.tanlifei.framework.main.presenter.impl;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.tanlifei.common.bean.ActBean;
import com.tanlifei.framework.main.interactor.GuideInteractor;
import com.tanlifei.framework.main.interactor.impl.GuideInteractorImpl;
import com.tanlifei.framework.main.presenter.GuidePresenter;
import com.tanlifei.framework.main.ui.activity.HomeActivity;
import com.tanlifei.framework.main.ui.activity.HomeActivity_;
import com.tanlifei.framework.main.ui.view.GuideView;
import com.tanlifei.support.utils.SPUtils;
import com.tanlifei.support.utils.StartActUtils;

import java.util.List;

/**
 * Created by tanlifei on 16/5/12.
 */
public class GuidePresenterImpl extends PagerAdapter implements GuidePresenter, ViewPager.OnPageChangeListener, View.OnClickListener{

    private GuideView guideView;
    private GuideInteractor interactor;
    private Context mContext;
    private List<View> list;

    public GuidePresenterImpl(GuideView guideView, Context mContext) {
        this.guideView = guideView;
        this.mContext = mContext;
        interactor = new GuideInteractorImpl();
    }

    @Override
    public List<View> addGuideViews() {
        return  interactor.addGuideViews(mContext,this);
    }

    @Override
    public void addGuideIndicatorViews(ViewGroup viewGroup) {
        List<View> list = interactor.addGuideIndicatorViews(mContext,this);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).setSelected(true);
            }
            viewGroup.addView(list.get(i));
        }
    }



    @Override
    public void onClick(View v) {
        gotoHomeView();
    }

    @Override
    public void gotoHomeView() {
        SPUtils.putBoolean(SplashPresenterImpl.FIRST_LAUNCHER_APP_TAG, false);//设置为已打开过该应用了
        StartActUtils.start(new ActBean(mContext, HomeActivity.TAG,HomeActivity_.class));
        StartActUtils.finish(new ActBean(mContext, HomeActivity.TAG));
    }


    @Override
    public ViewPager.OnPageChangeListener onPageChaneListener() {
        return this;
    }

    @Override
    public PagerAdapter setPagerAdapter() {
        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < guideView.guideDotsContainerView().getChildCount(); i++) {
            guideView.guideDotsContainerView().getChildAt(i).setSelected(false);
        }
        guideView.guideDotsContainerView().getChildAt(position).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public int getCount() {
        return addGuideViews().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(addGuideViews().get(position));
        return addGuideViews().get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(addGuideViews().get(position));
    }

}
