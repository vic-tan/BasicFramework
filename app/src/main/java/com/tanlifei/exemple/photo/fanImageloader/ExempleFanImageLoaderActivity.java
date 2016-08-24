package com.tanlifei.exemple.photo.fanImageloader;

import android.view.View;
import android.widget.RelativeLayout;

import com.support.imageloader.FanImageLoader;
import com.support.refresh.more.ListViewFinal;
import com.support.refresh.more.OnLoadMoreListener;
import com.tanlifei.common.base.adapter.abslistview.AbsCommonAdapter;
import com.tanlifei.common.base.adapter.abslistview.AbsViewHolder;
import com.tanlifei.common.ui.activity.refreshview.BaseAbsRefreshActivity;
import com.tanlifei.exemple.refreshview.bean.TrainBean;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.UrlConstants;
import com.tanlifei.support.utils.DateFormatUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加载资源数据界面
 * Created by tanlifei on 16/1/19.
 */
@EActivity(R.layout.exemple_acitivity_ptr_listview)
public class ExempleFanImageLoaderActivity extends BaseAbsRefreshActivity {
    @ViewById(R.id.ptr_root_layout)
    RelativeLayout ptrRootLayout;
    @ViewById(R.id.lv_games)
    ListViewFinal mLvGames;

    @AfterViews
    void init() {
        super.supperInit();
        actionBarView.setActionbarTitle("图片缓存及特效");
        mLvGames.setAdapter(mRefreshAdapter);
        mLvGames.setEmptyView(mFlEmptyView);
        mLvGames.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                requestLoadMore();
            }
        });
    }

    @Override
    public View getDataView() {
        return mLvGames;
    }

    @Override
    public Class<?> parseClassName() {
        return TrainBean.class;
    }

    @Override
    public View setPtrRootLayout() {
        return ptrRootLayout;
    }

    @Override
    public String requestUrl() {
        return UrlConstants.LIST_URL;
    }

    @Override
    public Map<String, String> requestParams() {
        Map<String, String> map = new HashMap<>();
        map.put("json", "{\n" +
                "    \"sid\": \"ipeiban2016\",\n" +
                "    \"pageNumber\": " + 1 + ",\n" +
                "    \"pageSize\": 10\n" +
                "}");
        return map;
    }


    @Override
    public AbsCommonAdapter setRefreshAdapter() {
        return new AbsCommonAdapter<TrainBean>(mContext, R.layout.exemple_fanimageloader_list_item, (List<TrainBean>) mRefreshList) {
            @Override
            protected void convert(AbsViewHolder holder, TrainBean bean, int position) {
                //FanImageLoader.create(bean.getCover()).setAllRes(R.mipmap.exemple_default_img).into(holder.getView(R.id.cover));
                holder.setText(R.id.title, bean.getName());
                holder.setText(R.id.desc, "开始时间:" + DateFormatUtils.format(bean.getBegin_time(), DateFormatUtils.FormatType.DAY) + "\r\n"
                        + "结束时间:" + DateFormatUtils.format(bean.getEnd_time(), DateFormatUtils.FormatType.DAY));

                if (0 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_DEFAULT)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .into(holder.getView(R.id.cover));
                } else if (1 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_FADE_IN)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.cover));

                } else if (2 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setRoundRadius(36)
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND)
                            .into(holder.getView(R.id.cover));

                } else if (3 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_FADE_IN)
                            .setRoundRadius(36)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.cover));

                } else if (4 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_VIGNETTE)
                            .setRoundRadius(36)
                            .into(holder.getView(R.id.cover));

                } else if (5 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_VIGNETTE_FADE_IN)
                            .setRoundRadius(36)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.cover));

                } else if (6 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE)
                            .into(holder.getView(R.id.cover));

                } else if (7 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE_FADE_IN)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.cover));

                } else if (8 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE_RING)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setStrokeWidth(5.f)
                            .setRingColor(0xff00ff00)
                            .setRingPadding(5.f)
                            .into(holder.getView(R.id.cover));

                } else if (9 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_BLUR)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.cover));

                } else if (10 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_BLUR_FADE_IN)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setBlurDepth(10)
                            .setFadeInTime(1000)
                            .into(holder.getView(R.id.cover));

                } else if (11 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_BLUR)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setRoundRadius(6)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.cover));

                } else if (12 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_ROUND_BLUR_VIGNETTE)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setRoundRadius(36)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.cover));

                } else if (13 == position) {
                    FanImageLoader.create(bean.getCover())
                            .setShowSize(holder.getView(R.id.cover).getWidth(), holder.getView(R.id.cover).getWidth())
                            .setDisplayType(FanImageLoader.DISPLAY_CIRCLE_BLUR)
                            .setDefaultRes(R.mipmap.ic_launcher)
                            .setFailRes(R.mipmap.ic_launcher)
                            .setEmptyRes(R.mipmap.ic_launcher)
                            .setBlurDepth(10)
                            .into(holder.getView(R.id.cover));
                } else {
                    FanImageLoader.create(bean.getCover())
                            .setAllRes(R.mipmap.ic_launcher)
                            .into(holder.getView(R.id.cover));
                }
            }

        };
    }


}
