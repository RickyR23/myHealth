package com.example.advancedalarmclock.dashButtons.medInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class medInfoDbHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "medLogDatabase.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "med_log";
    private static final String COLUMN_ID = "log_id";
    private static final String COLUMN_DATE = "log_date";
    private static final String COLUMN_TIME = "log_time";
    private static final String COLUMN_NAME = "log_name";
    private static final String COLUMN_NOTES = "log_notes";


    public medInfoDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " INTEGER, " +
                COLUMN_TIME + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NOTES + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addLog(int date, int time, String medName, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_NAME, medName);
        cv.put(COLUMN_NOTES, notes);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Data couldn't be added.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Data successfully added to journal.", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String Date, String Time, String Name, String Notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, Date);
        cv.put(COLUMN_TIME, Time);
        cv.put(COLUMN_NAME, Name);
        cv.put(COLUMN_NOTES, Notes);

        long result = db.update(TABLE_NAME, cv, "log_date=?", new String[] {Date});
        if(result == -1){
            Toast.makeText(context, "Error: Failed to Update.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Updated.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteRow(String Date){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "log_date=?", new String[]{Date});

        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

}
