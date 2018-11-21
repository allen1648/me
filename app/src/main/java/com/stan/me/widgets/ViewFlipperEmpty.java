package com.stan.me.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

public class ViewFlipperEmpty extends ViewFlipper {
    public static final int INDEX_PROGRESS = 0;
    public static final int INDEX_NETERROR = 1;

    public ViewFlipperEmpty(Context context, AttributeSet attrs) {
        super(context, attrs);

        //刷新进度条--0
        View progress = new ProgressBar(context);
        addView(progress, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        //NetError--1
    }

    public void showProgress() {
        setDisplayedChild(INDEX_PROGRESS);
    }

    public void showNoNetWork() {
        //setDisplayedChild(INDEX_NETERROR);
    }

}