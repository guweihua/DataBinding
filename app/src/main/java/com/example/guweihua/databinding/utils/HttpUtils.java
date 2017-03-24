package com.example.guweihua.databinding.utils;

import com.example.guweihua.databinding.MyApplication;
import com.example.guweihua.databinding.bean.CommentsBean;
import com.example.guweihua.databinding.bean.CommunityBean;
import com.example.guweihua.databinding.bean.DetailsBean;
import com.example.guweihua.databinding.bean.RepilesBean;
import com.example.guweihua.databinding.bean.UpDownBean;
import com.example.guweihua.databinding.bean.VideoBean;
import com.example.guweihua.databinding.service.CommentService;
import com.example.guweihua.databinding.service.CommunityService;
import com.example.guweihua.databinding.service.DetailsService;
import com.example.guweihua.databinding.service.OnLoadDataFinishListener;
import com.example.guweihua.databinding.service.RepilesService;
import com.example.guweihua.databinding.service.UpDownService;
import com.example.guweihua.databinding.service.VideoService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guweihua on 2017/3/24.
 */

public class HttpUtils {
    public static final int TYPE_REFLASH_SUCCESS = 1;
    public static final int TYPE_REFLASH_FAILED = 2;
    public static final int TYPE_LOAD_MORE_SUCCESS = 3;
    public static final int TYPE_LOAD_MORE_FAILED = 4;
    public static final int TYPE_LOAD_MORE_NODATA = 5;
    public static final int TYPE_UNNOKNED = -1;

    //下载社区数据
    public static void loadCommunityDatas(String type, final int page, final OnLoadDataFinishListener<List<CommunityBean.DataBean.ListBean>> listOnLoadDataFinishListener){
        MyApplication.app.retrofit.create(CommunityService.class).getDatas(type,page).enqueue(new Callback<CommunityBean>() {
            @Override
            public void onResponse(Call<CommunityBean> call, Response<CommunityBean> response) {
              try {
                  List<CommunityBean.DataBean.ListBean> list = response.body().getData().getList();
                  listOnLoadDataFinishListener.loadSuccess(list,getType(list,page));
              }catch (Exception e){
                  e.printStackTrace();
                  listOnLoadDataFinishListener.loadError(e.getMessage(),getType(null,page));
              }
            }

            @Override
            public void onFailure(Call<CommunityBean> call, Throwable throwable) {
                throwable.printStackTrace();
                listOnLoadDataFinishListener.loadError(throwable.getMessage(),getType(null,page));
            }
        });
    }

    //下载Video的数据
    public static void loadVideoDatas(String videoUrl, final OnLoadDataFinishListener<VideoBean> onLoadDataFinishListener){
        MyApplication.retrofit.create(VideoService.class).getVideoDatas(videoUrl).enqueue(new Callback<VideoBean>() {
            @Override
            public void onResponse(Call<VideoBean> call, Response<VideoBean> response) {
                VideoBean body = response.body();
                onLoadDataFinishListener.loadSuccess(body,0);
            }

            @Override
            public void onFailure(Call<VideoBean> call, Throwable throwable) {
                onLoadDataFinishListener.loadError(throwable.getMessage(),0);
            }
        });
    }

    public static void updown(String id, int type, int typeValue, final OnLoadDataFinishListener<UpDownBean> onLoadDataFinishListener){
        MyApplication.retrofit.create(UpDownService.class).getUpDownState(id,type,typeValue).enqueue(new Callback<UpDownBean>() {
            @Override
            public void onResponse(Call<UpDownBean> call, Response<UpDownBean> response) {
                if (response.isSuccessful()){
                    onLoadDataFinishListener.loadSuccess(response.body(),0);
                }else {
                    try {
                        String string = response.errorBody().string();
                        onLoadDataFinishListener.loadSuccess(new Gson().fromJson(string,UpDownBean.class),0);
                    } catch (IOException e) {
                        e.printStackTrace();
                        onLoadDataFinishListener.loadError(e.getMessage(),0);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpDownBean> call, Throwable throwable) {
                onLoadDataFinishListener.loadError(throwable.getMessage(),0);
            }
        });
    }

    //下载细节数据
    public static void loadDetailsData(String id, final OnLoadDataFinishListener<DetailsBean> onLoadDataFinishListener){
        MyApplication.retrofit.create(DetailsService.class).getDetailsData(id).enqueue(new Callback<DetailsBean>() {
            @Override
            public void onResponse(Call<DetailsBean> call, Response<DetailsBean> response) {
                if(response.isSuccessful()){
                    onLoadDataFinishListener.loadSuccess(response.body(),0);
                }else {
                    try {
                        onLoadDataFinishListener.loadError(response.errorBody().string(), 0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailsBean> call, Throwable throwable) {
                onLoadDataFinishListener.loadError(throwable.getMessage(),0);
            }
        });
    }

    //下载评论的数据(Commment)
    public static void loadCommentsDatas(int id, final int page, final OnLoadDataFinishListener<CommentsBean> onLoadDataFinishListener){
        MyApplication.retrofit.create(CommentService.class).getCommentsData(id,page + "").enqueue(new Callback<CommentsBean>() {
            @Override
            public void onResponse(Call<CommentsBean> call, Response<CommentsBean> response) {
                if (response.isSuccessful()){
                    CommentsBean body = response.body();
                    onLoadDataFinishListener.loadSuccess(body,getType(body.getData().getComment_list(),page));
                }else {
                    try {
                        onLoadDataFinishListener.loadError(response.errorBody().string(),getType(null,page));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentsBean> call, Throwable throwable) {
                onLoadDataFinishListener.loadError(throwable.getMessage(),getType(null,page));
            }
        });
    }

    //下载
    public static void loadRepilesData(int id, String content, final OnLoadDataFinishListener<RepilesBean> onLoadDataFinishListener){
        MyApplication.retrofit.create(RepilesService.class).getRepilesData(id,content).enqueue(new Callback<RepilesBean>() {
            @Override
            public void onResponse(Call<RepilesBean> call, Response<RepilesBean> response) {
                if (response.isSuccessful()){
                    onLoadDataFinishListener.loadSuccess(response.body(),0);
                }else {
                    try {
                        onLoadDataFinishListener.loadError(response.errorBody().string(),0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RepilesBean> call, Throwable throwable) {
                onLoadDataFinishListener.loadError(throwable.getMessage(),0);
            }
        });
    }

    private static int getType(List list, int page) {
        if (list == null && page == 1){
            return TYPE_REFLASH_FAILED;
        }
        if (list == null && page > 1){
            return TYPE_LOAD_MORE_FAILED;
        }
        if (list.size() == 0){
            return TYPE_LOAD_MORE_NODATA;
        }
        if (list.size() > 0 && page == 1){
                return TYPE_REFLASH_SUCCESS;
        }
        if (list.size() > 0 && page > 1){
            return TYPE_LOAD_MORE_SUCCESS;
        }
        return TYPE_UNNOKNED;
    }
}
