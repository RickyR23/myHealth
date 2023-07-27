package com.example.advancedalarmclock.dashButtons.userSettings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class settingsDbHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "userSettings.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "user_information";
    public static final String COLUMN_ID = "us_id";
    public static final String COLUMN_NAME = "us_Name";
    public static final String COLUMN_DOB = "us_Dob";
    public static final String COLUMN_HEIGHT = "us_height";
    public static final String COLUMN_EMERGENCYNAME = "en_Name";
    public static final String COLUMN_EMERGENCYPHONE = "en_Phone";
    public static final String COLUMN_EMERGENCYEMAIL = "en_Email";
    public static final String COLUMN_EMERGENCYRELATIONSHIP = "en_Relationship";

    public settingsDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " +
                COLUMN_DOB + " INTEGER, " +
                COLUMN_HEIGHT + " INTEGER, " +
                COLUMN_EMERGENCYNAME + " TEXT, " +
                COLUMN_EMERGENCYPHONE + " INTEGER, " +
                COLUMN_EMERGENCYEMAIL + " TEXT, " +
                COLUMN_EMERGENCYRELATIONSHIP + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addUserSettings(String name, int dob, int height, String emName, int emPhone, String emEmail, String emRelationship){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DOB, dob);
        cv.put(COLUMN_HEIGHT, height);
        cv.put(COLUMN_EMERGENCYNAME, emName);
        cv.put(COLUMN_EMERGENCYPHONE, emPhone);
        cv.put(COLUMN_EMERGENCYEMAIL, emEmail);
        cv.put(COLUMN_EMERGENCYRELATIONSHIP, emRelationship);
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

    void updateData(String name, String dob, String height, String emName, String emPhone, String emEmail, String emRelationship){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DOB, dob);
        cv.put(COLUMN_HEIGHT, height);
        cv.put(COLUMN_EMERGENCYNAME, emName);
        cv.put(COLUMN_EMERGENCYPHONE, emPhone);
        cv.put(COLUMN_EMERGENCYEMAIL, emEmail);
        cv.put(COLUMN_EMERGENCYRELATIONSHIP, emRelationship);

        long result = db.update(TABLE_NAME, cv, "us_Name=?", new String[] {name});
        if(result == -1){
            Toast.makeText(context, "Error: Failed to Update.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Updated.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteRow(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "us_Name=?", new String[]{Name});

        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
