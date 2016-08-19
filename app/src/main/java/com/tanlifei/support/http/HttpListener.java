package com.tanlifei.support.http;

import com.tanlifei.common.bean.BaseJson;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by tanlifei on 16/8/19.
 */
public interface HttpListener {
    void onAfter() ;
    void onBefore(Request request);
    void onError(Call call, Exception e);
    void onCusResponse(BaseJson response);
}
