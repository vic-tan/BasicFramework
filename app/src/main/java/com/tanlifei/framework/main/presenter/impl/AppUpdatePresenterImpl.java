package com.tanlifei.framework.main.presenter.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.support.okhttp.OkHttpUtils;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.framework.main.bean.AppUpdateBean;
import com.tanlifei.framework.main.presenter.IAppUpdatePresenter;
import com.tanlifei.support.constants.fixed.UrlConstants;
import com.tanlifei.support.http.ResultCallback;
import com.tanlifei.support.utils.AppCacheUtils;
import com.tanlifei.support.utils.AppUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * app 版本升级
 * Created by tanlifei on 16/5/12.
 */
public class AppUpdatePresenterImpl implements IAppUpdatePresenter {

    public static final String CHECK_APP_UPDATE_TAG = "check_app_update_tag";//保存请求版本升级key
    private Context mContext;

    public AppUpdatePresenterImpl(Context mContext) {
        this.mContext = mContext;
    }


    public Map<String, Object> tagList() {
        Map<String, Object> map = new HashMap<>();
        map.put("sid", "ipeiban2016");
        return map;
    }

    @Override
    public void checkAppUpdate() {
        AppCacheUtils.getInstance(mContext).remove(CHECK_APP_UPDATE_TAG);
        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new ResultCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {
                response = date();
                saveAppUpdate(response);
            }
        });
    }

    private void saveAppUpdate(BaseJson baseJson){
        try {
            AppUpdateBean appUpdateBean = new Gson().fromJson(new Gson().toJson(baseJson.getData()),AppUpdateBean.class);
            if(appUpdateBean.getVersion_code()> AppUtils.getVersionCode(mContext)){//是否升级
                AppCacheUtils.getInstance(mContext).put(CHECK_APP_UPDATE_TAG,appUpdateBean);
            }else{
                AppCacheUtils.getInstance(mContext).remove(CHECK_APP_UPDATE_TAG);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    public BaseJson date() {
        String str = "{\n" +
                "    \"code\": \"0000\",\n" +
                "    \"msg\": \"操作成功\",\n" +
                "    \"data\": {\n" +
                "        \"version_code\": \"12\",\n" +
                "        \"version_name\": \"1.0.1\",\n" +
                "        \"name\": \"灵犀语音助手\",\n" +
                "        \"url\":\"http://gh-game.oss-cn-hangzhou.aliyuncs.com/1435814701749842.apk\",\n" +
                "        \"desc\":\"灵犀Android<br>\n" +
                "                3.1.2298版产品更新计划<br>\n" +
                "                一、版本信息<br>\n" +
                "                版本号：<br>\n" +
                "                Android 3.1.2298版<br>\n" +
                "                二、产品介绍<br>\n" +
                "                实现智能操控的语音助手<br>\n" +
                "                【重要机能档案】<br>\n" +
                "                官方百度贴吧：灵犀语音助手吧<br>\n" +
                "                官方微信：yidonglingxi<br>\n" +
                "                官方微博：@灵犀官方微博\"\n" +
                "    }\n" +
                "}";
        BaseJson baseJson = new Gson().fromJson(str,BaseJson.class);
        return baseJson;

    }
}
