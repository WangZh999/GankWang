package com.example.wangzh.gangwang.data.base;

import java.util.Date;

/**
 * Created by WangZh on 2017/3/20.
 */

public class Girl {
    public String _id;
    public Date createdAt;
    public String desc;
    public Date publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;


    public int imageWidth;
    public int imageHeight;

    @Override
    public String toString() {
        return "url: "+url
                +"      createAt: "+createdAt
                +"      desc: "+desc+"";
    }
}
