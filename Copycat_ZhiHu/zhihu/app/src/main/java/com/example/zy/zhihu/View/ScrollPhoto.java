package com.example.zy.zhihu.View;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.example.zy.zhihu.Bean.Latest;
import com.example.zy.zhihu.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy' on 2016/4/26.
 */
public class ScrollPhoto extends FrameLayout implements OnClickListener {
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private List<Latest.TopStoriesBean> topStoriesBeans;
    private List<View> views;
    private Context context;
    private List<ImageView> imageViews;
    private ViewPager vp;
    private LinearLayout scroll;
    private ScrollPhotoOnItemClickListener scrollPhotoOnItemClickListener;
    private int currentItem;
    private boolean isAutoPlay;
    private Handler handler=new Handler();


    public ScrollPhoto(Context context) {
        this(context, null);
    }

    public ScrollPhoto(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollPhoto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        this.context = context;
        this.topStoriesBeans = new ArrayList<>();
        initView();
    }

    public void setTopStoriesBeans(List<Latest.TopStoriesBean> topStoriesBeans){
        this.topStoriesBeans=topStoriesBeans;
        reset();
    }

    private void reset() {
        views.clear();
        initUI();
    }

    private void initView() {
        views = new ArrayList<>();
        imageViews = new ArrayList<>();
    }

    private void initUI() {
        View view = LayoutInflater.from(context).inflate(R.layout.scrollphoto_layout, this, true);
        vp = (ViewPager) view.findViewById(R.id.scroVp);
        scroll = (LinearLayout) view.findViewById(R.id.scroll_ll);
        scroll.removeAllViews();

        int len = topStoriesBeans.size();
        for (int i = 0; i < len; i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.leftMargin = 5;
            params.rightMargin = 5;
            scroll.addView(imageView, params);
            imageViews.add(imageView);
        }

        for (int i = 0; i <= len+1 ; i++) {
            View fm = LayoutInflater.from(context).inflate(R.layout.photo, null);
            ImageView iv = (ImageView) fm.findViewById(R.id.im_title);
            TextView tv = (TextView) fm.findViewById(R.id.tv_title);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if(i==0){
                imageLoader.displayImage(topStoriesBeans.get(len-1).getImage(),iv,options);
                tv.setText(topStoriesBeans.get(len-1).getTitle());
            }else if(i==len+1){
                imageLoader.displayImage(topStoriesBeans.get(0).getImage(),iv,options);
                tv.setText(topStoriesBeans.get(0).getTitle());
            }else {
                imageLoader.displayImage(topStoriesBeans.get(i-1).getImage(),iv,options);
                tv.setText(topStoriesBeans.get(i-1).getTitle());
            }
            fm.setOnClickListener(this);
            views.add(fm);
        }
        vp.setAdapter(new PhotoAdapter());
        vp.setFocusable(true);
        vp.setCurrentItem(1);
        vp.addOnPageChangeListener(new MyOnPageChangeListener());
        currentItem=1;
        start();
    }

    private void start() {
        isAutoPlay=true;
        handler.postDelayed(auto,3000);
    }

    private final Runnable auto=new Runnable() {
        public void run() {
            if (isAutoPlay) {
                currentItem = currentItem % (topStoriesBeans.size() +1) + 1;
//                currentItem=vp.getCurrentItem();
                if (currentItem == 1) {
                    vp.setCurrentItem(currentItem, false);
                    handler.post(auto);
                } else {
                    vp.setCurrentItem(currentItem);
                    handler.postDelayed(auto, 3000);
                }
            } else {
//                vp.setCurrentItem(currentItem);
                handler.postDelayed(auto, 3000);
            }
        }
    };

    public void setScrollPhotoOnItemClickListener(ScrollPhotoOnItemClickListener listener){
        this.scrollPhotoOnItemClickListener=listener;
    }

    public interface ScrollPhotoOnItemClickListener{
        public void onItemClick(View V, Latest.TopStoriesBean bean);
    }

    public void onClick(View v) {
       if(scrollPhotoOnItemClickListener!=null){
           Latest.TopStoriesBean topStoriesBean=topStoriesBeans.get(vp.getCurrentItem()-1);
           scrollPhotoOnItemClickListener.onItemClick(v,topStoriesBean);
       }
    }

    class PhotoAdapter extends PagerAdapter {

        public int getCount() {
            return views.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        public void onPageSelected(int position) {
            for (int i = 0; i < imageViews.size(); i++) {
                if (i == position - 1) {
                    imageViews.get(i).setImageResource(R.mipmap.dot_focus);
                } else {
                    imageViews.get(i).setImageResource(R.mipmap.dot_blur);
                }
            }
        }

        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case 1://正在滑动
                    isAutoPlay = false;
                    break;
                case 2://滑动完毕
                    isAutoPlay = true;
//                        currentItem=vp.getCurrentItem();
                    break;
                case 0://什么都没干 && !isAutoPlay
                    if (vp.getCurrentItem() == 0) {
                        vp.setCurrentItem(topStoriesBeans.size(), false);
                    } else if (vp.getCurrentItem() == topStoriesBeans.size() + 1) {
                        vp.setCurrentItem(1, false);//vp.getCurrentItem()==vp.getAdapter().getCount()-1
                    }
                    currentItem = vp.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }
    }
}
