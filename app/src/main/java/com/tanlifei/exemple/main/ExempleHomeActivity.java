package com.tanlifei.exemple.main;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

import com.tanlifei.common.base.adapter.CommonAdapter;
import com.tanlifei.common.base.adapter.ViewHolder;
import com.tanlifei.common.ui.activity.BaseActionBarActivity;
import com.tanlifei.exemple.main.bean.ExempleHomeListBean;
import com.tanlifei.exemple.main.presenter.ExempleHomePresenter;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.StartActUtils;
import com.tanlifei.support.widget.textview.expandable.ExpandableTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 首页界面
 * Created by tanlifei on 16/1/19.
 */
@EActivity(R.layout.exemple_activity_home)
public class ExempleHomeActivity extends BaseActionBarActivity {

    public static final String TAG = ExempleHomeActivity.class.getSimpleName();
    private SparseBooleanArray mConvertTextCollapsedStatus = new SparseBooleanArray();
    private ExempleHomePresenter presenter;
    @ViewById(R.id.main_lv_list)
    ListView mList;


    @AfterViews
    void init() {
        super.initActionBar();
        actionBarView.setActionbarTitle("示例列表");
        presenter = new com.tanlifei.exemple.main.presenter.ExempleHomePresenterImpl();
        mList.setAdapter(new CommonAdapter<ExempleHomeListBean>(this, presenter.addList(), R.layout.exemple_activity_home_list_item) {
            @Override
            public void convert(ViewHolder holder, final ExempleHomeListBean bean) {
                holder.setText(R.id.mexemple_list_item_name, bean.getTitle());
                ((ExpandableTextView) holder.getView(R.id.expand_text_view)).setmClickType(ExpandableTextView.ClickFooter);
                ((ExpandableTextView) holder.getView(R.id.expand_text_view)).setConvertText(mConvertTextCollapsedStatus, holder.getPosition(), bean.getDesc());
                holder.getView(R.id.expand_text_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartActUtils.start(mContext, bean.getClazz());
                    }
                });
                holder.setOnClickListener(R.id.mexemple_list_item_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartActUtils.start(mContext, bean.getClazz());
                    }
                });
            }
        });
    }


    /**
     * 退出App
     */
    @Override
    public void onBackPressed() {
        exitApp();
    }


}
