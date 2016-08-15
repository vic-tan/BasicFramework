package com.tanlifei.exemple.dialog.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tanlifei.common.base.adapter.CommonAdapter;
import com.tanlifei.common.base.adapter.ViewHolder;
import com.tanlifei.common.ui.activity.BaseActionBarActivity;
import com.tanlifei.framework.R;

import java.util.ArrayList;
import java.util.List;


public class ExempleDialogPopupHomeActivity extends BaseActionBarActivity {
    private final List<String> mItems = new ArrayList<>();
    private final Class<?>[] mClazzs = {ExempleDialogCustomPopupActivity.class, ExempleDialogBubblePopupActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems.add("Custom Popup");
        mItems.add("BubblePopup");
        setContentView(R.layout.exemple_base_adapter_activity_simple_home);
        initActionBar();
        actionBarView.setActionbarTitle("Popup分类");
        ListView lv = (ListView) findViewById(R.id.main_lv_list);
        lv.setAdapter(new CommonAdapter<String>(this, mItems, R.layout.main_activity_home_list_item) {
            @Override
            public void convert(final ViewHolder holder, String bean) {
                holder.setText(R.id.main_list_item_name, bean);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, mClazzs[holder.getPosition()]);
                        startActivity(intent);
                    }
                });

            }
        });
    }

    @Override
    protected Class<?> childClassName() {
        return ExempleDialogPopupHomeActivity.class;
    }

}
