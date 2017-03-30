package com.example.wangzh.gangwang.ViewGirl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangzh.gangwang.R;
import com.example.wangzh.gangwang.data.get.GetImage;
import com.example.wangzh.gangwang.util.APP;

/**
 * Created by WangZh on 2017/3/25.
 */

public class GirlActivity extends AppCompatActivity{

    private TextView mTitle;
    private TextView mContent;
    private ImageView mGirlImg;

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);

        Intent intent = getIntent();
        String url=intent.getStringExtra("url");

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTitle = (TextView) findViewById(R.id.text_girl_title);
        mContent = (TextView) findViewById(R.id.text_girlB);
        mGirlImg = (ImageView) findViewById(R.id.imageView_girl);

        GetImage getImage = GetImage.getInstance();
        getImage.displayImage(mGirlImg, url, APP.getWidth());
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(x1 - x2 > 50) {
                Toast.makeText(GirlActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                Toast.makeText(GirlActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
