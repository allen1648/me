package com.stan.me.photography;

import com.stan.core.database.PhotoDao;
import com.stan.core.mvp.BasePresenter;
import com.stan.core.mvp.BaseView;

import java.util.List;

public class PhotoContract {
    interface View extends BaseView {
        void addData(List<PhotoDao.PhotoRecord> list);
        void setData(List<PhotoDao.PhotoRecord> list);
    }

    interface Presenter extends BasePresenter {
        void choosePhoto();
        void getPhoto();
    }
}
