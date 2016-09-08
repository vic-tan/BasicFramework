package com.tanlifei.common.ui.activity.refreshview;

import com.base.adapter.abslistview.AbsCommonAdapter;

/**
 * AbsListView 列表刷新基类，继承基本
 * Created by tanlifei on 16/8/18.
 */
public abstract class BaseAbsRefreshActivity extends BaseRefreshActivity {


    protected AbsCommonAdapter mRefreshAdapter;

    protected void supperInit() {
        super.supperInit();
        mRefreshAdapter = setRefreshAdapter();
    }

    @Override
    public void after() {
        mRefreshAdapter.notifyDataSetChanged();
    }


    public abstract AbsCommonAdapter setRefreshAdapter();


}
