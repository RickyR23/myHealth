package com.example.advancedalarmclock.dashButtons.medInfo;

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

public class medInfo extends AppCompatActivity {

    private FloatingActionButton infoDataButton;
    RecyclerView recyclerView;
    medInfoDbHelper myDb;
    medInfoCustomAdapter customAdapter;
    ArrayList<String> log_date, log_time, log_name, log_notes;
    ImageView rtnButton;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_info);
        recyclerView = findViewById(R.id.logRecycler);

        infoDataButton = (FloatingActionButton) findViewById(R.id.addLogDataJournal);
        infoDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openLogDataJournal();
            }
        });

        myDb = new medInfoDbHelper(medInfo.this);
        log_date = new ArrayList<>();
        log_time = new ArrayList<>();
        log_name = new ArrayList<>();
        log_notes = new ArrayList<>();
        displayData();

        customAdapter = new medInfoCustomAdapter(medInfo.this, this, log_date, log_time, log_name, log_notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(medInfo.this));

        rtnButton = findViewById(R.id.return_buttonLog);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchViewLog);
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

    public void openLogDataJournal(){
        Intent intent = new Intent(this, medInfoAddData.class);
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
                log_date.add(cursor.getString(1));
                log_time.add(cursor.getString(2));
                log_name.add(cursor.getString(3));
                log_notes.add(cursor.getString(4));
            }
        }
    }
}