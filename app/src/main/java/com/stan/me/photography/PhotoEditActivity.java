package com.stan.me.photography;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.stan.core.MeApplication;
import com.stan.me.BaseActivity;
import com.stan.me.R;

public class PhotoEditActivity extends BaseActivity<PhotoEditContract.Presenter> implements PhotoEditContract.View, View.OnClickListener {
    private static final String NAME_URI = "uri";
    private EditText mTitleEt;
    private EditText mContentEt;
    private ImageView mPicIv;
    private ImageView mBackIv;
    private ImageView mSubmitIv;
    private float mBitmapScale;//长宽比

    public static void start(Context context, Uri uri) {
        Intent intent = new Intent(context, PhotoEditActivity.class);
        intent.putExtra(NAME_URI, uri);
        context.startActivity(intent);
    }

    public static void startForResult(Fragment fragment, Uri uri, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), PhotoEditActivity.class);
        intent.putExtra(NAME_URI, uri);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_photo_edit);
        Uri uri = getIntent().getParcelableExtra(NAME_URI);
        mTitleEt = findViewById(R.id.et_title);
        mContentEt = findViewById(R.id.et_content);
        mPicIv = findViewById(R.id.iv_bitmap);
        mPicIv.setOnClickListener(this);
        mBackIv = findViewById(R.id.iv_left);
        mBackIv.setOnClickListener(this);
        mSubmitIv = findViewById(R.id.iv_right);
        mSubmitIv.setOnClickListener(this);
        loadBitmap(uri);
    }

    @Override
    public void setPresenter() {
        mPresenter = new PhotoEditPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.iv_bitmap:
            break;
        case R.id.iv_left:
            finish();
            break;
        case R.id.iv_right:
            //检查标题是否为空
            if (TextUtils.isEmpty(mTitleEt.getText().toString())) {
                Toast.makeText(this, R.string.pls_add_title, Toast.LENGTH_SHORT).show();
                return;
            }

            //检查内容是否为空
            if (TextUtils.isEmpty(mContentEt.getText().toString())) {
                Toast.makeText(this, R.string.pls_add_content, Toast.LENGTH_SHORT).show();
                return;
            }

            if (mPresenter.insertIntoDB((Uri) mPicIv.getTag())) {
                Toast.makeText(MeApplication.getInstance(), R.string.add_success, Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
            break;
        }
    }

    @Override
    public void loadBitmap(Uri uri) {
        mPicIv.setImageURI(uri);
        BitmapDrawable drawable = (BitmapDrawable) mPicIv.getDrawable();
        if (drawable != null) {
            Bitmap bitmap = drawable.getBitmap();
            if (bitmap != null) {
                mBitmapScale = (float) bitmap.getHeight() / (float) bitmap.getWidth();
            }
        }
        mPicIv.setTag(uri);
    }

    @Override
    public String getContent() {
        return mContentEt.getText().toString();
    }

    @Override
    public float getScale() {
        return mBitmapScale;
    }

    @Override
    public View getRootView() {
        return (View) mPicIv.getParent();
    }

    @Override
    public String getTitleStr() {
        return mTitleEt.getText().toString();
    }
}
