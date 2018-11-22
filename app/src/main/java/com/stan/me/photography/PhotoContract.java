package com.stan.me.photography;

import com.stan.core.mvp.BasePresenter;
import com.stan.core.mvp.BaseView;

public class PhotoContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter {
        void getPhoto();
    }
}
