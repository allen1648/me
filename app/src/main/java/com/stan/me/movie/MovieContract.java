package com.stan.me.movie;

import com.stan.core.http.bean.Top250Info;
import com.stan.core.mvp.BasePresenter;
import com.stan.core.mvp.BaseView;

import java.util.List;

public class MovieContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void updateMovies(List<Top250Info.MovieInfo> movieInfos);
    }

    interface Presenter extends BasePresenter {
        void getMovie();
    }
}
