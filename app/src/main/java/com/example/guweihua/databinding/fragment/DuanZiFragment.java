package com.example.guweihua.databinding.fragment;

import com.example.guweihua.databinding.R;
import com.example.guweihua.databinding.adapter.DuanZiRecyclerAdapter;
import com.example.guweihua.databinding.annoation.ActivityFragmentAnnoation;
import com.example.guweihua.databinding.base.BaseQuFragment;
import com.example.guweihua.databinding.base.BaseRecyclerAdapter;
import com.example.guweihua.databinding.bean.CommunityBean;

/**
 * Created by guweihua on 2017/3/23.
 */

@ActivityFragmentAnnoation(contentId = R.layout.fragment_base_qu)
public class DuanZiFragment extends BaseQuFragment{
    @Override
    protected BaseRecyclerAdapter<CommunityBean.DataBean.ListBean> initAdapter() {
        return new DuanZiRecyclerAdapter(a);
    }
}
