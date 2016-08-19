package com.tanlifei.uikit.dialog.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.support.autolayout.utils.AutoUtils;
import com.tanlifei.support.utils.ViewFindUtils;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.GlobalConstants;
import com.tanlifei.support.utils.CornerUtils;
import com.tanlifei.support.utils.ResUtils;
import com.tanlifei.uikit.dialog.base.dialog.BaseDialog;


/**
 * 自定义发现新的版本的提示框
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public abstract class NormalScrollViewDialog extends BaseDialog<NormalScrollViewDialog> {
    private TextView mTvOk,mTvExit;
    private TextView mTvContent;

    public NormalScrollViewDialog(Context context) {
        super(context);
    }


    @Override
    public View onCreateView() {
        widthScale(GlobalConstants.DIALOG_NORMAL_DIALOG_SCALE);
        View inflate = View.inflate(mContext, R.layout.common_dialog_normal_scroll_view, null);
        AutoUtils.autoSize(inflate);
        mTvContent = ViewFindUtils.find(inflate,R.id.tv_content);
        mTvContent.setLineSpacing(0, GlobalConstants.DIALOG_WONDWON_CONTENT_LINE_SPACING);
        mTvOk = ViewFindUtils.find(inflate, R.id.tv_ok);
        mTvExit = ViewFindUtils.find(inflate, R.id.tv_exit);
        setSelector(mTvOk);
        setSelector(mTvExit);
        inflate.setBackground(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), ResUtils.getDimens(R.dimen.common_border_radius)));
        return inflate;
    }

    public void setSelector(TextView v){
        v.setBackground(CornerUtils.btnSelector((int) ResUtils.getDimens(R.dimen.common_border_radius), Color.parseColor("#ffffff"), Color.parseColor("#E3E3E3"), -2));
    }


    public TextView getmTvOk() {
        return mTvOk;
    }

    public TextView getmTvContent() {
        return mTvContent;
    }

    public TextView getmTvExit() {
        return mTvExit;
    }
}
