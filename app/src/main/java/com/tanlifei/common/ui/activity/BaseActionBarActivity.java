package com.tanlifei.common.ui.activity;


import android.os.Bundle;
import android.view.View;

import com.tanlifei.framework.R;
import com.tanlifei.support.utils.ViewFindUtils;
import com.tanlifei.support.view.actionbar.ActionBarView;
import com.tanlifei.support.view.actionbar.OnBackClickListener;

/**
 * Created by tanlifei on 15/12/17.
 */
public abstract class BaseActionBarActivity extends BaseActivity {

    protected ActionBarView actionBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void initActionBar() {
        actionBarView = ViewFindUtils.find(this, R.id.actionbar);
        actionBarView.setOnBackClickListener(new OnBackClickListener() {
            @Override
            public void onClick(View v) {
                actionBack();
            }
        });
    }

}
