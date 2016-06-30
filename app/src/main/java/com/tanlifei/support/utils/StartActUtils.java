package com.tanlifei.support.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.tanlifei.common.bean.ActBean;
import com.tanlifei.framework.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * acitivity 之间的跳转
 * </ul>
 *
 * @author tanlifei
 * @date 2015-01-26 下午3:30:25
 */
public class StartActUtils {
    public static Map<String, Context> stackAct = new HashMap<>();


    /**
     * 找开activity
     *
     * @param intent
     */
    public static void start(ActBean actBean, Intent intent) {
        actManage(actBean.getContext(), actBean.getStackActKey(), true);
        actBean.getContext().startActivity(intent);
        ((Activity) actBean.getContext()).overridePendingTransition(R.anim.common_activity_start_anim, R.anim.common_activity_finish_main);
    }

    /**
     * 找开activity
     */
    public static void start(ActBean actBean) {
        start(actBean, new Intent(actBean.getContext(), actBean.getClazz()));
    }

    /**
     * 带Bundle的跳转
     *
     * @param bundle
     */
    public static void start(ActBean actBean, Bundle bundle) {
        Intent intent = new Intent(actBean.getContext(), actBean.getClazz());
        intent.putExtras(bundle);
        start(actBean, intent);
    }

    /**
     * 带一个实体参数跳转
     *
     * @param value : Parcelable
     */
    public static void start(ActBean actBean, String paramsKey, Parcelable value) {
        Intent intent = new Intent(actBean.getContext(), actBean.getClazz());
        Bundle bundle = new Bundle();
        bundle.putParcelable(paramsKey, value);
        intent.putExtras(bundle);
        start(actBean, intent);
    }


    /**
     * 带一个实体参数跳转
     *
     * @param value : serializeEntity
     */
    public static void start(ActBean actBean, String paramsKey, Serializable value) {
        Intent intent = new Intent(actBean.getContext(), actBean.getClazz());
        Bundle bundle = new Bundle();
        bundle.putSerializable(paramsKey, value);
        intent.putExtras(bundle);
        start(actBean, intent);
    }

    /**
     * 带多个参数跳转（map参数）
     *
     * @param map
     */
    public static void start(ActBean actBean, Map<String, Object> map) {
        start(actBean, mapToIntent(actBean, map));
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
    public static void forResult(ActBean actBean, Intent intent, int requestCode) {
        actManage(actBean.getContext(), actBean.getStackActKey(), true);
        ((Activity) actBean.getContext()).startActivityForResult(intent, requestCode);
    }

    /**
     * Activity 退出
     *
     * @Title: finishActivity
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static void finish(ActBean actBean) {
        actManage(actBean.getContext(), actBean.getStackActKey(), false);
        ((Activity) actBean.getContext()).finish();
        ((Activity) actBean.getContext()).overridePendingTransition(R.anim.activity_open_main, R.anim.activity_close_next);
    }

    /**
     * 通过activity绝对路径跳转，不带参数
     */
    public static void startForAbsolutePath(ActBean actBean) {
        ComponentName comp = new ComponentName(actBean.getContext().getPackageName(), actBean.getActivityAbsolutePath());
        Intent intent = new Intent();
        intent.setComponent(comp);
        start(actBean, intent);
    }

    /**
     * 通过activity绝对路径跳转，带参数
     */
    public static void startForAbsolutePath(ActBean actBean, Intent intent) {
        ComponentName comp = new ComponentName(actBean.getContext().getPackageName(), actBean.getActivityAbsolutePath());
        intent.setComponent(comp);
        start(actBean, intent);
    }


    /**
     * 把map参数转化为Intent buddle 参数
     *
     * @param map
     * @return
     */
    public static Intent mapToIntent(ActBean actBean, Map<String, Object> map) {
        Intent intent = new Intent(actBean.getContext(), actBean.getClazz());
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
     * 保存或者移除打开的act
     *
     * @param isStart
     */
    public static void actManage(Context context, String stackActKey, boolean isStart) {
        if (null == stackAct) {
            stackAct = new HashMap<>();
        }
        if (StringUtils.isEmpty(stackActKey)) {
            return;
        }
        if (isStart) {//启动activity
            if (!stackAct.containsKey(stackActKey)) {
                stackAct.put(stackActKey, context);
                return;
            }
        } else {//关闭activity
            if (stackAct.containsKey(stackActKey)) {
                stackAct.remove(stackActKey);
                return;
            }
        }
    }




}
