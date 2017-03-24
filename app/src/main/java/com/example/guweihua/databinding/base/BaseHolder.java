package com.example.guweihua.databinding.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.guweihua.databinding.utils.ActivityFragmentAnnoationManager;

/**
 * Created by guweihua on 2017/3/24.
 */

public class BaseHolder<T> extends RecyclerView.ViewHolder {
    ViewDataBinding binding;
    protected  int dataId;

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public BaseHolder(ViewDataBinding binding){
        super(binding.getRoot());
        this.binding = binding;
        dataId = ActivityFragmentAnnoationManager.check(this);
    }

    public void bind(T t){
        if (binding == null){
            throw new UnsupportedOperationException("不支持binding");
        }
        binding.setVariable(dataId,t);
        binding.executePendingBindings();
    }
}
