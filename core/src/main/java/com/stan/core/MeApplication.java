package com.stan.core;

import android.app.Application;

public class MeApplication extends Application {
    private static Application mInstance;

    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
