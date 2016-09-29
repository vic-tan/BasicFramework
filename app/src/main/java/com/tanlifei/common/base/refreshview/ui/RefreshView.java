package com.tanlifei.common.base.refreshview.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.tlf.basic.support.refresh.header.PtrClassicFrameLayout;


/**
 * Created by tanlifei on 16/5/12.
 */
public interface RefreshView {

    View getDataView();
    PtrClassicFrameLayout getRefreshPtrLayoutView();
    FrameLayout getRefreshEmptyView();


}
