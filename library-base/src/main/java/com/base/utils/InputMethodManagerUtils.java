package com.base.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 输入法键盘管理
* @ClassName: InputMethodManagerUtils
* @Description: 用一句话描述该文件做什么
* @author tanlifei 
* @date 2015年3月16日 下午1:20:39
*
 */
@SuppressWarnings("static-access")
public class InputMethodManagerUtils {
	/**
	 * 隐藏键盘
	* @Title: dismiss 
	* @Description: 用一句话描述该文件做什么
	* @param context
	* @param v void 返回类型  
	* @throws:throws
	 */
	public static void hideSoftInput(Context context, View v) {
		((InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
				v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
	

	/**
	 * 显示键盘
	* @Title: toggleSoftInput 
	* @Description: 用一句话描述该文件做什么
	* @param context void 返回类型  
	* @throws:throws
	 */
	public static void toggleSoftInput(Context context) {
		((InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
