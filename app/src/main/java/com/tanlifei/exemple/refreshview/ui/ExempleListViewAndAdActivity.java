package com.tanlifei.exemple.refreshview.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.fans.loader.FanImageLoader;
import com.google.gson.Gson;
import com.tanlifei.common.base.adapter.abslistview.AbsCommonAdapter;
import com.tanlifei.common.base.adapter.abslistview.AbsViewHolder;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInConfiguration;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInPresenter;
import com.tanlifei.common.base.refreshview.presenter.impl.RefreshPresenter;
import com.tanlifei.common.base.refreshview.ui.RefreshView;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.bean.PageBean;
import com.tanlifei.common.ui.activity.BaseActionBarActivity;
import com.tanlifei.exemple.refreshview.adapter.BannerAdapter;
import com.tanlifei.exemple.refreshview.bean.BannerBaen;
import com.tanlifei.exemple.refreshview.bean.TrainBean;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.UrlConstants;
import com.tanlifei.support.utils.DateFormatUtils;
import com.tanlifei.support.utils.GsonJsonUtils;
import com.tanlifei.support.widget.viewpager.ChildViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.loadingviewfinal.ListViewFinal;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;

/**
 * 加载资源数据界面
 * Created by tanlifei on 16/1/19.
 */
@EActivity(R.layout.exemple_acitivity_ptr_listview)
public class ExempleListViewAndAdActivity extends BaseActionBarActivity implements RefreshView, IRefreshInConfiguration {
    public static final String TAG = ExempleListViewAndAdActivity.class.getSimpleName();


    @ViewById(R.id.ptr_layout)
    public PtrClassicFrameLayout mPtrLayout;
    @ViewById(R.id.fl_empty_view)
    FrameLayout mFlEmptyView;
    @ViewById(R.id.lv_games)
    public ListViewFinal mLvGames;
    private List<TrainBean> mGameList;
    private AbsCommonAdapter<TrainBean> mNewGameListAdapter;
    private IRefreshInPresenter presenter;
    private List<BannerBaen> mBannerBaenList;

    void initBanner() {
        View v = LayoutInflater.from(this).inflate(R.layout.common_layout_viewpager, null);
        ChildViewPager vp = (ChildViewPager) v.findViewById(R.id.vp_banner);
        vp.setInterval(3000);
        vp.startAutoScroll();
        vp.setAdapter(new BannerAdapter(this,mBannerBaenList));
        mLvGames.addHeaderView(v);
        mPtrLayout.disableWhenHorizontalMove(true);
    }

    private String getBannerDate() {
        return "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": \"8a987d5155b47f520155be577d260179\",\n" +
                "            \"name\": \"2016《培训》直达号--唯品大学（广州站）\",\n" +
                "            \"image\": \"http://www.ipeiban.com.cn/mstatic/M00/00/0A/Chj90Vd84M2EPAtdAAAAAL8WRus720.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"8a987d51551412a10155490db7a0102f\",\n" +
                "            \"name\": \"2016中国企业培训服务会展-成都站\",\n" +
                "            \"image\": \"http://www.ipeiban.com.cn/mstatic/M00/00/12/Chj9rFdg1KuEAzxHAAAAAF5-3BU070.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"8a987d51551412a10155299141f6056d\",\n" +
                "            \"name\": \"2016《培训》直达号--苏宁大学（南京站）\",\n" +
                "            \"image\": \"http://www.ipeiban.com.cn/mstatic/M00/00/04/Chj90VdWatGEf7SGAAAAAH3FNXs021.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"code\": \"0000\",\n" +
                "    \"msg\": \"操作成功\"\n" +
                "}";

    }

    @AfterViews
    void init() {
        initActionBar();
        actionBarView.setActionbarTitle("listView 标准上拉下拉带广告轮播");
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
        mLvGames.setAdapter(mNewGameListAdapter);
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

        mBannerBaenList = new ArrayList<>();
        BaseJson bean = new Gson().fromJson(getBannerDate(), BaseJson.class);
        mBannerBaenList = GsonJsonUtils.fromJsonArray(new Gson().toJson(bean.getData()), BannerBaen.class);
        initBanner();
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
