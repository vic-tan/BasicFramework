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
import android.widget.TextView;

import com.support.okhttp.OkHttpUtils;
import com.tanlifei.common.bean.BaseJson;
import com.tanlifei.common.ui.activity.actionbar.BaseActionBarActivity;
import com.tanlifei.demo.evenbean.FirstEvent;
import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.UrlConstants;
import com.tanlifei.support.http.DialogCallback;
import com.tlf.basic.utils.StartActUtils;
import com.tlf.basic.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;


public class DemoEventBusOneActivity extends BaseActionBarActivity implements View.OnClickListener {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_eventbus_activity_one);
        EventBus.getDefault().register(this);
        tv = (TextView) findViewById(R.id.tv);
        Button indeterminate = (Button) findViewById(R.id.indeterminate);
        indeterminate.setOnClickListener(this);
        super.initActionBar();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indeterminate:
                StartActUtils.start(mContext, DemoEventBusTwoActivity.class);
                break;

        }
    }



    /**
     * 与发布者在同一个线程
     */
  /*  @Subscribe
    public void onEvent(FirstEvent event) {
        if (event.getTag() == 1) {
            String msg = "one onEvent 收到了消息：" + event.getMsg();
            tv.setText(msg);
            ToastUtils.show(mContext, msg);
        }
    }*/

    /**
     * 执行在主线程。
     * 非常实用，可以在这里将子线程加载到的数据直接设置到界面中。
     */
    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getTag() == 1) {
            String msg = "one onEventMainThread 收到了消息：" + event.getMsg();
            tv.setText(msg);
            ToastUtils.show(mContext, msg);
            OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).paramsForJson(tagList()).build().execute(new DialogCallback(mContext) {
                @Override
                public void onCusResponse(BaseJson response) {
                    ToastUtils.show(mContext, response.getData() + "");
                }
            });
        }
    }

    /**
     * 执行在子线程，如果发布者是子线程则直接执行，如果发布者不是子线程，则创建一个再执行
     * 此处可能会有线程阻塞问题。
     */
  /*  @Subscribe
    public void onEventBackgroundThread(FirstEvent event) {
        if (event.getTag() == 1) {
            String msg = "one onEventBackgroundThread 收到了消息：" + event.getMsg();
            tv.setText(msg);
            ToastUtils.show(mContext, msg);
        }
    }*/

    /**
     * 执行在在一个新的子线程
     * 适用于多个线程任务处理， 内部有线程池管理。
     */

   /* @Subscribe
    public void onEventAsync(FirstEvent event) {
        if (event.getTag() == 1) {
            String msg = "one onEventAsync：收到了消息" + event.getMsg();
            tv.setText(msg);
            ToastUtils.show(mContext, msg);
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    public Map<String, Object> tagList() {
        Map<String, Object> map = new HashMap<>();
        map.put("sid", "ipeiban2016");
        return map;
    }
}
