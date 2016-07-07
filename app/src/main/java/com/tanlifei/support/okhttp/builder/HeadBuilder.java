package com.tanlifei.support.okhttp.builder;


import com.tanlifei.support.okhttp.OkHttpUtils;
import com.tanlifei.support.okhttp.request.OtherRequest;
import com.tanlifei.support.okhttp.request.RequestCall;

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
