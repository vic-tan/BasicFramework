package com.tanlifei.common.base.refreshview.interactor.impl;

import android.content.Context;

import com.support.okhttp.OkHttpUtils;
import com.tanlifei.common.base.refreshview.interactor.IRefreshViewInteractor;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.support.http.HttpListener;
import com.tanlifei.support.http.ProcessCallback;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by tanlifei on 16/7/28.
 */
public class RefreshViewInteractorImpl implements IRefreshViewInteractor{

    @Override
    public void requestPageData(Context context, String url, Map<String, String> map, final boolean fromStart, final OnLoadPageDataListener listener) {


        OkHttpUtils.post().url(url).params(map).build().execute(new ProcessCallback(context, new HttpListener() {
            @Override
            public void onAfter() {
                listener.onPageAfter();
            }

            @Override
            public void onBefore(Request request) {
                listener.onPageBefore(request);
            }

            @Override
            public void onError(Call call, Exception e) {
                listener.onPageError(call,e,fromStart);
            }

            @Override
            public void onCusResponse(BaseJson response) {
                listener.onPageResponse(response);
            }
        }));
    }

    @Override
    public void requestData(Context context,String url, Map<String, String> map, final boolean fromStart, final OnLoadDataListener listener) {
        OkHttpUtils.post().url(url).params(map).build().execute(new ProcessCallback(context, new HttpListener() {
            @Override
            public void onAfter() {
                listener.onAfter();
            }

            @Override
            public void onBefore(Request request) {
                listener.onBefore(request);
            }

            @Override
            public void onError(Call call, Exception e) {
                listener.onError(call, e, fromStart);
            }

            @Override
            public void onCusResponse(BaseJson response) {
                listener.onResponse(response);
            }
        }));
    }
}
