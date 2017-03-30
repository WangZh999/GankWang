package com.example.wangzh.gangwang.util;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by WangZh on 2017/3/22.
 */

public class GetFragment {
    public static Fragment getVisibleFragment(android.support.v4.app.FragmentManager manager) {
        List<Fragment> fragmentList = manager.getFragments();
        for (Fragment fragment:fragmentList){
            if ((fragment!=null)&&(fragment.isVisible())){
                return fragment;
            }
        }
        return null;
    }
}
