package com.design.mynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 杰‘z on 2016/8/14.
 */
public class NoteDB {

    public static final String DB_NAME = "NOTES";
    public static final int VERSION = 1;
    private static NoteDB noteDB;
    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */
    private NoteDB(Context context) {
        NoteOpenHelper dbHelper = new NoteOpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB的实例。
     */
    public synchronized static NoteDB getInstance(Context context) {
        synchronized (NoteDB.class) {
            if (noteDB == null) {
                noteDB = new NoteDB(context);
            }
            return noteDB;
        }
    }

    /**
     * 将Note实例存储到数据库。
     */
    public void saveNote(Note note) {
        if (note != null) {
            ContentValues values = new ContentValues();
            values.put("note_title", note.getNote());
            values.put("note_content", note.getContent());
            values.put("edit_time", note.getLastTime());
            db.insert("Notelist", null, values);
        }
    }

    public void deleteNote(String id) {
        db.execSQL("delete from Notelist where _id > ?", new String[]{id});
    }

    public void updateNote(Note note) {
        db.execSQL("update Notelist set note_content = ? where note_title = ?",
                            new String[]{note.getContent(), note.getNote()});
        db.execSQL("update Notelist set edit_time = ? where note_title = ?",
                            new String[]{note.getLastTime(), note.getNote()});

    }

    public Note queryNote(Note note) {
        Cursor cursor = db.rawQuery("select * from Notelist where note_title = ? ", new String[]{note.getNote()});//Cursor 游标和 ResultSet 很像
        if (cursor.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            String id = String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
            String title = cursor.getString(cursor.getColumnIndex("note_title"));
            String time = cursor.getString(cursor.getColumnIndex("edit_time"));
            String content = cursor.getString(cursor.getColumnIndex("note_content"));

            return new Note(id, title, time, content);
        }
        return null;
    }
}

