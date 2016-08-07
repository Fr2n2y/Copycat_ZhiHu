package com.example.zy.zhihu.View;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zy.zhihu.Activity.MainActivity;
import com.example.zy.zhihu.Bean.StoriesBean;
import com.example.zy.zhihu.Bean.Theme;
import com.example.zy.zhihu.DataBase.WebCacheDbHelper;
import com.example.zy.zhihu.HttpGet;
import com.example.zy.zhihu.R;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.apache.http.Header;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy' on 2016/5/24.
 */
public class theme extends Fragment {
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ListView listView;
    private ImageView imageView;
    private TextView textView;
    private ThemeAdapter adapter;
    private String url;
    private setToolbar toolbar;
    private boolean loadmore;
    private WebCacheDbHelper helper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toolbar= (setToolbar) getActivity();
    }

    public void init() {
        this.helper=((MainActivity)getActivity()).getThemeHelper();
        Bundle date=getArguments();
        url=date.getString("url");
        HttpGet.get("http://news-at.zhihu.com/api/4/theme/" + url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                paraJson(s);
            }
        });
    }

    public void paraJson(String s){
        Gson gson=new Gson();
        Theme t=gson.fromJson(s,Theme.class);
        List<StoriesBean> been=t.getStoriesBeen();
        adapter.setStoriesBeen(been);
        imageLoader.displayImage(t.getImage(),imageView,options);
        textView.setText(t.getDescription());
        toolbar.setToolbar(t.getName());
    }

    public interface setToolbar{
        public void setToolbar(String s);
    }

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        adapter=new ThemeAdapter(getActivity());
        imageLoader=ImageLoader.getInstance();
        View view=inflater.inflate(R.layout.theme_layout,container,false);
        listView= (ListView) view.findViewById(R.id.lv_theme);
        View head=LayoutInflater.from(getActivity()).inflate(R.layout.theme_head,listView,false);
        imageView= (ImageView) head.findViewById(R.id.iv_theme);
        textView= (TextView) head.findViewById(R.id.tv_theme);
        listView.addHeaderView(head);
        listView.setAdapter(adapter);  // 出BUG
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StoriesBean storiesBean= (StoriesBean) parent.getAdapter().getItem(position);
                SQLiteDatabase db=helper.getWritableDatabase();
                db.execSQL("replace into themeCache(newsId) values("+storiesBean.getId()+")");
                db.close();
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(listView!=null&&listView.getChildCount()>0){//childcount是当前可见的
                    boolean enable=(firstVisibleItem == 0 && (view.getChildAt(firstVisibleItem).getTop() == 0));
                    MainActivity activity= (MainActivity) getActivity();
                    activity.setSwipeRefreshLayout(enable);
                    if(firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
                        loadmore=true;
                    }
                }
            }
        });
        return view;
    }

    class ThemeAdapter extends BaseAdapter{
        private List<StoriesBean> beens;
        private Context context;
        public ThemeAdapter(Context context){
            this.context=context;
            beens=new ArrayList<>();
        }

        public void setStoriesBeen(List<StoriesBean> beens){
            this.beens.addAll(beens);
            notifyDataSetChanged();
        }

        public int getCount() {
            return beens.size();
        }

        @Override
        public Object getItem(int position) {
            return beens.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHolder holder=null;
            if (convertView==null) {
                holder=new viewHolder();
                convertView=LayoutInflater.from(context).inflate(R.layout.mainstory_item,parent,false);
                holder.iv= (ImageView) convertView.findViewById(R.id.iv_title);
                holder.tp= (TextView) convertView.findViewById(R.id.tv_topic);
                holder.tv= (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            }else {
                holder= (viewHolder) convertView.getTag();
            }
            StoriesBean bean=beens.get(position);
            SQLiteDatabase db=helper.getWritableDatabase();
            Cursor cursor;
            try {
                cursor=db.rawQuery("select * from themeCache where newsId="+bean.getId(),null);
                try {
                    if(cursor.moveToFirst()){
                        holder.tv.setTextColor(context.getResources().getColor(R.color.clicked_tv_textcolor));
                    }else {
                        holder.tv.setTextColor(context.getResources().getColor(android.R.color.black));
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            db.close();
            holder.tp.setVisibility(View.GONE);
            holder.tv.setText(bean.getTitle());
            ((FrameLayout)holder.tp.getParent()).setBackgroundColor(Color.WHITE);
//            ((FrameLayout)holder.tp.getParent()).setBackgroundColor(Color.BLACK);
            if(bean.getImages()==null){
                holder.iv.setVisibility(View.GONE);
            }else {
                holder.iv.setVisibility(View.VISIBLE);
                imageLoader.displayImage(bean.getImages().get(0),holder.iv,options);
            }
            return convertView;
        }
    }
    public static class viewHolder{
        TextView tp;
        ImageView iv;
        TextView tv;
    }
}
