package com.example.wangzh.gangwang;

import android.content.Context;
import android.net.sip.SipAudioCall;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wangzh.gangwang.data.base.Girl;

import java.util.List;

/**
 * Created by WangZh on 2017/3/20.
 */

public class GirlListAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<Girl> mGirlList;
    private ProgressBar mProgressBar;
    //listener
    SipAudioCall.Listener listener;


    GirlListAdapter(Context context, List<Girl> girlList){
        this.mContext=context;
        this.mGirlList=girlList;
        Log.d("girlListAdapter","create adapter");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.girl_item,parent,false);
        Log.d("girlListAdapter","onCreateViewHolder");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        Girl girl=mGirlList.get(position);
        int limit=48;
        String text = girl.desc.length() < limit ? girl.desc : girl.desc.substring(0, limit) + "...";
        viewHolder.girl=girl;
        viewHolder.textView.setText(text);
        viewHolder.card.setTag(girl.desc);

        //set up girl image view here
        viewHolder.girlView.setImageResource(R.mipmap.ic_launcher);

        Log.d("girlListAdapter","onBindViewHolder");
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mGirlList.size();
    }

    public void setListener(SipAudioCall.Listener listener){
        this.listener=listener;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView girlView;
        TextView textView;
        View card;
        Girl girl;

        public ViewHolder(View viewItem){
            super(viewItem);
            card =viewItem;
            girlView=(ImageView)viewItem.findViewById(R.id.img_girl_item);
            textView=(TextView)viewItem.findViewById(R.id.text_girl_item);
            //ButterKnife.bind(this, itemView);
            card.setOnClickListener(this);

            Log.d("Adapter.ViewHolder","create");
        }

        @Override
        public void onClick(View v) {
            //set up on click lister
            //listener;
        }
    }
}
