package com.example.wangzh.gangwang.main.Gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzh.gangwang.R;
import com.example.wangzh.gangwang.data.base.Gank;
import com.example.wangzh.gangwang.main.MainActivityContract;
import com.example.wangzh.gangwang.main.MainPresenter;
import com.example.wangzh.gangwang.util.APP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZh on 2017/3/22.
 */

public class GankFragment extends Fragment implements MainActivityContract.View{
    private static GankFragment mGankFragment;
    private  MainActivityContract.Activity mActivityContract;
    private Gank mGank;
    private RecyclerView mRecyclerView;
    private GankListAdapter mGankListAdapter;
    private List<Gank> mGankList;
    private LinearLayoutManager mLinearLayoutManager;
    //listener
    MainPresenter mMainPresenter;

    private final int num = 10;
    private static int page = 0;

    public static GankFragment getInstance(MainActivityContract.Activity contract){
        if (mGankFragment==null){
            mGankFragment=new GankFragment();
        }
        return mGankFragment;
    }
    public void setGankList(List<Gank> gankList){
        this.mGankList=gankList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gank_frag,container,false);
        //for test
        if (mGankList == null) {
            mGankList=new ArrayList<Gank>();
            page = 2;
            mMainPresenter.getGankData("iOS", num, 1);
            mMainPresenter.getGankData("iOS", num, 2);
        }


        //set up recycler view
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_Gank);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager=new LinearLayoutManager
                (APP.getContext(), OrientationHelper.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mGankListAdapter = new GankListAdapter(mGankList);
        mGankListAdapter.setPresenter(mMainPresenter);
        mRecyclerView.setAdapter(mGankListAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ((newState == RecyclerView.SCROLL_STATE_IDLE)
                        && (lastVisibleItem == mLinearLayoutManager.getItemCount() - 1)) {
                    //do something
                    page++;
                    mMainPresenter.getGankData("iOS", num, page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem=mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });

        return view;
    }


    @Override
    public void setPresenter(MainPresenter presenter) {
        mMainPresenter = presenter;
    }

    @Override
    public void addData(List<?> data) {
        //mGankList.addAll()
        if (data != null) {
            for (Object gank : data) {
                mGankList.add((Gank) gank);
            }
        }
        mGankListAdapter.notifyDataSetChanged();
    }
}
