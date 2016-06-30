package com.tanlifei.framework.main.presenter.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tanlifei.framework.main.interactor.GuideInteractor;
import com.tanlifei.framework.main.interactor.impl.GuideInteractorImpl;
import com.tanlifei.framework.main.presenter.GuidePresenter;
import com.tanlifei.framework.main.ui.view.GuideView;

import java.util.List;

/**
 * Created by tanlifei on 16/5/12.
 */
public class GuidePresenterImpl implements GuidePresenter {

    private GuideView guideView;
    private GuideInteractor interactor;
    private Context mContext;

    public GuidePresenterImpl(GuideView guideView, Context mContext) {
        this.guideView = guideView;
        this.mContext = mContext;
        interactor = new GuideInteractorImpl();
    }

    @Override
    public List<View> addGuideViews(View.OnClickListener clickListener) {
        return interactor.addGuideViews(mContext, clickListener);
    }

    @Override
    public void addGuideIndicatorViews(ViewGroup viewGroup, View.OnClickListener clickListener) {
        List<View> list = interactor.addGuideIndicatorViews(mContext, clickListener);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).setSelected(true);
            }
            viewGroup.addView(list.get(i));
        }
    }

}
