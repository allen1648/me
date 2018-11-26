package com.stan.me.photography;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.stan.core.database.PhotoDao;
import com.stan.core.mvp.BaseView;

import java.util.List;

public class PhotoPresenter implements PhotoContract.Presenter {
    private static final int PAGE_SIZE = 10;
    private PhotoContract.View mView;
    private int mPageIndex;
    PhotoDao mPhotoDao = new PhotoDao();

    @Override
    public void choosePhoto() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        ((Fragment)mView).startActivityForResult(intent, PhotoFragment.REQUEST_CODE_CHOOSE_PIC);
    }

    @Override
    public void getPhoto() {
        List<PhotoDao.PhotoRecord> list = mPhotoDao.queryPhoto(mPageIndex, mPageIndex + PAGE_SIZE);
        mView.setData(list);
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
