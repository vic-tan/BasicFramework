package com.tanlifei.support.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tanlifei.framework.R;

/**
 * @author tanlifei
 * @ClassName: ToastUtils
 * @date 2015-01-26 下午3:30:25
 */
public class ToastUtils {

    public static final int DURATION = 1000;

    public static void show(Context context,int resId) {
        show(context,context.getResources().getText(resId), DURATION);
    }

    public static void show(Context context, int resId, int duration) {
        show(context,context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context ,text, DURATION);
    }

    public static void show(Context context, CharSequence text, int duration) {
        View layout = InflaterUtils.inflate(context, R.layout.common_toast_layout);
        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(text);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public static void show(Context context,int resId, Object... args) {
        show(String.format(context.getResources().getString(resId), args), DURATION);
    }

    public static void show( String format, Object... args) {
        show(String.format(format, args), DURATION);
    }

    public static void show( Context context,int resId, int duration, Object... args) {
        show(String.format(context.getResources().getString(resId), args), duration);
    }



}