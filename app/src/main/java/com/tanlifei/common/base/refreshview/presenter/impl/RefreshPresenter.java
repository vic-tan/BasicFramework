package com.tanlifei.common.base.refreshview.presenter.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.tanlifei.common.base.refreshview.interactor.IRefreshViewInteractor;
import com.tanlifei.common.base.refreshview.interactor.impl.RefreshViewInteractorImpl;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInConfiguration;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInPresenter;
import com.tanlifei.common.base.refreshview.ui.EmptyView;
import com.tanlifei.common.base.refreshview.ui.RefreshView;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.bean.BasePageListBean;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.JsonConstants;
import com.tanlifei.support.utils.GsonJsonUtils;
import com.tanlifei.support.utils.ListUtils;
import com.tanlifei.support.utils.NetUtils;
import com.tanlifei.support.utils.ToastUtils;

import java.util.List;
import java.util.Map;

import cn.finalteam.loadingviewfinal.GridViewFinal;
import cn.finalteam.loadingviewfinal.ListViewFinal;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import cn.finalteam.loadingviewfinal.ScrollViewFinal;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by tanlifei on 16/7/28.
 */
public class RefreshPresenter implements IRefreshInPresenter,
        IRefreshViewInteractor.OnLoadPageDataListener, IRefreshViewInteractor.OnLoadDataListener {

    protected Context mContext;
    protected RefreshView refreshView;
    protected IRefreshViewInteractor interactor;
    protected int page = 1;
    private IRefreshInConfiguration configuration;


    public RefreshPresenter(Context mContext, RefreshView refreshView, IRefreshInConfiguration configuration) {
        this.mContext = mContext;
        this.refreshView = refreshView;
        this.configuration = configuration;
        interactor = new RefreshViewInteractorImpl();
        page = 1;
    }


    @Override
    public void requestPageData(String url, Map<String, String> map,boolean fromStart) {
        if (fromStart) {
            page = 1;
        }
        //TODO 要做处理
        map.put("json", "{\n" +
                "    \"sid\": \"ipeiban2016\",\n" +
                "    \"pageNumber\": " + page + ",\n" +
                "    \"pageSize\": 10\n" +
                "}");
        interactor.requestPageData(url, map, this);
    }

    @Override
    public void requestData(String url, Map<String, String> map) {
        interactor.requestData(url, map, this);
    }

    /**
     * ————————————————————————— 不分页回调 ———————————————————————————————————————————
     **/
    @Override
    public void onBefore(Request request) {
        if (ListUtils.isEmpty(configuration.getmRefreshList())) {
            EmptyView.showLoading(refreshView.getRefreshEmptyView());
        }
    }

    @Override
    public void onError(Call call, Exception e) {
        if (ListUtils.isEmpty(configuration.getmRefreshList()) && !NetUtils.isConnected(mContext)) {//没有数据,且没有网络 提示布局
            EmptyView.showNetErrorEmpty(refreshView.getRefreshEmptyView());
        } else if (!ListUtils.isEmpty(configuration.getmRefreshList()) && !NetUtils.isConnected(mContext)) {//有数据且没有网络，提示Toast
            ToastUtils.show(mContext, R.string.common_net_error);
        } else if (ListUtils.isEmpty(configuration.getmRefreshList())) {//没有数据
            EmptyView.showNoDataEmpty(refreshView.getRefreshEmptyView());
        }
    }

    @Override
    public void onResponse(String response) {
        BaseJson bean = new Gson().fromJson(response.toString(), BaseJson.class);
        configuration.getmRefreshList().clear();
        List newGameResponse = null;
        try {
            newGameResponse = GsonJsonUtils.fromJsonArray(new Gson().toJson(bean.getData()), configuration.parseClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!ListUtils.isEmpty(newGameResponse)) {
            configuration.getmRefreshList().addAll(newGameResponse);
        }
    }

    @Override
    public void onAfter() {
        refreshView.getRefreshPtrLayoutView().onRefreshComplete();
        if (ListUtils.isEmpty(configuration.getmRefreshList())) {//没有数据
            EmptyView.showNoDataEmpty(refreshView.getRefreshEmptyView());
        }
        configuration.after();
    }


    /**
     * ————————————————————————— 分页回调 ———————————————————————————————————————————
     **/
    @Override
    public void onPageBefore(Request request) {
        if (ListUtils.isEmpty(configuration.getmRefreshList())) {
            EmptyView.showLoading(refreshView.getRefreshEmptyView());
        }
    }

    @Override
    public void onPageError(Call call, Exception e) {
        if (ListUtils.isEmpty(configuration.getmRefreshList()) && !NetUtils.isConnected(mContext)) {//没有数据,且没有网络 提示布局
            EmptyView.showNetErrorEmpty(refreshView.getRefreshEmptyView());
        } else if (!ListUtils.isEmpty(configuration.getmRefreshList()) && !NetUtils.isConnected(mContext)) {//有数据且没有网络，提示Toast
            ToastUtils.show(mContext, R.string.common_net_error);
        } else if (ListUtils.isEmpty(configuration.getmRefreshList()) && NetUtils.isConnected(mContext)) {//没有数据
            EmptyView.showNoDataEmpty(refreshView.getRefreshEmptyView());
        } else {
            if (null != refreshView.getDataView() && refreshView.getDataView() instanceof ListViewFinal) {
                ((ListViewFinal) refreshView.getDataView()).showFailUI();
            } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof GridViewFinal) {
                ((GridViewFinal) refreshView.getDataView()).showFailUI();
            } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof RecyclerViewFinal) {
                ((RecyclerViewFinal) refreshView.getDataView()).showFailUI();
            } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof ScrollViewFinal) {
                ((ScrollViewFinal) refreshView.getDataView()).showFailUI();
            }
        }
    }

    @Override
    public void onPageResponse(String response) {
        BaseJson bean = new Gson().fromJson(response.toString(), BaseJson.class);
        if (page == 1) {
            configuration.getmRefreshList().clear();
        }
        page = page + 1;
        BasePageListBean basePageListBean = new Gson().fromJson(new Gson().toJson(bean.getData()), BasePageListBean.class);
        List newGameResponse = null;
        try {
            newGameResponse = GsonJsonUtils.fromJsonArray(new Gson().toJson(basePageListBean.getList()), configuration.parseClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!ListUtils.isEmpty(newGameResponse)) {
            configuration.getmRefreshList().addAll(newGameResponse);
        }
        if (null != refreshView.getDataView() && refreshView.getDataView() instanceof ListViewFinal) {
            ((ListViewFinal) refreshView.getDataView()).setHasLoadMore(newGameResponse.size() < JsonConstants.PAGE_SIZE ? false : true);
        } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof GridViewFinal) {
            ((GridViewFinal) refreshView.getDataView()).setHasLoadMore(newGameResponse.size() < JsonConstants.PAGE_SIZE ? false : true);
        } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof RecyclerViewFinal) {
            ((RecyclerViewFinal) refreshView.getDataView()).setHasLoadMore(newGameResponse.size() < JsonConstants.PAGE_SIZE ? false : true);
        } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof ScrollViewFinal) {
            ((ScrollViewFinal) refreshView.getDataView()).setHasLoadMore(newGameResponse.size() < JsonConstants.PAGE_SIZE ? false : true);
        }
    }

    @Override
    public void onPageAfter() {
        if ((page - 1) <= 1) {
            refreshView.getRefreshPtrLayoutView().onRefreshComplete();
        } else {
            if (null != refreshView.getDataView() && refreshView.getDataView() instanceof ListViewFinal) {
                ((ListViewFinal) refreshView.getDataView()).onLoadMoreComplete();
            } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof GridViewFinal) {
                ((GridViewFinal) refreshView.getDataView()).onLoadMoreComplete();
            } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof RecyclerViewFinal) {
                ((RecyclerViewFinal) refreshView.getDataView()).onLoadMoreComplete();
            } else if (null != refreshView.getDataView() && refreshView.getDataView() instanceof ScrollViewFinal) {
                ((ScrollViewFinal) refreshView.getDataView()).onLoadMoreComplete();
            }
        }
        if (ListUtils.isEmpty(configuration.getmRefreshList())) {//没有数据
            EmptyView.showNoDataEmpty(refreshView.getRefreshEmptyView());
        }
        configuration.after();
    }

}
