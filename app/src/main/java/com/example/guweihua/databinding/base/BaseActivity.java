package com.example.guweihua.databinding.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.guweihua.databinding.utils.ActivityFragmentAnnoationManager;


/**
 * Created by guweihua on 2017/3/23.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity{
    protected T binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initView();
        initActivityImpl();
    }

    protected abstract void initActivityImpl();

    private  void initView(){
        int contentId = ActivityFragmentAnnoationManager.check(this);
        binding = DataBindingUtil.setContentView(this, contentId);
    };
}
