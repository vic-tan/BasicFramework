package com.tanlifei.exemple.baseadapter;

import android.os.Bundle;
import android.view.View;
import com.tanlifei.common.bean.ActBean;
import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.StartActUtils;

public class ExempleBaseAdpterMainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exemple_activity_base_adapter_main);
    }

    @Override
    protected Class<?> childClassName() {
        return ExempleBaseAdpterMainActivity.class;
    }

    public void A(View v) {
        StartActUtils.start(new ActBean(mContext, ExempleBaseAdapterSingleItemTypeActivity.class));
    }

    public void B(View v) {
        StartActUtils.start(new ActBean(mContext, ExempleBaseAdpterMutliItemTypeActivity.class));
    }


    @Override
    protected String setActionBarTitle() {
        return "公用adapter";
    }
}
