package com.tanlifei.support.okhttp.builder;


import com.google.gson.Gson;
import com.tanlifei.support.constants.fixed.LoggerConstants;
import com.tanlifei.support.okhttp.request.GetRequest;
import com.tanlifei.support.okhttp.request.RequestCall;
import com.tanlifei.support.utils.Logger;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by tanlifei on 15/12/14.
 */
public class GetBuilder extends OkHttpRequestBuilder implements HasParamsable
{
    @Override
    public RequestCall build()
    {
        if (params != null)
        {
            url = appendParams(url, params);
        }

        return new GetRequest(url, tag, params, headers).build();
    }

    protected String appendParams(String url, Map<String, String> params)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public GetBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public GetBuilder tag(Object tag)
    {
        this.tag = tag ;
        return this;
    }

    @Override
    public GetBuilder params(Map<String, String> params)
    {
        this.params = params;
        Logger.json(LoggerConstants.OKHTTP_PARAMS,new Gson().toJson(params));
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        Logger.json(LoggerConstants.OKHTTP_PARAMS,"{'"+key+"':'"+val+"'}");
        return this;
    }

    public GetBuilder paramsForJson(Map<String, Object> mapParams)
    {
        if (this.params == null)
        {
            this.params = new LinkedHashMap<>();
        }
        this.params.put("json", new Gson().toJson(mapParams));
        Logger.json(LoggerConstants.OKHTTP_PARAMS,new Gson().toJson(mapParams));
        return this;
    }

    @Override
    public GetBuilder addParamsForJson(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put("json", "{'"+key+"':'"+val+"'}");
        Logger.json(LoggerConstants.OKHTTP_PARAMS,"{'"+key+"':'"+val+"'}");
        return this;
    }

    @Override
    public GetBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }
}
