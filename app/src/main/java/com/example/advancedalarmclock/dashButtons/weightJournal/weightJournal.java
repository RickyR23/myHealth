package com.example.advancedalarmclock.dashButtons.weightJournal;

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
import com.example.advancedalarmclock.dashButtons.gcJournal.CustomAdapter;
import com.example.advancedalarmclock.dashButtons.gcJournal.gcJournal;
import com.example.advancedalarmclock.dashButtons.gcJournal.journalAddData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class weightJournal extends AppCompatActivity {
    private FloatingActionButton weightDataButton;
    RecyclerView recyclerView;
    weightDbHelper myDb;
    weightCustomAdapter customAdapter;
    ArrayList<String>  w_date, w_time, weight_measure, w_notes;
    ImageView rtnButton;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_journal);

        recyclerView = findViewById(R.id.weightRecycler);

        weightDataButton = (FloatingActionButton) findViewById(R.id.addWeightDataJournal);
        weightDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeightDataJournal();
            }
        });

        myDb = new weightDbHelper(weightJournal.this);
        w_date = new ArrayList<>();
        w_time = new ArrayList<>();
        weight_measure = new ArrayList<>();
        w_notes = new ArrayList<>();
        displayData();

        customAdapter = new weightCustomAdapter(weightJournal.this, this, w_date, w_time, weight_measure, w_notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(weightJournal.this));

        rtnButton = findViewById(R.id.return_buttonWeight);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchWeightView);
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

    public void openWeightDataJournal(){
        Intent intent = new Intent(this, weightJournalAddData.class);
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
                w_date.add(cursor.getString(1));
                w_time.add(cursor.getString(2));
                weight_measure.add(cursor.getString(3));
                w_notes.add(cursor.getString(4));
            }
        }
    }
}