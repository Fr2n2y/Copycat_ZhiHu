package com.example.zy.zhihu.UI;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zy.zhihu.Activity.MainActivity;
import com.example.zy.zhihu.Activity.NewActivity;
import com.example.zy.zhihu.Adapter.HeadStoriesAdapter;
import com.example.zy.zhihu.Bean.Before;
import com.example.zy.zhihu.Bean.Latest;
import com.example.zy.zhihu.Bean.StoriesBean;
import com.example.zy.zhihu.DataBase.ReadDbHelper;
import com.example.zy.zhihu.HttpGet;
import com.example.zy.zhihu.R;
import com.example.zy.zhihu.View.ScrollPhoto;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

/**
 * Created by zy' on 2016/4/26.
 */
public class MainFragment extends Fragment{
    private Before before;
    private String date;
    private Latest latest;
    private ListView listView;
    private ScrollPhoto photo;
    private Handler handler=new Handler();
    private ReadDbHelper readDbHelper;
    private HeadStoriesAdapter adapter;
    private boolean loading=false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        load();
        readDbHelper=((MainActivity)getActivity()).getReadDbHelper();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_frangment,container,false);
        listView= (ListView) view.findViewById(R.id.news_list);
        View head=inflater.inflate(R.layout.scrollphoto,listView,false);
        photo= (ScrollPhoto) head.findViewById(R.id.scroll);
        photo.setScrollPhotoOnItemClickListener(new ScrollPhoto.ScrollPhotoOnItemClickListener() {
            @Override
            public void onItemClick(View V, Latest.TopStoriesBean bean) {
                Intent intent=new Intent(getActivity(),NewActivity.class);
                StoriesBean storiesBean=new StoriesBean();
                storiesBean.setId(bean.getId());
                storiesBean.setTitle(bean.getTitle());
//                SQLiteDatabase db=getActivity().openOrCreateDatabase("ReadDbHelper.db",Context.MODE_PRIVATE,null);
                SQLiteDatabase db=readDbHelper.getWritableDatabase();
                db.execSQL("replace into ReadCache(newsId) values("+storiesBean.getId()+")");
                db.close();
                intent.putExtra("story",storiesBean);
                adapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });
        adapter=new HeadStoriesAdapter(getActivity());
        listView.addHeaderView(head);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==AbsListView.OnScrollListener.SCROLL_STATE_IDLE&&loading){//滑动停止并且，滑到最底下，自动加载前一天Sotries
                    loadMore("http://news-at.zhihu.com/api/4/stories/before/"+date);
                    loading=false;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (listView != null && listView.getChildCount() > 0) {
                    boolean enable = (firstVisibleItem == 0 && (view.getChildAt(firstVisibleItem).getTop() == 0));
                    MainActivity activity = (MainActivity) getActivity();
                    activity.setSwipeRefreshLayout(enable);
                    if(firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
                        loading=true;
                    }
            }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),NewActivity.class);
                StoriesBean storySeq= (StoriesBean) parent.getAdapter().getItem(position);
                intent.putExtra("story", storySeq);
                SQLiteDatabase db=readDbHelper.getWritableDatabase();
//                SQLiteDatabase db=getActivity().openOrCreateDatabase("ReadDbHelper.db", Context.MODE_PRIVATE,null);
                db.execSQL("replace into ReadCache(newsId) values("+storySeq.getId()+")");
                db.close();
//                TextView tv= (TextView) view.findViewById(R.id.tv_title);
//                tv.setTextColor(getResources().getColor(R.color.clicked_tv_textcolor));
                adapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });
        return view;
    }

    public void load(){
        HttpGet.get("http://news-at.zhihu.com/api/4/stories/latest", new TextHttpResponseHandler() {
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
            }

            public void onSuccess(int i, Header[] headers, String s) {
                paraJson(s);
            }
        });
    }

    private void loadMore(final String url) {
        HttpGet.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                paraJsonBefore(s);
            }
        });

    }

    private void paraJsonBefore(String s) {
        Gson gson=new Gson();
        before=gson.fromJson(s,Before.class);
        if(before == null){
            loading=false;
            return;
        }
        date=before.getDate();
        handler.post(new Runnable() {
            @Override
            public void run() {
                List<StoriesBean> storiesBeens=before.getStories();
                StoriesBean topic=new StoriesBean();
                topic.setType(12);
                topic.setTitle(setDate(date));
                storiesBeens.add(0,topic);
                adapter.addList(storiesBeens);
            }
        });
    }

    public void paraJson(String json){
        Gson gson=new Gson();
        latest=gson.fromJson(json,Latest.class);
        date=latest.getDate();
        photo.setTopStoriesBeans(latest.getTop_stories());//更新TopStory
        handler.post(new Runnable() {
            @Override
            public void run() {
                List<StoriesBean> storiesBeans=latest.getStories();
                StoriesBean storiesBean=new StoriesBean();
                storiesBean.setType(12);
                storiesBean.setTitle("今日热文");
                storiesBeans.add(0,storiesBean);
                adapter.addList(storiesBeans);
            }
        });
    }

    public String setDate(String date) {
        String reDate=date.substring(0,4);
        reDate+="年";
        reDate+=date.substring(4,6);
        reDate+="月";
        reDate+=date.substring(6,8);
        reDate+="日";
        return reDate;
    }

}
