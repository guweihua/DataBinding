package com.example.guweihua.databinding.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.guweihua.databinding.R;
import com.example.guweihua.databinding.VideoActivity;
import com.example.guweihua.databinding.adapter.ShiPinRecyclerAdapter;
import com.example.guweihua.databinding.annoation.ActivityFragmentAnnoation;
import com.example.guweihua.databinding.base.BaseQuFragment;
import com.example.guweihua.databinding.base.BaseRecyclerAdapter;
import com.example.guweihua.databinding.bean.CommunityBean;

/**
 * Created by guweihua on 2017/3/23.
 */
@ActivityFragmentAnnoation(contentId = R.layout.fragment_base_qu)
public class ShiPinFragment extends BaseQuFragment{
    @Override
    protected BaseRecyclerAdapter<CommunityBean.DataBean.ListBean> initAdapter() {
        return new ShiPinRecyclerAdapter(a);
    }

    @Override
    protected void onItemClick(CommunityBean.DataBean.ListBean itemAtPosition) {
        Intent intent = new Intent(a, VideoActivity.class);
        intent.putExtra("videoUrl", itemAtPosition.getVedio_url());
        startActivity(intent);
    }
}
