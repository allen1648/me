package com.stan.core.http;

/**
 * app用请求器
 */
public class RetrofitHelper extends BaseRetrofitHelper {

    private static RetrofitHelper mInstance;
    private DoubanApi mDoubanApi;

    private RetrofitHelper() {
        init();
    }

    public static RetrofitHelper getInstance() {
        return mInstance == null ? (mInstance = new RetrofitHelper()) : mInstance;
    }

    public void init() {
        super.init();
    }

    public DoubanApi getDoubanApis() {
        if(mDoubanApi == null) {
            mDoubanApi = getApiService(HttpUtils.DOUBAN_HOST, DoubanApi.class);
        }
        return mDoubanApi;
    }

}
