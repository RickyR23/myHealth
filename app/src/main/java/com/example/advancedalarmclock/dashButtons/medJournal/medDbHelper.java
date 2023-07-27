package com.example.advancedalarmclock.dashButtons.medJournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class medDbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "medDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "med_journal";
    private static final String COLUMN_ID = "med_id";
    private static final String COLUMN_NAME = "med_Name";
    private static final String COLUMN_FREQUENCY = "med_Frequency";
    private static final String COLUMN_NOTES = "med_Notes";

    public medDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " +
                COLUMN_FREQUENCY + " TEXT, " +
                COLUMN_NOTES + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addMed(String medName, String medFrequency, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, medName);
        cv.put(COLUMN_FREQUENCY, medFrequency);
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

    void updateData(String name, String frequency, String Notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_FREQUENCY, frequency);
        cv.put(COLUMN_NOTES, Notes);

        long result = db.update(TABLE_NAME, cv, "med_Name=?", new String[] {name});
        if(result == -1){
            Toast.makeText(context, "Error: Failed to Update.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Updated.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteRow(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "med_Name=?", new String[]{name});

        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


}
