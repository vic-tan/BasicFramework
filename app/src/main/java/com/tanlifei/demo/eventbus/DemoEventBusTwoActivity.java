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

package com.tanlifei.demo.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tanlifei.common.bean.params.BaseEventbusParams;
import com.tanlifei.common.ui.activity.actionbar.BaseActionBarActivity;
import com.tanlifei.framework.R;
import com.tlf.basic.utils.StartActUtils;
import com.tlf.basic.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class DemoEventBusTwoActivity extends BaseActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_eventbus_activity_two);
        Button indeterminate = (Button) findViewById(R.id.indeterminate);
        indeterminate.setOnClickListener(this);
        EventBus.getDefault().register(this);
        super.initActionBar();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indeterminate:
                StartActUtils.start(mContext, DemoEventBusThreeActivity.class);
                break;

        }
    }

    @Subscribe
    public void onEventMainThread(BaseEventbusParams event) {
        if (event.getTag() == 2) {
            String msg = "Two 收到了消息：" + event.getStrParam();
            ToastUtils.show(mContext, msg);
            EventBus.getDefault().post(
                    new BaseEventbusParams(1,"FirstEvent btn clicked"));
            StartActUtils.finish(mContext);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
