package com.example.zy.zhihu.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zy' on 2016/5/18.
 */
public class WebCacheDbHelper extends SQLiteOpenHelper {
    public WebCacheDbHelper(Context context,int version){
        super(context,"theme",null,version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists themeCache (id INTEGER primary key autoincrement,newsId INTEGER unique)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
