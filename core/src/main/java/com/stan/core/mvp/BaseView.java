package com.stan.core.mvp;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void onNetworkError(Throwable throwable);
}
