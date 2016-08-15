package com.tanlifei.exemple.baseadapter;

import android.os.Bundle;
import android.view.View;

import com.tanlifei.common.bean.params.ActParams;
import com.tanlifei.common.ui.activity.BaseActionBarActivity;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.StartActUtils;

public class ExempleBaseAdpterMainActivity extends BaseActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exemple_activity_base_adapter_main);
        initActionBar();
        actionBarView.setActionbarTitle("公用Adpter");
    }

    @Override
    protected Class<?> childClassName() {
        return ExempleBaseAdpterMainActivity.class;
    }

    public void A(View v) {
        StartActUtils.start(new ActParams(mContext, ExempleBaseAdpterMutliItemTypeActivity.class));
    }

    public void B(View v) {
        StartActUtils.start(new ActParams(mContext, ExempleBaseAdapterSingleItemTypeActivity.class));
    }


}
