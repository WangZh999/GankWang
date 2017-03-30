package com.example.wangzh.gangwang.main;

import android.util.Log;

import com.example.wangzh.gangwang.data.base.Gank;
import com.example.wangzh.gangwang.data.base.GankData;
import com.example.wangzh.gangwang.data.base.Girl;
import com.example.wangzh.gangwang.data.base.GirlData;
import com.example.wangzh.gangwang.data.get.GankAPI;
import com.example.wangzh.gangwang.data.get.RetrofitFactory;
import com.example.wangzh.gangwang.data.get.GirlAPI;
import com.example.wangzh.gangwang.main.Gank.GankFragment;
import com.example.wangzh.gangwang.main.Girl.GirlFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by WangZh on 2017/3/29.
 */

public class MainPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.Activity mView;
    private GirlFragment mGirlFragment;
    private GankFragment mGankFragment;
    private Retrofit mRetrofit;

    public MainPresenter(MainActivityContract.Activity view){
        this.mView = view;
        RetrofitFactory retrofitFactory= RetrofitFactory.getInstance();
        mRetrofit=retrofitFactory.getRetrofit();
        mGirlFragment = GirlFragment.getInstance(view);
        mGankFragment = GankFragment.getInstance(view);
    }


    @Override
    public void getGirlData(int num, int page) {
        GirlAPI girlAPI = mRetrofit.create(GirlAPI.class);
        Call<GirlData> call = girlAPI.getGirlData(num, page);
        call.enqueue(new Callback<GirlData>() {
            @Override
            public void onResponse(Call<GirlData> call, Response<GirlData> response) {
                List<Girl> girlList = response.body().results;

                mGirlFragment.addData(girlList);
                Log.e("get: ", "get girl data");
            }
            @Override
            public void onFailure(Call<GirlData> call, Throwable t) {

            }
        });

    }

    @Override
    public void getGankData(String type, int num, int page) {
        GankAPI gankAPI = mRetrofit.create(GankAPI.class);
        Call<GankData> call = gankAPI.getGankData(type, num, page);
        call.enqueue(new Callback<GankData>() {
            @Override
            public void onResponse(Call<GankData> call, Response<GankData> response) {
                List<Gank> gankList=response.body().results;
                for (Gank gank : gankList) {
                    gank.title = gank.desc;
                    gank.content = gank.url;
                }
                mGankFragment.addData(gankList);
                Log.e("get: ", "get gank data");
            }

            @Override
            public void onFailure(Call<GankData> call, Throwable t) {

            }
        });
    }

    @Override
    public void startWebActivity(String url) {
        mView.startWebActivity(url);
    }

    @Override
    public void startGirlActivity(String url) {
        mView.startGirlActivity(url);
    }

}
