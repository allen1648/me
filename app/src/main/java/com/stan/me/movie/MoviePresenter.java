package com.stan.me.movie;

import com.stan.core.http.RetrofitHelper;
import com.stan.core.http.bean.Top250Info;
import com.stan.core.mvp.BaseView;
import com.stan.core.mvp.RxPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter extends RxPresenter implements MovieContract.Presenter {

    private MovieContract.View mView;

    @Override
    public void getMovie() {
        mView.showProgress();
        Observable<Top250Info> movieObservable = RetrofitHelper.getInstance().getDoubanApis().getTop250();
//        addDisposable(movieObservable);
        movieObservable.map(new Function<Top250Info, List<Top250Info.MovieInfo>>() {
            @Override
            public List<Top250Info.MovieInfo> apply(Top250Info top250Info) throws Exception {
                return top250Info.getMoviesInfo();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Top250Info.MovieInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Top250Info.MovieInfo> movieInfos) {
                mView.updateMovies(movieInfos);
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
            }

            @Override
            public void onComplete() {
                mView.hideProgress();
            }
        });
    }

    @Override
    public void attachView(BaseView view) {
        mView = (MovieContract.View) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
