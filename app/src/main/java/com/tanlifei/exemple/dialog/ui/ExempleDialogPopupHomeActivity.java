package com.tanlifei.exemple.dialog.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tanlifei.common.ui.activity.BaseActivity;


public class ExempleDialogPopupHomeActivity extends BaseActivity {
    private Context mContext = this;
    private final String[] mItems = {"Custom Popup", "BubblePopup"};
    private final Class<?>[] mClazzs = {ExempleDialogCustomPopupActivity.class, ExempleDialogBubblePopupActivity.class};
    private DisplayMetrics mDisplayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplayMetrics = getResources().getDisplayMetrics();

        ListView lv = new ListView(mContext);
        lv.setCacheColorHint(Color.TRANSPARENT);
        lv.setBackgroundColor(Color.WHITE);
        lv.setFadingEdgeLength(0);
        lv.setAdapter(new SimpleHomeAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, mClazzs[position]);
                startActivity(intent);
            }
        });

        setContentView(lv);
    }

    @Override
    protected Class<?> childClassName() {
        return ExempleDialogPopupHomeActivity.class;
    }

    class SimpleHomeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int padding = (int) (mDisplayMetrics.density * 10);

            TextView tv = new TextView(mContext);
            tv.setText(mItems[position]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 18);
            tv.setTextColor(Color.parseColor("#468ED0"));
            // tv.setGravity(Gravity.CENTER);
            tv.setPadding(padding, padding, padding, padding);
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            return tv;
        }
    }
}
