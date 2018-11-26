package com.stan.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class PermissionUtil {
    public static final int READ_EXTERNAL_REQUESTCODE = 1;

    public static void requestPermissions(Context context, String permission, int requestCode) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);
        }
    }
}
