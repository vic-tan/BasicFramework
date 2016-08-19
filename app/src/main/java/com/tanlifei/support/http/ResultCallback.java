package com.tanlifei.support.http;

import android.content.Context;

import com.google.gson.Gson;
import com.support.okhttp.callback.Callback;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.support.constants.fixed.ExceptionConstants;
import com.tanlifei.support.exception.AppException;
import com.tanlifei.support.utils.StringUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 只返回结果onCusResponse方法，忽略其它方法
 * Created by tanlifei on 15/12/14.
 */
public abstract class ResultCallback extends Callback<BaseJson>
{
    protected Context mContext;

    public ResultCallback(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onAfter() {
        super.onAfter();
    }

    @Override
    public BaseJson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        BaseJson jsonBean = new Gson().fromJson(replaceId(new String(string)), BaseJson.class);
        return jsonBean;
    }

    @Override
    public void onResponse(BaseJson response) {
        try {
            if (null == response) {
                throw new AppException(mContext, ExceptionConstants.CODE_DATA_ERROR);
            }
            if (StringUtils.isEquals(response.getCode(),ExceptionConstants.CODE_SUCCEE)){
                onCusResponse(response);
            }else{
                throw new AppException(mContext, response.getMsg());
            }
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
    }

    @Override
    public void onError(Call call, Exception e) {
        super.onError(call, e);
        try {
            throw new AppException(mContext, e);
        } catch (AppException e1) {
            e1.printStackTrace();
        }
    }

    public abstract void onCusResponse(BaseJson response);

}
