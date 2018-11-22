package com.stan.me.photography;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stan.me.BaseFragment;
import com.stan.me.R;

public class PhotoFragment extends BaseFragment<PhotoContract.Presenter> implements PhotoContract.View {
    public static final String TAG = "PhotoFragment";
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
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
        mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mPhotoAdapter = new PhotoAdapter();
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    @Override
    public void setPresenter() {
        mPresenter = new PhotoPresenter();
    }
}
