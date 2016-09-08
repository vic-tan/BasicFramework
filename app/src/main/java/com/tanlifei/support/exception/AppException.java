package com.tanlifei.support.exception;

import android.content.Context;

import com.base.utils.Logger;
import com.base.utils.StringUtils;
import com.tanlifei.support.constants.fixed.ExceptionConstants;
import com.base.utils.ToastUtils;

/**
 * 本app自己定义的异常
 * Created by tanlifei on 15/12/14.
 */
public class AppException extends Exception {

    public static final String TAG = "AppException";

    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }


    public AppException(Context mContext, String msgCode) {
        super(msgCode);
        if (StringUtils.isEquals(msgCode, ExceptionConstants.CODE_DATA_ERROR)) {
            ToastUtils.show(mContext, "数据异常");
        } else if (StringUtils.isEquals(msgCode, ExceptionConstants.CODE_VALUE_0014)) {
            ToastUtils.show(mContext, "在另一台设备登录");
        } else {
            ToastUtils.show(mContext, msgCode);
            Logger.e(TAG, msgCode);
        }

    }

    public AppException(Context mContext, Exception e) {
        super(e.getMessage());
        Logger.e(TAG, e.toString());
    }

}
