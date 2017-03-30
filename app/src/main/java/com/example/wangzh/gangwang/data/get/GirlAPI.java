package com.example.wangzh.gangwang.data.get;

import com.example.wangzh.gangwang.data.base.GirlData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by WangZh on 2017/3/27.
 */

public interface GirlAPI {
    @GET("data/福利/{num}/{page}")
        //@GET("data/福利/10/1")
    Call<GirlData> getGirlData(
            @Path("num") int num,
            @Path("page") int page
    );

}

