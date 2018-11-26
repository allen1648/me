package com.stan.me.photography;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stan.core.database.PhotoDao;
import com.stan.me.BaseFragment;
import com.stan.me.R;

import java.util.List;

public class PhotoFragment extends BaseFragment<PhotoContract.Presenter> implements PhotoContract.View, View.OnClickListener {
    public static final String TAG = "PhotoFragment";
    public static final int REQUEST_CODE_CHOOSE_PIC = 1;
    public static final int REQUEST_CODE_ADD_PHOTO = 2;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private FloatingActionButton mFabButton;

    public static Fragment getInstance() {
        return new PhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photography, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mPresenter.getPhoto();
    }

    private void initView(View view) {
        mFabButton = view.findViewById(R.id.fab);
        mFabButton.setOnClickListener(this);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mPhotoAdapter = new PhotoAdapter(getActivity());
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    @Override
    public void setPresenter() {
        mPresenter = new PhotoPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.fab:
            mPresenter.choosePhoto();
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOSE_PIC) {
                Uri uri = data.getData();
                PhotoEditActivity.startForResult(this, uri, REQUEST_CODE_ADD_PHOTO);
            } else if (requestCode == REQUEST_CODE_ADD_PHOTO) {
                mPresenter.getPhoto();
            }
        }
    }

    @Override
    public void addData(List<PhotoDao.PhotoRecord> list) {
        mPhotoAdapter.addData(list);
        mPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void setData(List<PhotoDao.PhotoRecord> list) {
        mPhotoAdapter.setData(list);
        mPhotoAdapter.notifyDataSetChanged();
    }
}
