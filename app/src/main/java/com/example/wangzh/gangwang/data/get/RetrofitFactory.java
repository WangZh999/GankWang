package com.example.wangzh.gangwang.data.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WangZh on 2017/3/27.
 */

public class RetrofitFactory {
    private static RetrofitFactory retrofitFactory;
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    public static boolean isDebug = false;

    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    private RetrofitFactory(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (RetrofitFactory.isDebug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        httpClient.connectTimeout(12, TimeUnit.SECONDS);
        OkHttpClient client = httpClient.build();

        mRetrofit=new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static RetrofitFactory getInstance(){
        if (retrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (retrofitFactory == null) {
                    retrofitFactory = new RetrofitFactory();
                }
            }
        }
        return retrofitFactory;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

}
