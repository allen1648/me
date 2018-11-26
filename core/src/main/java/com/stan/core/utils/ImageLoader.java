package com.stan.core.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.stan.core.database.PhotoDao;

public class ImageLoader {
    private static ImageLoader sInstance;

    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }

    private ImageLoader() {

    }

    public void loadImage(Context context, PhotoDao.PhotoRecord record, ImageView mHolderView) {
        ColorDrawable drawable = new ColorDrawable(0xff000000);
        ColorDrawable errordrawable = new ColorDrawable(0xff00ff00);
        int width = ScreenUtil.getScreenWidth();
        RequestOptions options = new RequestOptions();
//                .placeholder(drawable)
//                .error(errordrawable);
//                .override(width, (int) (width * record.getScale()));
        Glide.with(context).load(record.getPath()).apply(options).into(mHolderView);
    }
}
