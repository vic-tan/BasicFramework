package com.tanlifei.exemple.refreshview.ui;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.fans.loader.FanImageLoader;
import com.tanlifei.common.base.adapter.abslistview.AbsCommonAdapter;
import com.tanlifei.common.base.adapter.abslistview.AbsViewHolder;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInConfiguration;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInPresenter;
import com.tanlifei.common.base.refreshview.presenter.impl.RefreshPresenter;
import com.tanlifei.common.base.refreshview.ui.RefreshView;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.bean.PageBean;
import com.tanlifei.common.ui.activity.BaseActionBarActivity;
import com.tanlifei.exemple.refreshview.bean.TrainBean;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.UrlConstants;
import com.tanlifei.support.utils.DateFormatUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;

/**
 * 加载资源数据界面
 * Created by tanlifei on 16/1/19.
 */
@EActivity(R.layout.exemple_acitivity_ptr_recycler)
public class ExempleRecyclerViewActivity extends BaseActionBarActivity implements RefreshView, IRefreshInConfiguration {
    public static final String TAG = ExempleRecyclerViewActivity.class.getSimpleName();


    @ViewById(R.id.ptr_layout)
    public PtrClassicFrameLayout mPtrLayout;
    @ViewById(R.id.fl_empty_view)
    FrameLayout mFlEmptyView;
    @ViewById(R.id.lv_games)
    public RecyclerViewFinal mLvGames;
    private List<TrainBean> mGameList;
    private AbsCommonAdapter<TrainBean> mNewGameListAdapter;
    private IRefreshInPresenter presenter;



    @AfterViews
    void init() {
        initActionBar();
        actionBarView.setActionbarTitle("RecyclerView 上拉下拉刷新");
        presenter = new RefreshPresenter(mContext, this, this);
        mGameList = new ArrayList<>();
        mNewGameListAdapter = new AbsCommonAdapter<TrainBean>(mContext, R.layout.train_open_list_item, mGameList) {
            @Override
            protected void convert(AbsViewHolder holder, TrainBean bean, int position) {
                FanImageLoader.create(bean.getCover()).setAllRes(R.mipmap.exemple_default_img).into(holder.getView(R.id.cover));
                holder.setText(R.id.title, bean.getName());
                holder.setText(R.id.desc, "开始时间:" + DateFormatUtils.format(bean.getBegin_time(), DateFormatUtils.FormatType.DAY) + "\r\n"
                        + "结束时间:" + DateFormatUtils.format(bean.getEnd_time(), DateFormatUtils.FormatType.DAY));
            }

        };
       // mLvGames.setAdapter(mNewGameListAdapter);
        mLvGames.setEmptyView(mFlEmptyView);
        mPtrLayout.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.requestPageData(UrlConstants.LIST_URL, params(1), true);
            }
        });
        mPtrLayout.setLastUpdateTimeRelateObject(this);
        mLvGames.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.requestPageData(UrlConstants.LIST_URL, params(1), false);
            }
        });
        mPtrLayout.autoRefresh();
    }


    public Map<String, String> params(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("json", "{\n" +
                "    \"sid\": \"ipeiban2016\",\n" +
                "    \"pageNumber\": " + page + ",\n" +
                "    \"pageSize\": 10\n" +
                "}");
        return map;
    }


    @Override
    public View getDataView() {
        return mLvGames;
    }

    @Override
    public PtrClassicFrameLayout getRefreshPtrLayoutView() {
        return mPtrLayout;
    }

    @Override
    public FrameLayout getRefreshEmptyView() {
        return mFlEmptyView;
    }

    @Override
    public Class<?> parseClassName() {
        return TrainBean.class;
    }

    @Override
    public void customParseJson(BaseJson baseJson, PageBean pageBean) {

    }

    @Override
    public BaseAdapter getAdapter() {
        return mNewGameListAdapter;
    }

    @Override
    public List getList() {
        return mGameList;
    }
}
