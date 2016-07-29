package com.tanlifei.framework.main.interactor.impl;

import android.content.Context;
import android.view.View;

import com.tanlifei.framework.R;
import com.tanlifei.framework.main.interactor.IGuideInteractor;
import com.tanlifei.support.utils.InflaterUtils;
import com.tanlifei.support.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanlifei on 16/5/12.
 */
public class GuideInteractorImpl implements IGuideInteractor {

    private List<View> guideViews;
    private List<View> guideIndicatorViews;

    @Override
    public List<View> addGuideViews(Context context, View.OnClickListener listener) {
        if (ListUtils.isEmpty(guideViews)) {
            guideViews = new ArrayList<>();
            guideViews.add(InflaterUtils.inflate(context, R.layout.main_activity_guide_page_one));
            guideViews.add(InflaterUtils.inflate(context, R.layout.main_activity_guide_page_two));
            guideViews.add(InflaterUtils.inflate(context, R.layout.main_activity_guide_page_three));
            View v = InflaterUtils.inflate(context, R.layout.main_activity_guide_page_four);
            v.findViewById(R.id.imgBtn_start).setOnClickListener(listener);
            guideViews.add(v);

        }
        return guideViews;
    }


    @Override
    public List<View> addGuideIndicatorViews(Context context, View.OnClickListener listener) {
        if (ListUtils.isEmpty(guideViews)) {
            addGuideViews(context, listener);
        }
        if (ListUtils.isEmpty(guideIndicatorViews)) {
            guideIndicatorViews = new ArrayList<>();
            for (int i = 0; i < guideViews.size(); i++) {
                guideIndicatorViews.add(InflaterUtils.inflate(context, R.layout.main_activity_guide_indicator));
            }
        }
        return guideIndicatorViews;
    }
}
