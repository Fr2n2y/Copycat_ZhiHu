package com.example.zy.zhihu.Activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.zy.zhihu.Bean.Latest;
import com.example.zy.zhihu.Bean.StoriesBean;
import com.example.zy.zhihu.Bean.Story;
import com.example.zy.zhihu.DataBase.ReadDbHelper;
import com.example.zy.zhihu.HttpGet;
import com.example.zy.zhihu.R;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.Header;

/**
 * Created by zy' on 2016/5/13.
 */
public class NewActivity extends AppCompatActivity {
    private AppBarLayout appBarLayout;
    private StoriesBean storySeq;
    private WebView webView;
    private ImageView view;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Story story;
    protected void onCreate(Bundle savedInstanceState) {//初始化必须得到的数据只要id和title就可以，image后面通过json获取
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);

        appBarLayout= (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.VISIBLE);
        view= (ImageView) findViewById(R.id.new_iv);
//        view.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);图层阴影,不太好图层全部遮挡...全都有阴影
        storySeq= (StoriesBean) getIntent().getSerializableExtra("story");
        Toolbar toolbar= (Toolbar) findViewById(R.id.new_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collasingToolbar);
        collapsingToolbarLayout.setTitle(storySeq.getTitle());
        webView= (WebView) findViewById(R.id.new_wv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        webView.getSettings().setDatabaseEnabled(true);
        // 开启Application Cache功能
        webView.getSettings().setAppCacheEnabled(true);
        HttpGet.get("http://news-at.zhihu.com/api/4/story/" + storySeq.getId(), new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                s = s.replaceAll("'", "''");
                parseJson(s);
            }
        });
    }


    private void parseJson(String s) {
        Gson gson=new Gson();
        story=gson.fromJson(s,Story.class);
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();
        final ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(story.getImage(),view,options);
        String css = "<link rel=\"stylesheet\" href=\"http:\\/\\/news-at.zhihu.com\\/css\\/news_qa.auto.css?v=4b3e3.css\" type=\"text/css\">";
        String html= "<html><head>" + css + "</head><body>" + story.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");//去掉显示空白图片位置
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }
}
