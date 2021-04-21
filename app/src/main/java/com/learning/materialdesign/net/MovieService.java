package com.learning.materialdesign.net;

import com.learning.materialdesign.bean.HttpResult;
import com.learning.materialdesign.bean.Movie;
import com.learning.materialdesign.bean.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService{
    @GET("top250")
    Observable<HttpResult> getTopMovie(@Query("page") int page);
}
