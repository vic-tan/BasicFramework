package com.tanlifei.common.base.refreshview.interactor.impl;

import com.tanlifei.common.base.refreshview.interactor.IRefreshViewInteractor;
import com.tanlifei.support.okhttp.OkHttpUtils;
import com.tanlifei.support.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by tanlifei on 16/7/28.
 */
public class RefreshViewInteractorImpl implements IRefreshViewInteractor{

    @Override
    public void requestPageData(String url, Map<String, String> map, final OnLoadPageDataListener listener) {

        OkHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request) {
                listener.onPageBefore(request);
            }

            @Override
            public void onError(Call call, Exception e) {
                listener.onPageError(call,e);
            }


            @Override
            public void onResponse(String response) {
                listener.onPageResponse(response);
            }

            @Override
            public void onAfter() {
                listener.onPageAfter();
            }
        });

    }

    @Override
    public void requestData(String url, Map<String, String> map, final OnLoadDataListener listener) {
        OkHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {

            @Override
            public void onBefore(Request request) {
                listener.onBefore(request);
            }

            @Override
            public void onError(Call call, Exception e) {
                listener.onError(call,e);
            }


            @Override
            public void onResponse(String response) {
                listener.onResponse(response);
            }

            @Override
            public void onAfter() {
                listener.onAfter();
            }
        });
    }
}
