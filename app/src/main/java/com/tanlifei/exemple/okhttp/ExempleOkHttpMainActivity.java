package com.tanlifei.exemple.okhttp;

import android.os.Bundle;
import android.view.View;

import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.ui.activity.BaseActionBarActivity;
import com.tanlifei.framework.R;
import com.tanlifei.support.okhttp.OkHttpUtils;
import com.tanlifei.support.okhttp.callback.Callback;
import com.tanlifei.support.okhttp.callback.loading.LoadingStandardCallback;
import com.tanlifei.support.utils.NetUtils;
import com.tanlifei.support.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class ExempleOkHttpMainActivity extends BaseActionBarActivity {


    String url = "http://2ctest.zhixueyun.com/zxy-mobile/ask/tagList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exemple_activity_okhttp_main);
        initActionBar();
        actionBarView.setActionbarTitle("OKHttp请求示例");
    }


    public void A(View v) {
        if (!NetUtils.isConnected(mContext)) {
            ToastUtils.show(mContext, "没有网络!!");
            return;
        }
        OkHttpUtils.post().url(url).paramsForJson(tagList()).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response) throws Exception {
                return response.body();
            }

            @Override
            public void onResponse(Object response) {
                ToastUtils.show(mContext, response.toString());
            }
        });
    }

    public void B(View v) {
        if (!NetUtils.isConnected(mContext)) {
            ToastUtils.show(mContext, "没有网络!!");
            return;
        }
        OkHttpUtils.post().url(url).paramsForJson(tagList()).build().execute(new LoadingStandardCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext, response.getData() + "");
            }
        });
    }

    public void C(View v) {
        if (!NetUtils.isConnected(mContext)) {
            ToastUtils.show(mContext, "没有网络!!");
            return;
        }
        OkHttpUtils.post().url(url).paramsForJson(tagList()).build().execute(new LoadingStandardCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {
                ToastUtils.show(mContext, response.getData() + "");
            }
        });
    }


    public Map<String, Object> tagList() {
        Map<String, Object> map = new HashMap<>();
        map.put("sid", "ipeiban2016");
        return map;
    }
}
