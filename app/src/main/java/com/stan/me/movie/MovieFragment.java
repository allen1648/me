package com.stan.me.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stan.core.http.bean.Top250Info;
import com.stan.me.BaseFragment;
import com.stan.me.R;
import com.stan.me.adapters.MovieAdapter;
import com.stan.me.widgets.ViewFlipperEmpty;

import java.util.List;

public class MovieFragment extends BaseFragment implements MovieContract.View {
    private MoviePresenter mPresenter;
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private View mContentView;
    private ViewFlipperEmpty mEmptyView;

    public static Fragment getInstance() {
        return new MovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = View.inflate(getActivity(), R.layout.fragment_movie, null);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mEmptyView = mContentView.findViewById(R.id.empty);
        initRecyclerView();
        mPresenter = new MoviePresenter();
        mPresenter.attachView(this);
        mPresenter.getMovie();
    }

    private void initRecyclerView() {
        mRecyclerView = getView().findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void setPresenter(MovieContract.Presenter presenter) {
        mPresenter = (MoviePresenter) presenter;
    }

    @Override
    public void onNetworkError(Throwable throwable) {

    }

    @Override
    public void showProgress() {
        mEmptyView.setVisibility(View.VISIBLE);
        mEmptyView.showProgress();
    }

    @Override
    public void hideProgress() {
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void updateMovies(List<Top250Info.MovieInfo> movieInfos) {
        if(mMovieAdapter == null) {
            mMovieAdapter = new MovieAdapter(getActivity(), movieInfos);
            mRecyclerView.setAdapter(mMovieAdapter);
        } else {
            mMovieAdapter.setData(movieInfos);
        }
    }
}
