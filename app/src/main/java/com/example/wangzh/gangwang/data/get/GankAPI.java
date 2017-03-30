package com.example.wangzh.gangwang.data.get;

import com.example.wangzh.gangwang.data.base.GankData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by WangZh on 2017/3/27.
 */

public interface GankAPI {
    //@GET("data/" + "{type}" + "/" + "{num}" + "/" + "{page}")
    @GET("data/{type}/{num}/{page}")
    Call<GankData> getGankData(
            @Path("type") String type,
            @Path("num") int num,
            @Path("page") int page
    );

}
