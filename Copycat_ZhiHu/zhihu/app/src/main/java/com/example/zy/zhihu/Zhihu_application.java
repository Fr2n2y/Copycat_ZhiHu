package com.example.zy.zhihu;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by zhangyang on 16/8/4.
 */
public class Zhihu_application extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        File cacheDir = new File("/data/data/" + getApplicationContext().getPackageName() + "/ImageCache");
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .diskCache(new UnlimitedDiskCache(cacheDir)).build();
        ImageLoader.getInstance().init(configuration);
    }
}
