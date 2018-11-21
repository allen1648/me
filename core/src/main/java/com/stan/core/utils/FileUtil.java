package com.stan.core.utils;

import android.content.Context;

public class FileUtil {
    public static String getNetCacheDir(Context context) {
        return context.getCacheDir()+"/NetworkCache";
    }
}
