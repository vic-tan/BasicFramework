package com.tanlifei.exemple;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

import com.tanlifei.common.base.adaper.CommonAdapter;
import com.tanlifei.common.base.adaper.ViewHolder;
import com.tanlifei.common.bean.ActBean;
import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.StartActUtils;
import com.tanlifei.support.view.textview.expandable.ExpandableTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 首页界面
 * Created by tanlifei on 16/1/19.
 */
@EActivity(R.layout.exemple_activity_home)
public class ExempleHomeActivity extends BaseActivity {

    public static final String TAG = ExempleHomeActivity.class.getSimpleName();
    private SparseBooleanArray mConvertTextCollapsedStatus = new SparseBooleanArray();
    private ExempleHomePresenter presenter;
    @ViewById(R.id.main_lv_list)
    ListView mList;


    @AfterViews
    void init() {
        super.initActionBar();
        presenter = new ExempleHomePresenterImpl();
        mList.setAdapter(new CommonAdapter<ExempleHomeListBean>(this, presenter.addList(), R.layout.exemple_activity_home_list_item) {
            @Override
            public void convert(ViewHolder holder, final ExempleHomeListBean bean) {
                holder.setText(R.id.mexemple_list_item_name, bean.getTitle());
                ((ExpandableTextView) holder.getView(R.id.expand_text_view)).setmClickType(ExpandableTextView.ClickFooter);
                ((ExpandableTextView) holder.getView(R.id.expand_text_view)).setConvertText(mConvertTextCollapsedStatus, holder.getPosition(), bean.getDesc());
                holder.getView(R.id.expand_text_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartActUtils.start(new ActBean(mContext, bean.getClazz()));
                    }
                });
            }
        });
    }

    @Override
    protected Class<?> childClassName() {
        return ExempleHomeActivity_.class;
    }

    /**
     * 退出App
     */
    @Override
    public void onBackPressed() {
        exitApp();
    }


}
