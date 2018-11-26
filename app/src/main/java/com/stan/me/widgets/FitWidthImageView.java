package com.stan.me.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FitWidthImageView extends android.support.v7.widget.AppCompatImageView {

	public FitWidthImageView(Context context) {
		super(context);
	}

	public FitWidthImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth();
		float resourceScale = getResourceScale();
		if (resourceScale == 0) {
			return;
		}
		int height = (int) (resourceScale * width);
		setMeasuredDimension(width, height);
	}

	private float getResourceScale() {
		Drawable drawable = getDrawable();
		if (drawable != null && drawable instanceof BitmapDrawable) {
			BitmapDrawable bd = (BitmapDrawable) drawable;
			Bitmap bitmap = bd.getBitmap();
			if(bitmap == null){
				return 1;
			}
			return (float) bitmap.getHeight() / (float) bitmap.getWidth();
		}
		return 0;
	}

}
