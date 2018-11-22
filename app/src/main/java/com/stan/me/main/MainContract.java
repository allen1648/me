package com.stan.me.main;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.stan.core.mvp.BasePresenter;
import com.stan.core.mvp.BaseView;

public class MainContract {

    interface View extends BaseView {
        void setTitle(String title);
        void replaceFragment(Fragment fragment, String tag);
        void setMenuTransformationOffset(float slideOffset);
        void setMenuIconState(MaterialMenuDrawable.IconState iconState);
        void switchDrawer(boolean open);
    }

    interface Presenter extends BasePresenter {
        void onMovieClick();
        void onPhotoClick();
        void onDrawerSlide(float slideOffset);
        void onDrawerStateChanged(int newState, boolean isDrawerOpened);
        int getStatusBarColor(Activity activity);
    }
}
