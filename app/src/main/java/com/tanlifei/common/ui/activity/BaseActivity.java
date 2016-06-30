package com.tanlifei.common.ui.activity;


import android.content.Context;
import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by tanlifei on 15/12/17.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    /**
     * 退出App
     */
    public void exitApp() {

    }


}
