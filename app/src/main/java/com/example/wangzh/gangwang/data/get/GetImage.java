package com.example.wangzh.gangwang.data.get;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by WangZh on 2017/3/30.
 */

public class GetImage {
    private static GetImage mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    final static boolean isDebug = true;

    private GetImage(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (GetImage.isDebug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logging);
        }
        mOkHttpClient = httpClientBuilder.build();
        //mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public static GetImage getInstance() {
        if (mInstance == null) {
            synchronized (GetImage.class){
                if (mInstance == null){
                    mInstance = new GetImage();
                }
            }
        }
        return mInstance;
    }

    public void displayImage(final ImageView view, final String url,int width) {
        final String url_width;
        if (width == 0) {
            url_width = url + "?imageView2/0/w/" + 500;
        }else {
            url_width = url + "?imageView2/0/w/" + width;
        }
            //
            final Request request = new Request.Builder()
                    .url(url_width)
                    .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //view.setVisibility(View.GONE);
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                       view.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("get image", "ok");
                InputStream is = null;
                try {
                    is = response.body().byteStream();
                    try {
                        is.reset();
                    } catch (IOException e) {
                        final Request request = new Request.Builder()
                                .url(url_width)
                                .build();
                        Call call2 = mOkHttpClient.newCall(request);
                        response = call2.execute();
                        is = response.body().byteStream();
                    }
                    //final Bitmap bm = BitmapFactory.decodeStream(is, null, ops);
                    final Bitmap bm=BitmapFactory.decodeStream(is);
                    mDelivery.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bm);
                        }
                    });
                } catch (Exception e) {
                }finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}