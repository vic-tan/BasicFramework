package com.tanlifei.exemple.okhttp;

import android.os.Bundle;
import android.view.View;

import com.support.okhttp.OkHttpUtils;
import com.support.utils.Logger;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.ui.activity.actionbar.BaseActionBarActivity;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.UrlConstants;
import com.tanlifei.support.http.DialogCallback;
import com.tanlifei.support.http.HttpListener;
import com.tanlifei.support.http.ProcessCallback;
import com.tanlifei.support.http.ResultCallback;
import com.tanlifei.support.utils.NetUtils;
import com.support.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

public class ExempleOkHttpMainActivity extends BaseActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exemple_activity_okhttp_main);
        initActionBar();
        actionBarView.setActionbarTitle("Http请求示例");
    }


    public void A(View v) {
        if (!NetUtils.isConnected(mContext)) {
            ToastUtils.show(mContext, "没有网络!!");
            return;
        }
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new ResultCallback(mContext) {

            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext, response.getData().toString());
            }


        });
    }

    public void B(View v) {
        if (!NetUtils.isConnected(mContext)) {
            ToastUtils.show(mContext, "没有网络!!");
            return;
        }
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new DialogCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext, response.getData() + "");
            }
        });
    }

    public void C(View v) {
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new ProcessCallback(mContext, new HttpListener() {
            @Override
            public void onAfter() {
                Logger.d("onAfter");
                ToastUtils.show(mContext, "onAfter");
            }

            @Override
            public void onBefore(Request request) {
                Logger.d("onBefore");
                ToastUtils.show(mContext, "onBefore");
            }

            @Override
            public void onError(Call call, Exception e) {
                Logger.d("onError");
                ToastUtils.show(mContext, "onError");
            }

            @Override
            public void onCusResponse(BaseJson response) {
                Logger.d("onCusResponse");
                ToastUtils.show(mContext, "onCusResponse");
            }
        }));
    }


    public Map<String, Object> tagList() {
        Map<String, Object> map = new HashMap<>();
        map.put("sid", "ipeiban2016");
        return map;
    }


}
