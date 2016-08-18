package com.tanlifei.common.base.refreshview.interactor;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by tanlifei on 16/5/12.
 */
public interface IRefreshViewInteractor {

    /**
     * 不分页加载
     */
    interface OnLoadDataListener {//请求首页加载接口回调
         void onBefore(Request request) ;
         void onError(Call call, Exception e,boolean fromStart);
         void onResponse(String response) ;
         void onAfter() ;
    }

    /**
     * 分页加载
     */
    interface OnLoadPageDataListener {//请求首页加载接口回调
        void onPageBefore(Request request) ;
        void onPageError(Call call, Exception e,boolean fromStart);
        void onPageResponse(String response) ;
        void onPageAfter() ;
    }

    /**
     * 分页加载数据
     * @param url
     * @param map
     * @param listener
     */
    void requestPageData(String url,Map<String, String> map, boolean fromStart, OnLoadPageDataListener listener);

    /**
     * 不分显示数据
     * @param url
     * @param map
     * @param listener
     */
    void requestData(String url,Map<String, String> map, boolean fromStart, OnLoadDataListener listener);
}
