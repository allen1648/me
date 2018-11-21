package com.stan.me.main;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.stan.core.mvp.BaseView;
import com.stan.me.MeApplication;
import com.stan.me.R;
import com.stan.me.movie.MovieFragment;
import com.stan.me.photography.PhotoFragment;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    @Override
    public void attachView(BaseView view) {
        mView = (MainContract.View) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onMovieClick() {
        mView.replaceFragment(MovieFragment.getInstance());
        mView.setTitle(MeApplication.getInstance().getString(R.string.movie));
        mView.switchDrawer(false);
    }

    @Override
    public void onPhotoClick() {
        mView.replaceFragment(PhotoFragment.getInstance());
        mView.setTitle(MeApplication.getInstance().getString(R.string.photopraphy));
        mView.switchDrawer(false);
    }

    @Override
    public void onDrawerSlide(float slideOffset) {
        mView.setMenuTransformationOffset(slideOffset);
    }

    @Override
    public void onDrawerStateChanged(int newState, boolean isDrawerOpened) {
        if (newState == DrawerLayout.STATE_IDLE) {
            if (isDrawerOpened) {
                mView.setMenuIconState(MaterialMenuDrawable.IconState.ARROW);
            } else {
                mView.setMenuIconState(MaterialMenuDrawable.IconState.BURGER);
            }
        }
    }

    @Override
    public int getStatusBarColor(Activity activity) {
        View statusBar = activity.findViewById(android.R.id.statusBarBackground);
        ColorDrawable cd = (ColorDrawable) statusBar.getBackground();
        return cd.getColor();
    }
}
