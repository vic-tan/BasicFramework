package com.support.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.support.R;

import java.io.Serializable;
import java.util.Map;
/**
 * acitivity 之间的跳转
 * </ul>
 *
 * @author tanlifei
 * @date 2015-01-26 下午3:30:25
 */
public class StartActUtils {

    /**
     * 找开activity
     *
     * @param intent
     */
    public static void start(Context mContext, Intent intent) {
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.common_activity_start_anim, R.anim.common_activity_finish_main);
    }

    /**
     * 找开activity
     */
    public static void start(Context mContext,Class<?> clazz) {
        start(mContext,new Intent(mContext, clazz));
    }

    /**
     * 带Bundle的跳转
     *
     * @param bundle
     */
    public static void start(Context mContext,Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtras(bundle);
        start(mContext, intent);
    }

    /**
     * 带一个实体参数跳转
     *
     * @param value : Parcelable
     */
    public static void start(Context mContext,Class<?> clazz, String paramsKey, Parcelable value) {
        Intent intent = new Intent(mContext, clazz);
        Bundle bundle = new Bundle();
        bundle.putParcelable(paramsKey, value);
        intent.putExtras(bundle);
        start(mContext, intent);
    }


    /**
     * 带一个实体参数跳转
     *
     * @param value : serializeEntity
     */
    public static void start(Context mContext,Class<?> clazz, String paramsKey, Serializable value) {
        Intent intent = new Intent(mContext, clazz);
        Bundle bundle = new Bundle();
        bundle.putSerializable(paramsKey, value);
        intent.putExtras(bundle);
        start(mContext, intent);
    }

    /**
     * 带多个参数跳转（map参数）
     *
     * @param map
     */
    public static void start(Context mContext, Class<?> clazz, Map<String, Object> map) {
        start(mContext, mapToIntent(mContext,clazz, map));
    }


    /**
     * 带结果的回调码开始跳转
     *
     * @param intent
     * @param requestCode void 返回类型
     * @Title: startActivityForResult
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static void forResult(Context mContext, Intent intent, int requestCode) {
        ((Activity) mContext).startActivityForResult(intent, requestCode);
    }


    /**
     * 带结果的回调码开始跳转
     *
     * @param map
     * @param requestCode void 返回类型
     * @Title: startActivityForResult
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static void forResult(Context mContext,Class<?> clazz, Map<String,Object> map, int requestCode) {
        ((Activity) mContext).startActivityForResult(mapToIntent(mContext,clazz, map), requestCode);
    }

    public static void setResult(Activity mContext, Intent intent,int requestCode){
        mContext.setResult(requestCode,intent);
    }

    public static void setResult(Activity mContext, Map<String,Object> map, int requestCode) {
        setResult(mContext,mapToIntent(map), requestCode);
    }

    /**
     * Activity 退出
     *
     * @Title: finishActivity
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static void finish(Context mContext) {
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.activity_open_main, R.anim.activity_close_next);
    }


    /**
     * 把map参数转化为Intent buddle 参数
     *
     * @param map
     * @return
     */
    public static Intent mapToIntent(Context mContext,Class<?> clazz, Map<String, Object> map) {
        Intent intent = new Intent(mContext, clazz);
        Bundle bundle = new Bundle();
        if (map != null && map.size() > 0) {
            for (String key : map.keySet()) {
                if (map.get(key) instanceof String) {
                    bundle.putString(key, (String) map.get(key));
                } else if (map.get(key) instanceof Integer) {
                    bundle.putInt(key, (Integer) map.get(key));
                } else if (map.get(key) instanceof Boolean) {
                    bundle.putBoolean(key, (Boolean) map.get(key));
                } else if (map.get(key) instanceof Double) {
                    bundle.putDouble(key, (Double) map.get(key));
                } else if (map.get(key) instanceof Long) {
                    bundle.putLong(key, (Long) map.get(key));
                } else if (map.get(key) instanceof Float) {
                    bundle.putFloat(key, (Float) map.get(key));
                } else if (map.get(key) instanceof Double) {
                    bundle.putDouble(key, (Double) map.get(key));
                } else if (map.get(key) instanceof Serializable) {
                    bundle.putSerializable(key, (Serializable) map.get(key));
                } else if (map.get(key) instanceof Parcelable) {
                    bundle.putParcelable(key, (Parcelable) map.get(key));
                }
            }
        }
        return intent.putExtras(bundle);
    }

    /**
     * 把map参数转化为Intent buddle 参数
     *
     * @param map
     * @return
     */
    public static Intent mapToIntent(Map<String, Object> map) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (map != null && map.size() > 0) {
            for (String key : map.keySet()) {
                if (map.get(key) instanceof String) {
                    bundle.putString(key, (String) map.get(key));
                } else if (map.get(key) instanceof Integer) {
                    bundle.putInt(key, (Integer) map.get(key));
                } else if (map.get(key) instanceof Boolean) {
                    bundle.putBoolean(key, (Boolean) map.get(key));
                } else if (map.get(key) instanceof Double) {
                    bundle.putDouble(key, (Double) map.get(key));
                } else if (map.get(key) instanceof Long) {
                    bundle.putLong(key, (Long) map.get(key));
                } else if (map.get(key) instanceof Float) {
                    bundle.putFloat(key, (Float) map.get(key));
                } else if (map.get(key) instanceof Double) {
                    bundle.putDouble(key, (Double) map.get(key));
                } else if (map.get(key) instanceof Serializable) {
                    bundle.putSerializable(key, (Serializable) map.get(key));
                } else if (map.get(key) instanceof Parcelable) {
                    bundle.putParcelable(key, (Parcelable) map.get(key));
                }
            }
        }
        return intent.putExtras(bundle);
    }

}
