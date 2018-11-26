package com.stan.me.photography;

import android.net.Uri;

import com.stan.core.mvp.BasePresenter;
import com.stan.core.mvp.BaseView;

public class PhotoEditContract {
    interface View extends BaseView {
        void loadBitmap(Uri uri);
        String getTitleStr();
        String getContent();
        float getScale();
        android.view.View getRootView();
    }

    interface Presenter extends BasePresenter {
        boolean insertIntoDB(Uri uri);
    }
}
