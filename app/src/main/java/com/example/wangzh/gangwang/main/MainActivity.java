package com.example.wangzh.gangwang.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.wangzh.gangwang.GankWebview.WebViewActivity;
import com.example.wangzh.gangwang.R;
import com.example.wangzh.gangwang.ViewGirl.GirlActivity;
import com.example.wangzh.gangwang.main.Gank.GankFragment;
import com.example.wangzh.gangwang.main.Girl.GirlFragment;
import com.example.wangzh.gangwang.util.APP;
import com.example.wangzh.gangwang.util.AddFragmentToFramelayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SwipeRefreshLayout.OnRefreshListener, MainActivityContract.Activity, View.OnClickListener{


    private SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar mProgressBar;
    private ImageView but_girl;
    private ImageView but_gank;
    private Fragment mFragment;

    MainPresenter mMainPresenter;


    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APP app=new APP();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        but_girl = (ImageView) findViewById(R.id.button_girl);
        but_girl.setOnClickListener(this);
        but_girl.setAlpha((float)1);
        but_gank = (ImageView) findViewById(R.id.button_gank);
        but_gank.setOnClickListener(this);
        but_gank.setAlpha((float)0.5);

        //set up FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //WebViewActivity.startActivity(MainActivity.this,"");
            }
        });

        //set up NavigationView and DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mMainPresenter=new MainPresenter(this);


        //set up fragment
        //AddFragmentToFramelayout.add(getSupportFragmentManager(), new GirlFragment(), R.id.frag_main);
        mFragment = GirlFragment.getInstance(this);
        ((GirlFragment)mFragment).setPresenter(mMainPresenter);

        AddFragmentToFramelayout.add(getSupportFragmentManager(), mFragment, R.id.frag_main);


        //set up swipe refresh layout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mSwipeRefreshLayout.setProgressViewOffset(true, 0, 35);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id==R.id.button_search){

        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_slideshow) {
            //Intent =new Intent()
        }
        return true;
    }


    //下拉刷新操作
    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    @Override
    public void startWebActivity(String url) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void startGirlActivity(String url) {
        Intent intent = new Intent(MainActivity.this, GirlActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void setProgressVisibility(boolean state) {
        if (state) {
            mProgressBar.setVisibility(View.VISIBLE);
        }else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public Fragment getFragment() {
        return mFragment;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_girl:
                if (mFragment instanceof GankFragment) {
                    //mFragment.
                    but_girl.setAlpha((float)1);
                    but_gank.setAlpha((float)0.5);
                    mFragment = GirlFragment.getInstance(MainActivity.this);
                    ((GirlFragment)mFragment).setPresenter(mMainPresenter);
                    AddFragmentToFramelayout.replace(getSupportFragmentManager(), mFragment, R.id.frag_main);
                }
                break;
            case R.id.button_gank:
                if (mFragment instanceof GirlFragment) {
                    but_gank.setAlpha((float)1);
                    but_girl.setAlpha((float)0.5);
                    mFragment = GankFragment.getInstance(MainActivity.this);
                    ((GankFragment)mFragment).setPresenter(mMainPresenter);
                    AddFragmentToFramelayout.replace(getSupportFragmentManager(), mFragment, R.id.frag_main);
                }
                break;
            default:
                break;
        }

    }
}
