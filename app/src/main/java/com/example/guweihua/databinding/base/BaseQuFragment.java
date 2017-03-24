package com.example.guweihua.databinding.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.guweihua.databinding.bean.CommunityBean;
import com.example.guweihua.databinding.databinding.FragmentBaseQuBinding;
import com.example.guweihua.databinding.service.OnLoadDataFinishListener;
import com.example.guweihua.databinding.utils.HttpUtils;
import com.example.guweihua.databinding.view.SuperRecyclerView;

import java.util.List;

/**
 * Created by guweihua on 2017/3/23.
 */

public class BaseQuFragment extends BaseFragment<FragmentBaseQuBinding> implements OnLoadDataFinishListener<List<CommunityBean.DataBean.ListBean>>, SwipeRefreshLayout.OnRefreshListener, SuperRecyclerView.OnItemClickListener {

    private String type;
    private BaseRecyclerAdapter<CommunityBean.DataBean.ListBean> adapter;
    boolean enableLoadMore = true;
    boolean isLoadMore;
    boolean isFirst = true;
    int page = 1;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void initFragmentImpl() {
        type = bundle.getString("type");
        adapter = initAdapter();
        initView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getUserVisibleHint() == true) {
            performLoadFirst();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && binding != null) {
            performLoadFirst();
        }
    }

    private void performLoadFirst() {
        if (isFirst) {
            isFirst = false;
            binding.swip.setRefreshing(true);
            loadFirst();
            Log.e("tagasd", getClass().getSimpleName() + "jizaishuju");
        }
    }

    private void loadFirst() {
        page = 1;
        if (!binding.swip.isRefreshing()) {
            binding.swip.setRefreshing(true);
        }
        HttpUtils.loadCommunityDatas(type,page,this);
    }

    private void initView() {
        binding.swip.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(a);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (enableLoadMore && newState == RecyclerView.SCROLL_STATE_IDLE){
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if (!adapter.isEmpty() && isLoadMore && lastVisibleItemPosition == adapter.getItemCount() - 1){
                        isLoadMore = true;
                        Log.e("tag", "loadmore");
                        HttpUtils.loadCommunityDatas(type,page,BaseQuFragment.this);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.recycler.setOnItemClickListener(this);
    }

    protected BaseRecyclerAdapter<CommunityBean.DataBean.ListBean> initAdapter() {
        throw new IllegalStateException("必须重写initAdapter方法");
    }

    //创建一个新的实例
    public BaseQuFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        setArguments(bundle);
        return this;
    }

    @Override
    public void loadSuccess(List<CommunityBean.DataBean.ListBean> listBeen, int type) {
        Toast.makeText(a, "成功", Toast.LENGTH_SHORT).show();
        switch (type){
            case HttpUtils.TYPE_REFLASH_SUCCESS:
                adapter.updataDataSource(null);
                binding.swip.setRefreshing(false);
                page ++;
                adapter.updataDataSource(listBeen);
                enableLoadMore(true);
                break;
            case HttpUtils.TYPE_LOAD_MORE_SUCCESS:
                page ++;
                adapter.updataDataSource(listBeen);
                isLoadMore = false;
                break;
            case HttpUtils.TYPE_LOAD_MORE_NODATA:
                adapter.removeFooterView();
                enableLoadMore(false);
                break;
        }
    }

    void enableLoadMore(boolean enable) {
        this.enableLoadMore = enable;
    }

    @Override
    public void loadError(String msg, int type) {
        switch (type){
            case HttpUtils.TYPE_LOAD_MORE_FAILED:
                isLoadMore = false;
            case HttpUtils.TYPE_REFLASH_FAILED:
                binding.swip.setRefreshing(false);
                Toast.makeText(a, "加载失败-->" + msg, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onRefresh() {
        loadFirst();
    }

    @Override
    public void onItemClick(RecyclerView recyclerView, View view, int position) {
        CommunityBean.DataBean.ListBean itemAtPosition = adapter.getItemAtPosition(position);
        if (itemAtPosition != null){
            onItemClick(itemAtPosition);
        }
    }

    protected void onItemClick(CommunityBean.DataBean.ListBean itemAtPosition) {

    }
}
