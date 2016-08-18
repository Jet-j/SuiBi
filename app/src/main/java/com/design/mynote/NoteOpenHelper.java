package com.design.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 杰‘z on 2016/8/14.
 */
public class NoteOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_NOTELIST = "create table Notelist ("
            + "_id integer primary key autoincrement, "
            + "note_title text, "
            + "note_content text, "
            + "edit_time text)";

    public NoteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTELIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
