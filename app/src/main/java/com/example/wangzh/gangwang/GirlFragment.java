package com.example.wangzh.gangwang;

import android.net.sip.SipAudioCall;
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

import com.example.wangzh.gangwang.data.base.Girl;
import com.example.wangzh.gangwang.util.APP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZh on 2017/3/20.
 */

public class GirlFragment extends Fragment {
    private Girl mGirl;
    private List<Girl> mGirlList;
    private RecyclerView mRecyclerView;
    private GirlListAdapter mGirlListAdapter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    //listener
    private SipAudioCall.Listener mGirlListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.girl_frag,container,false);
        Log.d("fragment","create start");
        mGirlList =new ArrayList<Girl>();
        for (int i=0;i<20;i++){
            Girl girl=new Girl();
            String desc="wang";
            for (int j=0;j<i+5;j++){
                desc+="wang";
            }
            girl.desc=desc;
            mGirlList.add(girl);

        }

        //set up recycler view
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerView_Girl);
        mRecyclerView.setHasFixedSize(true);
        mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mGirlListAdapter =new GirlListAdapter(APP.getContext(),mGirlList);
        mRecyclerView.setAdapter(mGirlListAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (lastVisibleItem==mStaggeredGridLayoutManager.getItemCount()-1){
                    //Toast.makeText(APP.getContext(),"last",Toast.LENGTH_SHORT).show();
                    //System.out.println("last");
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
}
