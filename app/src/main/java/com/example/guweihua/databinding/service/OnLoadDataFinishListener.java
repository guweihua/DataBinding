package com.example.guweihua.databinding.service;

/**
 * Created by guweihua on 2017/3/24.
 */

public interface OnLoadDataFinishListener<T> {
    void loadSuccess(T t,int page);

    void loadError(String msg,int page);
}
