package com.tanlifei.support.okhttp.request;


import com.tanlifei.support.exception.AppException;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by tanlifei on 15/12/14.
 */
public class PostStringRequest extends OkHttpRequest
{
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String content;
    private MediaType mediaType;


    public PostStringRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, String content, MediaType mediaType)
    {
        super(url, tag, params, headers);
        this.content = content;
        this.mediaType = mediaType;

        if (this.content == null)
        {
            AppException.illegalArgument("the content can not be null !");
        }
        if (this.mediaType == null)
        {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }

    }

    @Override
    protected RequestBody buildRequestBody()
    {
        return RequestBody.create(mediaType, content);
    }

    @Override
    protected Request buildRequest(RequestBody requestBody)
    {
        return builder.post(requestBody).build();
    }


}
