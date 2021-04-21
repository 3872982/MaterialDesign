package com.learning.materialdesign.net;


import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Richen.Xu on 2017/6/5.
 */

/**
 * 在Converter.Factory中注册我们自定义的解析器
 */
public class ResponseConvertFactory extends Converter.Factory {

    private final Gson mGson;

    public static ResponseConvertFactory create(){
        return new ResponseConvertFactory(new Gson());
    }

    public static ResponseConvertFactory create(Gson gson){
        return new ResponseConvertFactory(gson);
    }

    private ResponseConvertFactory(Gson gson){
        if(gson == null){
            throw new ApiException("Gson == null");
        }

        mGson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GsonResponseBodyConvert<>(mGson,type);
    }
}
