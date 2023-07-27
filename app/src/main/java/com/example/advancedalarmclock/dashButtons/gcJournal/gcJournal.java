package com.example.advancedalarmclock.dashButtons.gcJournal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.advancedalarmclock.MainActivity;
import com.example.advancedalarmclock.dashButtons.gcInfo.gcInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.advancedalarmclock.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class gcJournal extends AppCompatActivity {
    private FloatingActionButton journalDataButton;
    RecyclerView recyclerView;
    DBHelper mydb;
    CustomAdapter customAdapter;
    ArrayList<String> gc_date, gc_time, gc_glucose, gc_notes;
    ImageView rtnButton;
    SearchView searchView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gc_journal);
        recyclerView = findViewById(R.id.gcRecycler);




        journalDataButton = (FloatingActionButton) findViewById(R.id.addDataJournal);
        journalDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataJournal();
            }
        });

        mydb = new DBHelper(gcJournal.this);
        gc_date = new ArrayList<>();
        gc_time = new ArrayList<>();
        gc_glucose = new ArrayList<>();
        gc_notes = new ArrayList<>();
        displayData();

        customAdapter = new CustomAdapter(gcJournal.this, this, gc_date, gc_time, gc_glucose, gc_notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(gcJournal.this));

        rtnButton = findViewById(R.id.return_button);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }



    public void openDataJournal(){
        Intent intent = new Intent(this, journalAddData.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void displayData() {
        Cursor cursor = mydb.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String date = cursor.getString(1); // Assuming date is stored at index 1 in the cursor
                SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy"); // Input format of your date data from the database
                SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy"); // Output format with slashes

                try {
                    Date parsedDate = inputFormat.parse(date);
                    String formattedDate = outputFormat.format(parsedDate);
                    gc_date.add(formattedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    gc_date.add(date);
                }

                gc_time.add(cursor.getString(2));
                gc_glucose.add(cursor.getString(3));
                gc_notes.add(cursor.getString(4));
            }
        }
    }



}