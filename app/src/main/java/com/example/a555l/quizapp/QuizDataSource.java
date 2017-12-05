package com.example.a555l.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.a555l.quizapp.MySQLiteHelper.COLUMN_ID;
import static com.example.a555l.quizapp.MySQLiteHelper.COLUMN_question_num;
import static com.example.a555l.quizapp.MySQLiteHelper.TABLE_COMMENTS;

public class QuizDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColums = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_question_num,MySQLiteHelper.COLUMN_answer};

    public QuizDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean checkRecord(){
        boolean rValue = false;
        Cursor cursor = database.query(TABLE_COMMENTS,
                allColums,null,null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                // Data Is Exist;
                rValue = true;
            } while (cursor.moveToNext());
        }

        return rValue;
    }


    public void insertRecord(String number,String answer) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_question_num,number);
        values.put(MySQLiteHelper.COLUMN_answer,answer);
        database.insert(TABLE_COMMENTS, null, values);
    }


    public List<Quiz> get_searchCollege(){
        List<Quiz>quizs = new ArrayList<Quiz>();

        Cursor cursor = database.query(TABLE_COMMENTS,
                allColums,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Quiz quiz = cursorToComment(cursor);
            quizs.add(quiz);
            cursor.moveToNext();
        }


        cursor.close();
        return quizs;
    }
    public void deleteComment() {
        database.delete(TABLE_COMMENTS, null, null);

        Log.d("insert","delete:");
    }


    private Quiz cursorToComment (Cursor cursor) {
        Quiz quiz = new Quiz ();
        quiz.setId(cursor.getLong(0));
        quiz.setQuestion_num(cursor.getString(1));
        quiz.setQuestion_answer(cursor.getString(2));

        return quiz;
    }
}
