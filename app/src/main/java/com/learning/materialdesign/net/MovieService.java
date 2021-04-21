package com.learning.materialdesign.net;

import com.learning.materialdesign.bean.HttpResult;
import com.learning.materialdesign.bean.Subjects;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService{
    @GET("top250")
    Observable<HttpResult<Subjects>> getTopMovie(@Query("page") int page);
}
