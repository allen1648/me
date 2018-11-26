package com.stan.core.utils;

import android.util.DisplayMetrics;

import com.stan.core.MeApplication;

public class ScreenUtil {
    private static float mScreenDensity = 0;
    private static int mScreenDpi = 0;
    private static int mScreenHeight = 0;
    private static int mScreenWidth = 0;
    private static int mStatusBarHeight = 0;
    private static void initilize() {
        DisplayMetrics dm = MeApplication.getInstance().getResources().getDisplayMetrics();

        mScreenDensity = dm.density;
        // 获取dpi的值
        mScreenDpi = dm.densityDpi;
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }

    public static int getScreenHeight() {
        if (0 == mScreenHeight) {
            initilize();
        }

        return mScreenHeight;
    }

    /**
     * 获取屏幕宽度(单位像素)
     *
     * @return
     */
    public static int getScreenWidth() {
        if (0 == mScreenWidth) {
            initilize();
        }

        return mScreenWidth;
    }
}
