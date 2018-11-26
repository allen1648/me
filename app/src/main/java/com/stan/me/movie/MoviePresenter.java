package com.stan.me.movie;

import com.stan.core.http.RetrofitHelper;
import com.stan.core.http.bean.Top250Info;
import com.stan.core.mvp.RxPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter extends RxPresenter<MovieContract.View> implements MovieContract.Presenter {

    @Override
    public void getMovie() {
        mView.showProgress();
        Observable<Top250Info> movieObservable = RetrofitHelper.getInstance().getDoubanApis().getTop250();
        Disposable disposable = movieObservable.map(new Function<Top250Info, List<Top250Info.MovieInfo>>() {

            @Override
            public List<Top250Info.MovieInfo> apply(Top250Info top250Info) throws Exception {
                return top250Info.getMoviesInfo();
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Top250Info.MovieInfo>>() {

            @Override
            public void accept(List<Top250Info.MovieInfo> movieInfos) throws Exception {
                mView.updateMovies(movieInfos);
                mView.hideProgress();
            }

        });
        addDisposable(disposable);
    }

}
