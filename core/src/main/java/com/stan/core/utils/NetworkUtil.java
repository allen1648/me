package com.stan.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.stan.core.MeApplication;

public class NetworkUtil {
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MeApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = cm.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }
}
