package com.tanlifei.exemple.refreshview.ui;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.fans.loader.FanImageLoader;
import com.tanlifei.common.base.adapter.CommonAdapter;
import com.tanlifei.common.base.adapter.ViewHolder;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInConfiguration;
import com.tanlifei.common.base.refreshview.presenter.IRefreshInPresenter;
import com.tanlifei.common.base.refreshview.presenter.impl.RefreshPresenter;
import com.tanlifei.common.base.refreshview.ui.RefreshView;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.bean.PageBean;
import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.exemple.refreshview.bean.GameInfo;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.UrlConstants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;
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
public class ExempleListViewActivity extends BaseActivity implements RefreshView,IRefreshInConfiguration {
    public static final String TAG = ExempleListViewActivity.class.getSimpleName();


    @ViewById(R.id.ptr_layout)
    public PtrClassicFrameLayout mPtrLayout;
    @ViewById(R.id.fl_empty_view)
    FrameLayout mFlEmptyView;
    @ViewById(R.id.lv_games)
    public ListViewFinal mLvGames;
    private List<GameInfo> mGameList;
    private CommonAdapter<GameInfo> mNewGameListAdapter;
    private IRefreshInPresenter presenter;

    private int mPage = 1;




    @AfterViews
    void init() {
        presenter = new RefreshPresenter(mContext, this,this);
        mGameList = new ArrayList<>();
        mNewGameListAdapter = new CommonAdapter<GameInfo>(mContext, mGameList, R.layout.exemple_refresh_adapter_list_item) {
            @Override
            public void convert(ViewHolder holder, GameInfo bean) {
                FanImageLoader.create(bean.getIconUrl()).setAllRes(R.mipmap.ic_launcher).into(holder.getView(R.id.ic_game_icon));
                holder.setText(R.id.tv_game_name, bean.getName());
                holder.setRating(R.id.rb_game_rank, bean.getTaskScore() / 2.0f);
                holder.setText(R.id.tv_game_socre, new DecimalFormat("#0.0").format(bean.getTaskScore()) + "分");
                holder.setText(R.id.tv_game_player_number, "热度:" + String.valueOf(bean.getPlayerCount()));
                holder.setText(R.id.tv_game_comment_number, "评论数:" + String.valueOf(bean.getCommentCount()));
            }
        };
        mLvGames.setAdapter(mNewGameListAdapter);
        mLvGames.setEmptyView(mFlEmptyView);
        mPtrLayout.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.requestPageData(UrlConstants.NEW_GAME,params(1),true);
            }
        });
        mPtrLayout.setLastUpdateTimeRelateObject(this);
        mLvGames.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.requestPageData(UrlConstants.NEW_GAME,params(1),false);
            }
        });
        mPtrLayout.autoRefresh();
    }


    public Map<String, String> params(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("limit", 12 + "");
        return map;
    }


    @Override
    protected Class<?> childClassName() {
        return ExempleListViewActivity_.class;
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
        return GameInfo.class;
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
