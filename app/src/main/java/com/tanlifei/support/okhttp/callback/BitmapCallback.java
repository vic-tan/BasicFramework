package com.tanlifei.support.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * Created by tanlifei on 15/12/14.
 */
public abstract class BitmapCallback extends Callback<Bitmap>
{

    @Override
    public Bitmap parseNetworkResponse(Response response) throws Exception
    {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}
