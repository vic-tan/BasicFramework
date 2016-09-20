package com.tanlifei.demo.presenter;

import com.tanlifei.demo.interactor.DemoHomeInteractor;
import com.tanlifei.demo.interactor.DemoHomeInteractorImpl;
import com.tanlifei.exemple.main.bean.ExempleHomeListBean;

import java.util.List;


/**
 * Created by tanlifei on 16/5/12.
 */
public class DemoHomePresenterImpl implements DemoHomePresenter {


    private DemoHomeInteractor interactor;

    public DemoHomePresenterImpl() {
        this.interactor = new DemoHomeInteractorImpl();
    }


    @Override
    public List<ExempleHomeListBean> addList() {
        return interactor.addList();
    }
}
