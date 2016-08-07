package com.example.zy.zhihu.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zy.zhihu.Activity.MainActivity;
import com.example.zy.zhihu.Activity.NewActivity;
import com.example.zy.zhihu.Bean.StoriesBean;
import com.example.zy.zhihu.DataBase.ReadDbHelper;
import com.example.zy.zhihu.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy' on 2016/4/27.
 */
public class HeadStoriesAdapter extends BaseAdapter {
    private List<StoriesBean> storySeqs;
    private Context context;
    private ImageLoader loader;
    private DisplayImageOptions options;
    public HeadStoriesAdapter(Context context){
        this.context=context;
        this.storySeqs=new ArrayList<>();
        this.loader=ImageLoader.getInstance();
        options=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
    }

    public void addList(List<StoriesBean> storySeqs){
        this.storySeqs.addAll(storySeqs);
        notifyDataSetChanged();
    }

    public int getCount() {
        return storySeqs.size();
    }

    public Object getItem(int position) {
        return storySeqs.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
//        把每一个子View都放在Holder中，当第一次创建convertView对象时，把这些子view找出来。
//        然后用convertView的setTag将viewHolder设置到Tag中，以便系统第二次绘制ListView时从Tag中取出。
//        当第二次重用convertView时，只需从convertView中getTag取出来就可以。  记得优化~~~(づ￣ 3￣)づ~~~
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.mainstory_item,parent,false);
            viewHolder.tv_topic= (TextView) convertView.findViewById(R.id.tv_topic);
            viewHolder.iv_title= (ImageView) convertView.findViewById(R.id.iv_title);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);//设置一个格外的数据getTag()取出来
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        /*

        设置标题的 before显示的是年月日星期几，Latest显示的是今日热文。使用TextView来进行显示

         */
        StoriesBean storiesBean=storySeqs.get(position);
        SQLiteDatabase db=context.openOrCreateDatabase("ReadDbHelper.db",Context.MODE_PRIVATE,null);
        Cursor cursor= null;
        try {
            cursor = db.rawQuery("select * from ReadCache where newsId ="+storiesBean.getId(),null);
            try {
                if(cursor.moveToFirst()){
                    viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.clicked_tv_textcolor));
                }else {
                    viewHolder.tv_title.setTextColor(context.getResources().getColor(android.R.color.black));
                }
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

        if (storiesBean.getType() == 12) {
            ((FrameLayout) viewHolder.tv_topic.getParent()).setBackgroundColor(Color.TRANSPARENT);
            viewHolder.tv_title.setVisibility(View.GONE);
            viewHolder.iv_title.setVisibility(View.GONE);
            viewHolder.tv_topic.setVisibility(View.VISIBLE);
            viewHolder.tv_topic.setText(storiesBean.getTitle());
        }
        else {
            ((FrameLayout) viewHolder.tv_topic.getParent()).setBackgroundColor(Color.WHITE);
            viewHolder.tv_topic.setVisibility(View.GONE);
            viewHolder.tv_title.setVisibility(View.VISIBLE);
            viewHolder.iv_title.setVisibility(View.VISIBLE);
            viewHolder.tv_title.setText(storiesBean.getTitle());
            loader.displayImage(storiesBean.getImages().get(0), viewHolder.iv_title, options);
        }
        return convertView;
    }

    public static class ViewHolder {
        TextView tv_topic;
        TextView tv_title;
        ImageView iv_title;
    }
}
