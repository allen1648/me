package com.stan.me.photography;

import com.stan.core.mvp.BasePresenter;
import com.stan.core.mvp.BaseView;

public class photoContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
