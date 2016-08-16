package com.tanlifei.support.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanlifei.framework.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by tanlifei on 16/7/22.
 */
public class PromptView extends AutoLinearLayout{

    public ImageView getPb_loading() {
        return pb_loading;
    }

    public ImageView getIv_logo() {
        return iv_logo;
    }

    public TextView getTv_hint() {
        return tv_hint;
    }

    private ImageView pb_loading;
    private ImageView iv_logo;
    private TextView tv_hint;

    public PromptView(Context context) {
        super(context);
    }

    public PromptView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PromptView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.common_loading_prompt,this, true);
        pb_loading = (ImageView) findViewById(R.id.pb_loading);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        tv_hint = (TextView) findViewById(R.id.tv_hint);
    }
}
