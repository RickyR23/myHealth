package com.example.advancedalarmclock.dashButtons.bpJournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class bpDbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "BloodPressureDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "bp_journal";
    private static final String COLUMN_BPID = "bp_id";
    private static final String COLUMN_BPDATE = "bp_date";
    private static final String COLUMN_BPTIME = "bp_time";
    private static final String COLUMN_BPSys = "bpSys_measure";
    private static final String COLUMN_BPNOTE = "bp_notes";
    private static final String COLUMN_BPDia = "bpDia_measure";
    private static final String COLUMN_BPPulse = "bpPulse_measure";

    public bpDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_BPID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BPDATE + " INTEGER, " +
                COLUMN_BPTIME + " INTEGER, " +
                COLUMN_BPSys + " INTEGER, " +
                COLUMN_BPDia + " INTEGER, " +
                COLUMN_BPPulse + " INTEGER, " +
                COLUMN_BPNOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBc(int date, int time, int bpSysMeasure, int bpDiaMeasure, int bpPulseMeasure, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BPDATE, date);
        cv.put(COLUMN_BPTIME, time);
        cv.put(COLUMN_BPSys, bpSysMeasure);
        cv.put(COLUMN_BPDia, bpDiaMeasure);
        cv.put(COLUMN_BPPulse, bpPulseMeasure);
        cv.put(COLUMN_BPNOTE, notes);
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

    void updateData(String Date, String Time, String BPSys, String BPDia, String BPPulse, String Notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BPDATE, Date);
        cv.put(COLUMN_BPTIME, Time);
        cv.put(COLUMN_BPSys, BPSys);
        cv.put(COLUMN_BPDia, BPDia);
        cv.put(COLUMN_BPPulse, BPPulse);
        cv.put(COLUMN_BPNOTE, Notes);

        long result = db.update(TABLE_NAME, cv, "bp_date=?", new String[] {Date});
        if(result == -1){
            Toast.makeText(context, "Error: Failed to Update.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Updated.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteRow(String Date){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "bp_date=?", new String[]{Date});

        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
