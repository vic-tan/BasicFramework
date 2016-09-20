package com.tanlifei.demo.interactor;

import com.tanlifei.demo.eventbus.DemoEventBusMainActivity;
import com.tanlifei.exemple.main.bean.ExempleHomeListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanlifei on 16/5/12.
 */
public class DemoHomeInteractorImpl implements DemoHomeInteractor {


    @Override
    public List<ExempleHomeListBean> addList() {
        List<ExempleHomeListBean> list = new ArrayList<>();

        list.add(new ExempleHomeListBean("EventBus", DemoEventBusMainActivity.class,
                "这是EventBus测试示范"));
        return list;
    }


}
