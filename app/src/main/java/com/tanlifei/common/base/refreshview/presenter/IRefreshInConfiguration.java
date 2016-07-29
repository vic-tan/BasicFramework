package com.tanlifei.common.base.refreshview.presenter;

import android.widget.BaseAdapter;

import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.bean.PageBean;

import java.util.List;

/**
 * Created by tanlifei on 16/7/28.
 */
public interface IRefreshInConfiguration {


    /**
     * json解析的类对象,通过fastjson 解析json时反射出相应的实体bean，所以要传入一个实体类对象
     *
     * @return Class<?> 要解析的实体
     */
    Class<?> parseClassName();


    /**
     * 手动解析json
     *
     * @param baseJson 请求的回来的baseJson
     * @param pageBean 类型，上拉还是下拉
     */
    void customParseJson(BaseJson baseJson, PageBean pageBean);


    /**
     * 设置Adapter
     *
     * @return ListAdapter 返回类型
     */
    BaseAdapter getAdapter();


    /**
     * 设置装载容器list
     */
    List getList();
}
