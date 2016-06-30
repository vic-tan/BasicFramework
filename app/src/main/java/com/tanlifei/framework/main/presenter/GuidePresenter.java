package com.tanlifei.framework.main.presenter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tanlifei on 16/5/12.
 */
public interface GuidePresenter {

    List<View> addGuideViews(View.OnClickListener clickListener);

    void addGuideIndicatorViews(ViewGroup viewGroup,View.OnClickListener clickListener);

}
