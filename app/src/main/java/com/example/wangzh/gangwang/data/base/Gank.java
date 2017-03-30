package com.example.wangzh.gangwang.data.base;

import java.util.Date;
import java.util.List;

/**
 * Created by WangZh on 2017/3/22.
 */

public class Gank {
    public String _id;
    public Date createdAt;
    public String desc;
    public List<String> images;
    public Date publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;

    public Date updatedAt;
    public int imageWidth;
    public int imageHeight;
    public String title;
    public String content;

    @Override
    public String toString() {
        String img = "";
        if (images != null) {
            for (String str : images) {
                img += str + "      ";
            }
        }

        return "img:     " + img
                + "url:    " + url
                + "     desc:    " + desc;
    }
}
