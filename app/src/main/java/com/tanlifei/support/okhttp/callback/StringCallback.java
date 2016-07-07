package com.tanlifei.support.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by tanlifei on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{

    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return replaceId(new String(response.body().string()));
    }

}
