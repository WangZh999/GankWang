package com.example.wangzh.gangwang.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by WangZh on 2017/3/21.
 */

public class AddFragmentToFramelayout {
    public static void add(@NonNull android.support.v4.app.FragmentManager fragmentManager,
                            @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void replace(@NonNull android.support.v4.app.FragmentManager fragmentManager,
                           @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }
}
