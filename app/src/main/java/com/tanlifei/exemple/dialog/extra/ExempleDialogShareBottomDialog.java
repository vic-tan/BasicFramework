package com.tanlifei.exemple.dialog.extra;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.tanlifei.framework.R;
import com.tanlifei.support.utils.ToastUtils;
import com.tanlifei.support.utils.ViewFindUtils;
import com.tanlifei.support.view.dialog.base.dialog.BottomBaseDialog;


public class ExempleDialogShareBottomDialog extends BottomBaseDialog<ExempleDialogShareBottomDialog> {
    private LinearLayout mLlWechatFriendCircle;
    private LinearLayout mLlWechatFriend;
    private LinearLayout mLlQq;
    private LinearLayout mLlSms;

    public ExempleDialogShareBottomDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public ExempleDialogShareBottomDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        //showAnim(new FlipVerticalSwingEnter());
       // dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.exemple_dialog_dialog_share, null);
        mLlWechatFriendCircle = ViewFindUtils.find(inflate, R.id.ll_wechat_friend_circle);
        mLlWechatFriend = ViewFindUtils.find(inflate, R.id.ll_wechat_friend);
        mLlQq = ViewFindUtils.find(inflate, R.id.ll_qq);
        mLlSms = ViewFindUtils.find(inflate, R.id.ll_sms);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        mLlWechatFriendCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext,"朋友圈");
                dismiss();
            }
        });
        mLlWechatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext,"微信");
                dismiss();
            }
        });
        mLlQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext,"QQ");
                dismiss();
            }
        });
        mLlSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "短信");
                dismiss();
            }
        });
    }
}
