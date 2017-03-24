package com.example.guweihua.databinding.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.example.guweihua.databinding.base.BaseHolder;
import com.example.guweihua.databinding.base.BaseRecyclerAdapter;
import com.example.guweihua.databinding.bean.CommunityBean;

/**
 * Created by guweihua on 2017/3/24.
 */

public class DuanZiRecyclerAdapter extends BaseRecyclerAdapter<CommunityBean.DataBean.ListBean>{

    public DuanZiRecyclerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected BaseHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected int getChildItemViewType(int position) {
        return 0;
    }
}
