/*
 * Copyright (c) 2015 Kaopiz Software Co., Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.tanlifei.exemple.refreshview.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tanlifei.common.ui.activity.BaseActionBarActivity;
import com.tanlifei.framework.R;
import com.tanlifei.support.kprogresshud.KProgressHUD;
import com.tanlifei.support.utils.StartActUtils;


public class ExempleRefreshViewMainActivity extends BaseActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exemple_refresh_view_activity_main);
        initActionBar();
        actionBarView.setActionbarTitle("列表刷新");

        Button listView = (Button) findViewById(R.id.listView);
        listView.setOnClickListener(this);

        Button labelIndeterminate = (Button) findViewById(R.id.label_indeterminate);
        labelIndeterminate.setOnClickListener(this);

        Button detailIndeterminate = (Button) findViewById(R.id.detail_indeterminate);
        detailIndeterminate.setOnClickListener(this);

        Button determinate = (Button) findViewById(R.id.determinate);
        determinate.setOnClickListener(this);

        Button annularDeterminate = (Button) findViewById(R.id.annular_determinate);
        annularDeterminate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listView:
                StartActUtils.start(mContext,ExempleListViewActivity_.class);
                break;
            case R.id.label_indeterminate:
                StartActUtils.start(mContext,ExempleListViewAndAdActivity_.class);
                break;
            case R.id.detail_indeterminate:
                StartActUtils.start(mContext,ExempleRecyclerViewActivity_.class);
                break;
            case R.id.determinate:
                StartActUtils.start(mContext,ExempleGridViewActivity_.class);
                break;
            case R.id.annular_determinate:
                StartActUtils.start(mContext,ExempleScrollViewActivity_.class);
                break;
        }

    }

}
