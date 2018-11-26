package com.stan.me.photography;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.stan.core.database.PhotoDao;
import com.stan.core.mvp.BaseView;
import com.stan.core.utils.DateUtil;
import com.stan.me.R;

public class PhotoEditPresenter implements PhotoEditContract.Presenter {
    private PhotoEditContract.View mView;
    private PhotoDao mPhotoDao = new PhotoDao();

    @Override
    public void attachView(BaseView view) {
        mView = (PhotoEditContract.View) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean insertIntoDB(Uri uri) {
        //TODO 耗时操作要异步
        boolean isSuccess = false;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = ((Activity) mView).getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor.moveToFirst()) {
            int columeIndex = cursor.getColumnIndex(filePathColumn[0]);
            String path = cursor.getString(columeIndex);
            String uriStr = uri.toString();
            String date = DateUtil.getCurrentTime();
            String title = mView.getTitleStr();
            String content = mView.getContent();
            float scale = mView.getScale();
            if (mPhotoDao.canAdd(uri.toString())) {
                isSuccess = mPhotoDao.addPhoto(path, uriStr, 0, date, title, content, scale);
            } else {
                Toast.makeText((Context)mView, R.string.photo_added, Toast.LENGTH_SHORT).show();
            }
        }
        cursor.close();
        return isSuccess;
    }
}
