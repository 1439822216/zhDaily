package com.example.zhdaily.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static String DB_NAME = "zh.db";
    public static final String U_USERINFO="info";
    public static final String U_USERINFO1="collection";
    public SQLiteHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + U_USERINFO + "(" + "id integer primary key autoincrement, "
        + "newId varchar, "
        + "title varchar, "
        + "image varchar, "
        + "timedata varchar" + ")");
        db.execSQL("create table if not exists " + U_USERINFO1 + "(" + "id integer primary key autoincrement, "
                + "newId varchar, "
                + "title varchar, "
                + "image varchar, "
                + "timedata varchar" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
