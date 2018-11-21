package com.stan.core.mvp;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
