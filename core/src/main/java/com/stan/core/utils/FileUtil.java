package com.stan.core.utils;

import android.content.Context;

import com.stan.core.MeApplication;

public class FileUtil {
    public static final String NAME_PHOTO_DATABASE_FILE = "photo.db";

    public static String getNetCacheDir(Context context) {
        return context.getCacheDir()+"/NetworkCache";
    }

    public static String getDatabasePath() {
        return MeApplication.getInstance().getDatabasePath("photo") + "";
    }
}
