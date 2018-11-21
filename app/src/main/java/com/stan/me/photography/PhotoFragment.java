package com.stan.me.photography;

import android.support.v4.app.Fragment;

import com.stan.me.BaseFragment;

public class PhotoFragment extends BaseFragment {
    public static final String TAG = "PhotoFragment";
    public static Fragment getInstance() {
        return new PhotoFragment();
    }
}
