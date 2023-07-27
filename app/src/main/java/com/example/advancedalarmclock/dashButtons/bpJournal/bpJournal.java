package com.example.advancedalarmclock.dashButtons.bpJournal;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class bpJournal extends AppCompatActivity {
    private FloatingActionButton bpDataButton;
    RecyclerView recyclerView;
    bpDbHelper myDb;
    bpCustomAdapter customAdapter;
    ArrayList<String> bp_date, bp_time, bpSys, bpDia, bpPulse, bp_notes;
    ImageView rtnButton;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_journal);

        recyclerView = findViewById(R.id.bpRecycler);

        bpDataButton = (FloatingActionButton) findViewById(R.id.addBpDataJournal);
        bpDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openBpDataJournal();
            }
        });
        myDb = new bpDbHelper(bpJournal.this);
        bp_date = new ArrayList<>();
        bp_time = new ArrayList<>();
        bpSys = new ArrayList<>();
        bpDia = new ArrayList<>();
        bpPulse = new ArrayList<>();
        bp_notes = new ArrayList<>();
        displayData();

        customAdapter = new bpCustomAdapter(bpJournal.this, this, bp_date, bp_time, bpSys, bpDia, bpPulse, bp_notes);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(bpJournal.this));

        rtnButton = findViewById(R.id.return_buttonBp);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchBpView);
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

    public void openBpDataJournal(){
        Intent intent = new Intent(this, bpJournalAddData.class);
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
                bp_date.add(cursor.getString(1));
                bp_time.add(cursor.getString(2));
                bpSys.add(cursor.getString(3));
                bpDia.add(cursor.getString(4));
                bpPulse.add(cursor.getString(5));
                bp_notes.add(cursor.getString(6));
            }
        }
    }
}