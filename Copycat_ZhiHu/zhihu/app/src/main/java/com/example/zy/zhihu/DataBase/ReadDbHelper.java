package com.example.zy.zhihu.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zy' on 2016/5/19.
 */
public class ReadDbHelper extends SQLiteOpenHelper {

    public ReadDbHelper(Context context,int version){
        super(context,"ReadDbHelper.db",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists ReadCache (id INTEGER primary key autoincrement,newsId INTEGER unique)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
