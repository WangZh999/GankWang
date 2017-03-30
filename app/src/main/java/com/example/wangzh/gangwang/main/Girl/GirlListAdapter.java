package com.example.wangzh.gangwang.main.Girl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangzh.gangwang.R;
import com.example.wangzh.gangwang.data.base.Girl;
import com.example.wangzh.gangwang.data.get.GetImage;
import com.example.wangzh.gangwang.main.MainPresenter;
import com.example.wangzh.gangwang.util.APP;

import java.util.List;

/**
 * Created by WangZh on 2017/3/20.
 */

public class GirlListAdapter extends RecyclerView.Adapter{
    private List<Girl> mGirlList;
    //mPresenter
    private MainPresenter mMainPresenter;
    private GetImage mGetImage;

    private static final int imgWidth = APP.getWidth() / 2;


    GirlListAdapter( List<Girl> girlList){
        this.mGirlList=girlList;
        mGetImage = GetImage.getInstance();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.girl_item,parent,false);
        return new GirlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        GirlViewHolder viewHolder=(GirlViewHolder)holder;
        Girl girl=mGirlList.get(position);
        int limit=48;
        String text = girl.desc.length() < limit ? girl.desc : girl.desc.substring(0, limit) + "...";
        viewHolder.girl=girl;
        viewHolder.textView.setText(text);
        viewHolder.card.setTag(girl.desc);

        //set up girl image view here
        viewHolder.girlView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.girlView.setMinimumHeight(300);
        mGetImage.displayImage(viewHolder.girlView, girl.url, imgWidth);
    }


    @Override
    public int getItemCount() {
        return mGirlList.size();
    }

    public void setPresenter(MainPresenter presenter){
        this.mMainPresenter = presenter;
    }


    private class GirlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView girlView;
        TextView textView;
        View card;
        Girl girl;

        public GirlViewHolder(View viewItem){
            super(viewItem);
            card =viewItem;
            girlView=(ImageView)viewItem.findViewById(R.id.img_girl_item);
            textView=(TextView)viewItem.findViewById(R.id.text_girl_item);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mMainPresenter.startGirlActivity(girl.url);
        }
    }
}
