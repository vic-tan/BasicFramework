package com.tanlifei.support.http;

import android.content.Context;

import com.google.gson.Gson;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.ExceptionConstants;
import com.tanlifei.support.exception.AppException;
import com.tlf.basic.support.okhttp.callback.Callback;
import com.tlf.basic.uikit.kprogresshud.KProgressHUD;
import com.tlf.basic.utils.StringUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 提示框加载基类，
 * 所有的提示框都得继承本类，
 * Created by tanlifei on 15/12/14.
 */
public abstract class DialogCallback extends Callback<BaseJson> {

    protected KProgressHUD hud;
    protected Context mContext;

    public DialogCallback(Context mContext) {
        this.mContext = mContext;
        hud = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel(mContext.getResources().getString(R.string.common_dialog_loading))
                .setCancellable(true);
    }


    @Override
    public void onAfter() {
        super.onAfter();
        hud.dismiss();
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
        hud.show();
    }

    @Override
    public void onError(Call call, Exception e) {
        super.onError(call, e);
        hud.dismiss();
        try {
            throw new AppException(mContext, e);
        } catch (AppException e1) {
            e1.printStackTrace();
        }
    }

    public abstract void onCusResponse(BaseJson response);


}
