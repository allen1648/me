package com.stan.core.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class BitmapUtil {
    public static byte[] bitmapTobytes(Bitmap bitmap) {
        if(bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
