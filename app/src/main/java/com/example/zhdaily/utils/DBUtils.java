package com.example.zhdaily.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zhdaily.bean.SQLBean;

import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if(instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }

    public boolean insertNew(SQLBean sqlBean){
        boolean result = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put("newId",sqlBean.getNewId());
        contentValues.put("title",sqlBean.getTitle());
        contentValues.put("image",sqlBean.getImage());
        contentValues.put("timedata",sqlBean.getTimedata());
        long id = db.insert(SQLiteHelper.U_USERINFO,null,contentValues);
        if(id > 0){
            result = true;
        }
        return result;
    }
    public boolean deleteNew(SQLBean sqlBean){
        boolean result = false;
        int i = db.delete(SQLiteHelper.U_USERINFO, "newId = ?", new String[]{sqlBean.getNewId()});
        if(i > 0){
            result = true;
        }
        return result;
    }
    public List<SQLBean> queryNew(){
        List<SQLBean> list  = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + SQLiteHelper.U_USERINFO,null);
        while (cursor.moveToNext()){
            SQLBean sqlBean = new SQLBean();
            sqlBean.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
            sqlBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            sqlBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
            sqlBean.setTimedata(cursor.getString(cursor.getColumnIndex("timedata")));
            list.add(sqlBean);
        }
        cursor.close();
        return list;
    }
    public boolean queryOne(SQLBean sqlBean){
        boolean result = false;
        String sql = "select * from " + SQLiteHelper.U_USERINFO + " where newId=?";
        Cursor cursor = db.rawQuery(sql,new String[]{sqlBean.getNewId()});
        if (cursor.getCount() > 0){
            result = true;
        }
        return result;
    }
    public boolean insertHe(SQLBean sqlBean){
        boolean result = false;
        boolean b = queryOne(sqlBean);
        if (b){

        }else {
            insertNew(sqlBean);
            result = true;
        }
        return result;

    }
    public boolean queryOneByColl(SQLBean sqlBean){
        boolean result = false;
        String sql = "select * from " + SQLiteHelper.U_USERINFO1 + " where newId=?";
        Cursor cursor = db.rawQuery(sql,new String[]{sqlBean.getNewId()});
        if (cursor.getCount() > 0){
            result = true;
        }
        return result;
    }
    public boolean deleteNewByColl(SQLBean sqlBean){
        boolean result = false;
        int i = db.delete(SQLiteHelper.U_USERINFO1, "newId = ?", new String[]{sqlBean.getNewId()});
        if(i > 0){
            result = true;
        }
        return result;
    }
    public boolean insertNewByColl(SQLBean sqlBean){
        boolean result = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put("newId",sqlBean.getNewId());
        contentValues.put("title",sqlBean.getTitle());
        contentValues.put("image",sqlBean.getImage());
        contentValues.put("timedata",sqlBean.getTimedata());
        long id = db.insert(SQLiteHelper.U_USERINFO1,null,contentValues);
        if(id > 0){
            result = true;
        }
        return result;
    }
    public List<SQLBean> queryNewByColl(){
        List<SQLBean> list  = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + SQLiteHelper.U_USERINFO1,null);
        while (cursor.moveToNext()){
            SQLBean sqlBean = new SQLBean();
            sqlBean.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
            sqlBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            sqlBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
            sqlBean.setTimedata(cursor.getString(cursor.getColumnIndex("timedata")));
            list.add(sqlBean);
        }
        cursor.close();
        return list;
    }
}

