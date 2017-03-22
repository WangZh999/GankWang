package com.example.wangzh.gangwang.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by WangZh on 2017/3/20.
 */

public class APP extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
