package com.example.zy.zhihu;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * Created by zy' on 2016/4/27.
 */
public class HttpGet {
    String BASE_URL="http://news-at.zhihu.com/api/4/story/";
    String News="http://news-at.zhihu.com/api/4/news/";
    private static AsyncHttpClient client=new AsyncHttpClient();
    public static void get(String url,ResponseHandlerInterface handler){
        client.get(url,handler);
    }
    public static void getImage(String url,ResponseHandlerInterface handler){
        client.get(url,handler);
    }


}
