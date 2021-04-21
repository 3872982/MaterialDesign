package com.learning.materialdesign.net;

import android.util.Log;

import com.google.gson.Gson;
import com.learning.materialdesign.bean.HttpResult;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/6/5.
 */


/**
 * 自定义Gson解析类，可以在这个类中优雅地实现数据的预处理
 * @param <T>
 */
public class GsonResponseBodyConvert<T> implements Converter<ResponseBody,T> {
    private final Gson mGson;
    private final Type mType;

    public GsonResponseBodyConvert(Gson gson, Type type) {
        mGson = gson;
        mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.d("NETWORK","response >> "+response);

        //httpResult只解析Result字段
        HttpResult httpResult = mGson.fromJson(response,HttpResult.class);

        if(httpResult.code != 200){
            throw new ApiException(ApiException.NET_ERROR);
        }
        return mGson.fromJson(response,mType);
    }
}
