package com.example.advancedalarmclock.dashButtons.weightJournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class weightDbHelper  extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "WeightDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "weight_journal";
    private static final String COLUMN_ID = "w_id";
    private static final String COLUMN_DATE = "w_date";
    private static final String COLUMN_TIME = "w_time";
    private static final String COLUMN_WEIGHT = "w_weight";
    private static final String COLUMN_NOTE = "w_notes";

    public weightDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " INTEGER, " +
                COLUMN_TIME + " INTEGER, " +
                COLUMN_WEIGHT + " INTEGER, " +
                COLUMN_NOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addWeight(int date, int time, int gcMeasure, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_WEIGHT, gcMeasure);
        cv.put(COLUMN_NOTE, notes);
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

    void updateWeightData(String Date, String Time, String Weight, String Notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, Date);
        cv.put(COLUMN_TIME, Time);
        cv.put(COLUMN_WEIGHT, Weight);
        cv.put(COLUMN_NOTE, Notes);

        long result = db.update(TABLE_NAME, cv, "w_date=?", new String[] {Date});
        if(result == -1){
            Toast.makeText(context, "Error: Failed to Update.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Updated.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteWeightRow(String Date){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "w_date=?", new String[]{Date});

        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
