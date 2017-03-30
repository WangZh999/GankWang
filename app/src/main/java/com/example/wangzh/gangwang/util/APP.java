package com.example.wangzh.gangwang.util;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by WangZh on 2017/3/20.
 */

public class APP extends Application {
    private static Context mContext;
    private static int width;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        WindowManager windowManager = (WindowManager) getContext().
                getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        width = outMetrics.widthPixels;
    }

    public static Context getContext(){
        return mContext;
    }

    public static int getWidth() {
        return width;
    }
}
