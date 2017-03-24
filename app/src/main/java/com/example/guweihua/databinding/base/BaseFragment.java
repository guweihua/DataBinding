package com.example.guweihua.databinding.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guweihua.databinding.utils.ActivityFragmentAnnoationManager;

/**
 * Created by guweihua on 2017/3/23.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private int contentId;
    protected Bundle bundle;
    protected Activity a;
    protected T binding;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (contentId == 0){
            bundle = getArguments();
            contentId = ActivityFragmentAnnoationManager.check(this);
            a = activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null){
            binding = DataBindingUtil.inflate(inflater, contentId, container, false);
            initFragmentImpl();
        }
        return binding.getRoot();
    }

    protected abstract void initFragmentImpl();
}
