package com.example.advancedalarmclock.dashButtons.hrJournal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.gcJournal.journalAddData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class hrJournal extends AppCompatActivity {

    private FloatingActionButton hrJournalDataButton;
    RecyclerView recyclerView;
    hrDbHelper myDb;
    hrCustomAdapter customAdapter;
    ArrayList<String> hr_date, hr_time, hr_measure, hr_notes;
    ImageView rtnButton;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_journal);
        recyclerView = findViewById(R.id.hrRecycler);

        hrJournalDataButton = (FloatingActionButton)  findViewById(R.id.addHrDataJournal);
        hrJournalDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHrDataJournal();
            }
        });

        myDb = new hrDbHelper(hrJournal.this);
        hr_date = new ArrayList<>();
        hr_time = new ArrayList<>();
        hr_measure = new ArrayList<>();
        hr_notes = new ArrayList<>();
        displayData();

        customAdapter = new hrCustomAdapter(hrJournal.this, this, hr_date, hr_time, hr_measure, hr_notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(hrJournal.this));

        rtnButton = findViewById(R.id.return_Hrbutton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchHrView);
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

    public void openHrDataJournal(){
        Intent intent = new Intent(this, hrJournalAddData.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void displayData(){
        Cursor cursor = myDb.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                hr_date.add(cursor.getString(1));
                hr_time.add(cursor.getString(2));
                hr_measure.add(cursor.getString(3));
                hr_notes.add(cursor.getString(4));
            }
        }
    }

}