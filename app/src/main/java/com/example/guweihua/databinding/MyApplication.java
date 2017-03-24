package com.example.guweihua.databinding;

import android.app.Application;

import com.example.guweihua.databinding.utils.NetCacheInterceptor;
import com.example.guweihua.databinding.utils.NoNetCacheInterceptor;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guweihua on 2017/3/23.
 */

public class MyApplication extends Application {

    public static MyApplication app;
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initNet();
    }

    private void initNet() {
        File cacheFile = new File(getCacheDir(),"okhttp-cache");
        Cache cache = new Cache(cacheFile,1024 * 1024 * 3);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).addInterceptor(new NoNetCacheInterceptor(this)).addNetworkInterceptor(new NetCacheInterceptor(this)).build();
        retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl("http://api.m.qu.cn/").addConverterFactory(GsonConverterFactory.create()).build();
    }
}
