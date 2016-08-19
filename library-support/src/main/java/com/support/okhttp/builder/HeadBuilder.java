package com.support.okhttp.builder;


import com.support.okhttp.OkHttpUtils;
import com.support.okhttp.request.OtherRequest;
import com.support.okhttp.request.RequestCall;

/**
 * Created by tanlifei on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
