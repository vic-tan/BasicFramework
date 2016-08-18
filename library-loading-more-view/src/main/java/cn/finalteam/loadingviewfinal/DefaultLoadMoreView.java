package cn.finalteam.loadingviewfinal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.finalteam.loadingviewfinal.loadingview.R;

/**
 * Desction:
 * Author:pengjianbo
 * Date:16/3/1 下午7:19
 */
public class DefaultLoadMoreView extends RelativeLayout implements ILoadMoreView {
    private RotateAnimation mFlipAnimation;
    private TextView mTvMessage;
    private View mPbLoading;
    private int mRotateAniTime = 800;

    public DefaultLoadMoreView(Context context) {
        super(context);
        init(context);
    }

    public DefaultLoadMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DefaultLoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.loading_view_final_footer_default, this);
        ;
        mPbLoading = findViewById(R.id.pb_loading);
        mTvMessage = (TextView) findViewById(R.id.tv_loading_msg);
        buildAnimation();
    }

    @Override
    public void showNormal() {
        mPbLoading.setVisibility(View.GONE);
        mPbLoading.clearAnimation();
        mTvMessage.setText(R.string.loading_view_click_loading_more);
    }

    @Override
    public void showNoMore() {
        mPbLoading.setVisibility(View.GONE);
        mPbLoading.clearAnimation();
        mTvMessage.setText(R.string.loading_view_no_more);
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
        mPbLoading.startAnimation(mFlipAnimation);
        mTvMessage.setText(R.string.loading_view_loading);
    }

    @Override
    public void showFail() {
        mPbLoading.setVisibility(View.GONE);
        mPbLoading.clearAnimation();
        mTvMessage.setText(R.string.loading_view_net_error);


    }

    @Override
    public View getFooterView() {
        return this;
    }

    private void buildAnimation() {
        mFlipAnimation = new RotateAnimation(0, 280, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(mRotateAniTime);
        mFlipAnimation.setFillAfter(true);

        /*mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(mRotateAniTime);
        mReverseFlipAnimation.setFillAfter(true);*/
    }
}
