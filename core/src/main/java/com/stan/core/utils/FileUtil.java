package com.stan.core.utils;

import android.content.Context;

import com.stan.core.MeApplication;

public class FileUtil {
    public static String getNetCacheDir(Context context) {
        return context.getCacheDir()+"/NetworkCache";
    }

    public static String getDatabasePath() {
        return MeApplication.getInstance().getDatabasePath("photo") + "";
    }
}
