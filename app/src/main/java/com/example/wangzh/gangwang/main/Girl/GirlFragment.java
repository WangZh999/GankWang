package com.example.wangzh.gangwang.main.Girl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangzh.gangwang.R;
import com.example.wangzh.gangwang.data.base.Girl;
import com.example.wangzh.gangwang.main.MainActivityContract;
import com.example.wangzh.gangwang.main.MainPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZh on 2017/3/20.
 */

public class GirlFragment extends Fragment implements MainActivityContract.View{
    private static GirlFragment mGirlFragment;
    //private  MainActivityContract.Activity mActivityContract;
    private Girl mGirl;
    private List<Girl> mGirlList;
    private RecyclerView mRecyclerView;
    private GirlListAdapter mGirlListAdapter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    //mPresenter
    private MainPresenter mMainPresenter;

    private final int num = 10;
    private static int page = 0;


    public GirlFragment(){
    }


    public static GirlFragment getInstance(MainActivityContract.Activity contract){
        if (mGirlFragment==null){
            mGirlFragment=new GirlFragment();
        }
        return mGirlFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.girl_frag,container,false);
        Log.d("fragment","create start");


        //for test
        if (mGirlList == null) {
            mGirlList =new ArrayList<Girl>();
            page = 1;
            mMainPresenter.getGirlData(num, 1);

            //mMainPresenter.getGirlData(num, 2);
        }

        //set up recycler view
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerView_Girl);
        mRecyclerView.setHasFixedSize(true);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        mGirlListAdapter = new GirlListAdapter(mGirlList);
        mGirlListAdapter.setPresenter(mMainPresenter);
        mRecyclerView.setAdapter(mGirlListAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ((newState == RecyclerView.SCROLL_STATE_IDLE)
                        && (lastVisibleItem == mStaggeredGridLayoutManager.getItemCount() - 1)) {
                    //Toast.makeText(APP.getContext(),"last",Toast.LENGTH_SHORT).show();
                    page++;
                    mMainPresenter.getGirlData(num, page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //lastPositions=new int[(mStaggeredGridLayoutManager.getSpanCount())];
                int []lastPositions=mStaggeredGridLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem=findMax(lastPositions);
            }
        });

        Log.d("fragment","create finish");

        return view;
    }


    private int findMax(int [] positions){
        int max=positions[0];
        for (int i=1;i<positions.length;i++){
            if (positions[i]>max){
                max=positions[i];
            }
        }
        return max;
    }


    @Override
    public void setPresenter(MainPresenter presenter) {
        mMainPresenter = presenter;
    }

    @Override
    public void addData(List<?> data) {

        if (data != null) {
            for (Object girl : data) {
                mGirlList.add((Girl) girl);
            }
        }
        mGirlListAdapter.notifyDataSetChanged();

    }
}
