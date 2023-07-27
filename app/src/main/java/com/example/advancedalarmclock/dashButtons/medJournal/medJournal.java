package com.example.advancedalarmclock.dashButtons.medJournal;

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

public class medJournal extends AppCompatActivity {
    private FloatingActionButton medJournalDataButton;
    RecyclerView recyclerView;
    medDbHelper myDb;
    medCustomAdapter customAdapter;
    ArrayList<String> med_name, med_frequency, med_notes;
    ImageView rtnButton;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_journal);

        recyclerView = findViewById(R.id.medRecycler);

        medJournalDataButton = (FloatingActionButton) findViewById(R.id.addMedDataJournal);
        medJournalDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedDataJournal();
            }
        });

        myDb = new medDbHelper(medJournal.this);
        med_name = new ArrayList<>();
        med_frequency = new ArrayList<>();
        med_notes = new ArrayList<>();
        displayData();

        customAdapter = new medCustomAdapter(medJournal.this, this, med_name, med_frequency, med_notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(medJournal.this));

        rtnButton = findViewById(R.id.return_buttonMed);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchViewMed);
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

    public void openMedDataJournal(){
        Intent intent = new Intent(this, medJournalAddData.class);
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
                med_name.add(cursor.getString(1));
                med_frequency.add(cursor.getString(2));
                med_notes.add(cursor.getString(3));
            }
        }
    }
}