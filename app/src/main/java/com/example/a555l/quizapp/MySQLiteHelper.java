package com.example.a555l.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_question_num = "number";
    public static final String COLUMN_answer = "answer";

    private static final String DATABASE_NAME = "colleges.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE  =
            "create table " + TABLE_COMMENTS
                    + "("
                    +   COLUMN_ID +" "+"integer primary key autoincrement,"
                    +   COLUMN_question_num +" "+"text not null,"
                    +   COLUMN_answer +" "+"text not null"
                    + ");";


    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to"
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_COMMENTS);
        onCreate(db);
    }
}