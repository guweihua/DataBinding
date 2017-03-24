package com.example.guweihua.databinding.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guweihua on 2017/3/23.
 */

public class NetCacheInterceptor implements Interceptor {
    Context ctx;

    public NetCacheInterceptor(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e("inter", "网络拦截器");
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        if (request.method().equals("POST"))
            return proceed;
        if (proceed.isSuccessful())
            return proceed.newBuilder().header("Cache-Control","public , max - age = 1 ").removeHeader("Pragma").build();
        return proceed;
    }
}
