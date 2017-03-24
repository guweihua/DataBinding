package com.example.guweihua.databinding.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.guweihua.databinding.bean.CommunityBean;
import com.example.guweihua.databinding.databinding.FragmentBaseQuBinding;

/**
 * Created by guweihua on 2017/3/23.
 */

public class BaseQuFragment extends BaseFragment<FragmentBaseQuBinding> {

    private String type;
    private BaseRecyclerAdapter<CommunityBean.DataBean.ListBean> adapter;
    boolean enableLoadMore = true;
    boolean isLoadMore;
    boolean isFirst = true;
    int page = 1;

    @Override
    protected void initFragmentImpl() {
        type = bundle.getString("type");
        adapter = initAdapter();
        initView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getUserVisibleHint() == true){
            performLoadFirst();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && binding != null){
            performLoadFirst();
        }
    }

    private void performLoadFirst() {
        if (isFirst){
            isFirst = false;
            binding.swip.setRefreshing(true);
            loadFirst();
            Log.e("tagasd", getClass().getSimpleName() + "jizaishuju");
        }
    }

    private void loadFirst() {
        page = 1;
        if (binding.swip.isRefreshing()){
            binding.swip.setRefreshing(true);
        }

    }

    private void initView() {

    }

    private BaseRecyclerAdapter initAdapter() {
        return null;
    }
}
