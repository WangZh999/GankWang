package com.example.wangzh.gangwang.main.Gank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangzh.gangwang.R;
import com.example.wangzh.gangwang.data.base.Gank;
import com.example.wangzh.gangwang.main.MainPresenter;

import java.util.List;

/**
 * Created by WangZh on 2017/3/22.
 */

public class GankListAdapter extends RecyclerView.Adapter {
    private List<Gank> mGankList;
    private MainPresenter mMainPresenter;

    GankListAdapter(List<Gank> gankList){
        this.mGankList=gankList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gank_item,parent,false);
        return new GankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GankViewHolder mGankViewHolder=(GankViewHolder)holder;
        Gank gank=mGankList.get(position);
        int titleLimit=10;
        int contentLimit =40;
        String title = gank.title.length() < titleLimit ?
                gank.title : gank.title.substring(0, titleLimit - 2) + "...";
        String content = gank.content.length() < contentLimit ?
                gank.content : gank.content.substring(0, contentLimit - 3) + "...";
        mGankViewHolder.gank=gank;
        mGankViewHolder.title.setText(title);
        mGankViewHolder.content.setText(content);

        //set up img
        mGankViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return mGankList.size();
    }

    public void setPresenter(MainPresenter presenter){
        mMainPresenter = presenter;
    }

    private class GankViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View card;
        ImageView imageView;
        TextView title;
        TextView content;
        Gank gank;

        public GankViewHolder(View itemView) {
            super(itemView);
            card =itemView;
            imageView=(ImageView)card.findViewById(R.id.img_gank_item);
            title=(TextView)card.findViewById(R.id.text_gank_item_title);
            content=(TextView)card.findViewById(R.id.text_gank_item_content);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //set up on click lister
            //mPresenter;
            mMainPresenter.startWebActivity(gank.url);
        }
    }
}
