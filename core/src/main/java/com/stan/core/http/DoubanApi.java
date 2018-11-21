package com.stan.core.http;


import com.stan.core.http.bean.Top250Info;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DoubanApi {
    @GET("movie/top250")
    Observable<Top250Info> getTop250();
}
