package com.tanlifei.common.ui.activity.refreshview;

import com.base.adapter.recycler.RvCommonAdapter;

/**
 * RecylerView 列表刷新基类，继承基本
 * Created by tanlifei on 16/8/18.
 */
public abstract class BaseRvRefreshActivity extends BaseRefreshActivity {


    protected RvCommonAdapter mRefreshAdapter;

    protected void supperInit() {
        super.supperInit();
        mRefreshAdapter = setRefreshAdapter();
    }

    @Override
    public void after() {
        mRefreshAdapter.notifyDataSetChanged();
    }


    public abstract RvCommonAdapter setRefreshAdapter();


}
