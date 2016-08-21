package com.tanlifei.framework.main.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.framework.R;
import com.tanlifei.framework.main.bean.AppUpdateBean;
import com.tanlifei.framework.main.ui.service.AppDownloadService;
import com.tanlifei.framework.main.ui.service.CheckAppUpdateService;
import com.tanlifei.support.utils.AppUtils;
import com.uikit.dialog.DialogTools;
import com.uikit.dialog.listener.OnBtnClickL;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 * app升级界面
 * Created by tanlifei on 16/1/19.
 */
@Fullscreen //全屏
@EActivity(R.layout.main_activity_app_update)
public class AppUpdateActivity extends BaseActivity {

    @AfterViews
    void init() {
        appUpdate((AppUpdateBean) getIntent().getParcelableExtra("bean"));

    }

    private void appUpdate(final AppUpdateBean appUpdateBean) {
        if (null != appUpdateBean && appUpdateBean.getVersion_code() > AppUtils.getVersionCode(mContext)) {
            DialogTools.getInstance(mContext).title("版本升级").content(Html.fromHtml(appUpdateBean.getDesc()).toString()).setOnBtnClickL(new OnBtnClickL() {
                @Override
                public void onBtnClick(View v, Dialog dialog) {
                    dialog.dismiss();
                }
            }, new OnBtnClickL() {
                @Override
                public void onBtnClick(View v, Dialog dialog) {
                    Intent intent = new Intent(getBaseContext(), AppDownloadService.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bean", appUpdateBean);
                    intent.putExtras(bundle);
                    startService(intent);
                    dialog.dismiss();
                }
            }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            }).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(mContext, CheckAppUpdateService.class));//查检升级服务
    }
}
