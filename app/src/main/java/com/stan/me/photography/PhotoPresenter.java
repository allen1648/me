package com.stan.me.photography;

import com.stan.core.mvp.BaseView;

public class PhotoPresenter implements PhotoContract.Presenter {
    private PhotoContract.View mView;

    @Override
    public void getPhoto() {

    }

    @Override
    public void attachView(BaseView view) {
        mView = (PhotoContract.View) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
