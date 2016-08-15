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

public class ExempleDialogSimpleHomeActivity extends BaseActionBarActivity {
    private final List<String> mItems = new ArrayList<>();
    private final Class<?>[] mClazzs = {ExempleDialogDialogHomeActivity.class, ExempleDialogPopupHomeActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems.add("Dialog");
        mItems.add("Popup");
        setContentView(R.layout.exemple_base_adapter_activity_simple_home);
        initActionBar();
        actionBarView.setActionbarTitle("Dialog公用分类");
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
        return ExempleDialogSimpleHomeActivity.class;
    }

}
