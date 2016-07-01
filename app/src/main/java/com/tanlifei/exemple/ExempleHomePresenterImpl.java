package com.tanlifei.exemple;

import java.util.List;


/**
 * Created by tanlifei on 16/5/12.
 */
public class ExempleHomePresenterImpl implements ExempleHomePresenter {


    private ExempleHomeInteractor interactor;

    public ExempleHomePresenterImpl() {
        this.interactor = new ExempleHomeInteractorImpl();
    }


    @Override
    public List<ExempleHomeListBean> addList() {
        return interactor.addList();
    }
}
