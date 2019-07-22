package com.staydev.databasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student_database";
    private final int DATABASE_VERSION = 1;
    private final String TABLE_STUDENT = "students";
    private final String KEY_ID = "id";
    private final String KEY_FIRSTNAME = "name";
    private final String CREATE_TABLE_STUDENTS = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("table", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STUDENT + "'");
        onCreate(db);
    }

    public long addStudentDetail(String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_FIRSTNAME, student);
        long insert =  db.insert(TABLE_STUDENT, null, values);

        return insert;
    }

    public ArrayList<String> getAllStudentsList(){
        ArrayList<String> studentArrayList = new ArrayList<String>();
        String name = "";
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()){
            do {
                name  = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                studentArrayList.add(name);
            }while (c.moveToNext());
            Log.d("array", studentArrayList.toString());
        }
        return studentArrayList;
    }
}