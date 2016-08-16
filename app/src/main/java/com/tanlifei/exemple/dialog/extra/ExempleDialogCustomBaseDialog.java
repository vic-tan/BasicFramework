package com.tanlifei.exemple.dialog.extra;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.tanlifei.framework.R;
import com.tanlifei.support.utils.CornerUtils;
import com.tanlifei.support.utils.ViewFindUtils;
import com.tanlifei.support.widget.dialog.base.dialog.BaseDialog;


public class ExempleDialogCustomBaseDialog extends BaseDialog<ExempleDialogCustomBaseDialog> {
    private TextView mTvCancel;
    private TextView mTvExit;

    public ExempleDialogCustomBaseDialog(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView() {
        widthScale(0.85f);
        //showAnim(new Swing());

        // dismissAnim(this, new ZoomOutExit());
        View inflate = View.inflate(mContext, R.layout.exemple_dialog_dialog_custom_base, null);
        mTvCancel = ViewFindUtils.find(inflate, R.id.tv_cancel);
        mTvExit = ViewFindUtils.find(inflate, R.id.tv_exit);
        inflate.setBackground(
                CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), 10));

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
