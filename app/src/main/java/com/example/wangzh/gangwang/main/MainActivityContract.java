package com.example.wangzh.gangwang.main;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by WangZh on 2017/3/22.
 */

public interface MainActivityContract {
    public interface Activity {
        void startWebActivity(String url);
        void startGirlActivity(String url);
        void setProgressVisibility(boolean state);
        Fragment getFragment();
    }

    public interface View{
        void setPresenter(MainPresenter presenter);
        void addData(List<?> data);
    }

    public interface Presenter{
        void getGirlData(int num, int page);
        void getGankData(String typr, int num, int page);
        void startWebActivity(String url);
        void startGirlActivity(String url);
    }

}
