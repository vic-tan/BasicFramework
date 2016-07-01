package com.tanlifei.framework.main.ui.activity;

import android.view.View;
import android.widget.ListView;

import com.tanlifei.common.base.adaper.CommonAdapter;
import com.tanlifei.common.base.adaper.ViewHolder;
import com.tanlifei.common.bean.ActBean;
import com.tanlifei.common.ui.activity.BaseActivity;
import com.tanlifei.exemple.ExempleHomeActivity_;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.StartActUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页界面
 * Created by tanlifei on 16/1/19.
 */
@EActivity(R.layout.main_activity_home)
public class HomeActivity extends BaseActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();

    List<String> list = new ArrayList<>();
    @ViewById(R.id.main_lv_list)
    ListView mList;

    private void addList() {
        list.add("集成示范");
        list.add("测试示范");
    }


    @AfterViews
    void init() {
        super.initActionBar();
        addList();
        mList.setAdapter(new CommonAdapter<String>(this, list, R.layout.main_activity_home_list_item) {
            @Override
            public void convert(final ViewHolder holder, String bean) {
                holder.setText(R.id.main_list_item_name, bean);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (holder.getPosition()) {
                            case 0:
                                StartActUtils.start(new ActBean(mContext, ExempleHomeActivity_.class));
                                break;
                        }
                    }
                });

            }
        });
    }

    @Override
    protected Class<?> childClassName() {
        return HomeActivity_.class;
    }

    /**
     * 退出App
     */
    @Override
    public void onBackPressed() {
        exitApp();
    }


}
