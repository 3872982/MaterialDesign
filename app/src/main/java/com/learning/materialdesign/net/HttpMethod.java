package com.learning.materialdesign.net;

import android.util.Log;

import com.learning.materialdesign.bean.HttpResult;
import com.learning.materialdesign.bean.Subjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpMethod {

    //    private static final String BASEURL= "https://api.douban.com/v2/movie/";
    //豆瓣API挂了，换的别人私人提供的
    //API github https://github.com/wenyanjun/douban
    private static final String BASEURL= "http://39.105.38.10:8081/movie/";
    private static final long CONNECTTIMEOUT = 5;
    private static HttpMethod mHttpMethod = null;

    private final Retrofit mRetrofit;
    private final MovieService mMovieService;

    public HttpMethod() {

        //手动创建一个OKHttpClient并设置超时
        OkHttpClient.Builder  httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(CONNECTTIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                //使用自定义的Converter转换,便于对Http请求进行预处理
                .addConverterFactory(ResponseConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mMovieService = mRetrofit.create(MovieService.class);
    }


    /**
     * 单例模式，由于方法不会再子线程中调用，所以不要双重加锁
     * @return
     */
    public static HttpMethod getInstance(){
        if(mHttpMethod == null){
            mHttpMethod = new HttpMethod();
        }

        return mHttpMethod;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber  观察者对象
     * @param page  页数
     */
    public void getTopMovie(Subscriber<Subjects> subscriber, int page){
        //将被观察者转换成我们期望的模式
        Observable<Subjects> observable = mMovieService.getTopMovie(page)
                .map(new HttpResultFunc<Subjects>());

        //添加线程管理 并 执行订阅
        doSubscribe(observable,subscriber);
    }

    //添加线程管理并订阅
    public <T> void doSubscribe(Observable<T> observer, Subscriber<T> subscriber){
        observer.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 用来统一处理服务器传来的结果，处理ResultCode，ResultMessage等信息，返回我们想要的处理过后的信息
     *
     * @param <T> Subscriber真正需要的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if(httpResult.code != 200){
                throw new ApiException(ApiException.NET_ERROR);
            }
            return httpResult.data;
        }
    }
}
