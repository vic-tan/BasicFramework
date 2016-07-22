package com.tanlifei.common.ui.activity;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tanlifei.common.bean.ActBean;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.StartActUtils;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by tanlifei on 15/12/17.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    protected Context mContext;
    protected TextView mActionbarOpt;
    protected TextView mActionbarTitle;
    protected LinearLayout mActionbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        baseRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        mContext = this;
    }


    protected void initActionBar() {
        mActionbarTitle = (TextView) findViewById(R.id.actionbar_tv_title);
        mActionbarTitle.setText(setActionBarTitle());
        mActionbarOpt = (TextView) findViewById(R.id.actionbar_right_opt);
        mActionbarBack = (LinearLayout) findViewById(R.id.actionbar_rlly_lift);
        if (null != mActionbarBack) {
            mActionbarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionBack();
                }
            });
        }
    }

    protected abstract Class<?> childClassName();

    /**
     * 显示横竖屏
     * ActivityInfo.SCREEN_ORIENTATION_PORTRAIT 只能竖屏
     * -1，可以横竖屏
     */
    protected void baseRequestedOrientation(int requestedOrientation) {
        if (requestedOrientation == -1) {
            return;
        }else {
            setRequestedOrientation(requestedOrientation);
        }
    }

    /**
     * 返回操作 子类可以覆盖此方法做特殊业务
     */
    protected void actionBack() {
        StartActUtils.finish(new ActBean(BaseActivity.this, childClassName()));
    }

    /**
     * 设置标题
     *
     * @return
     */
    protected String setActionBarTitle() {
        return "请设置标题";
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        actionBack();
    }

    /**
     * 当右边有操作按钮时， 重新设置标题右对齐方式，默认为了居中，向右添加140px 与返回键宽一样
     */
    protected void reSetActionbarTitlemargins() {
        if (null != mActionbarTitle) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mActionbarTitle.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            mActionbarTitle.requestLayout();
        }
    }

    /**
     * 退出App
     */
    public void exitApp() {

    }


}
