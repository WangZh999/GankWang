package com.example.wangzh.gangwang.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import com.example.wangzh.gangwang.util.APP;

/**
 * Created by WangZh on 2017/3/21.
 */

public class GirlRecyclerView extends RecyclerView {

    public GirlRecyclerView(Context context) {
        super(context);
    }

    public GirlRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GirlRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setScrollUpListener(){
        new OnScrollListener(){
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isSlideToBottom()) {
                    // show progress bar
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           //get data from net
                           // add data
                        }
                    },1000);
                    // hide progress bar
                    Toast.makeText(APP.getContext(),"add data",Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView,dx, dy);
            }
        };
    }


    public boolean isSlideToBottom() {
        if (computeVerticalScrollExtent() + computeVerticalScrollOffset()
                >= computeVerticalScrollRange())
            return true;
        return false;
    }
}
