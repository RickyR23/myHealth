package com.example.advancedalarmclock.dashButtons.gcJournal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;

public class updateActivity extends AppCompatActivity {

    EditText dateInput, timeInput, gcInput, notesInput;
    Button updateButton, deleteButton;
    ImageView rtnButton;
    String gc_date, gc_time, glucose_measure, gc_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dateInput = findViewById(R.id.updateDate);
        timeInput = findViewById(R.id.updateTime);
        gcInput = findViewById(R.id.updateGc);
        notesInput = findViewById(R.id.updateNotes);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        getAndSetIntentData();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DBHelper myDb = new DBHelper(updateActivity.this);
            myDb.deleteRow(gc_date);
            finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper myDB = new DBHelper(updateActivity.this);

                gc_date = dateInput.getText().toString().trim();
                gc_time = timeInput.getText().toString().trim();
                glucose_measure = gcInput.getText().toString().trim();
                gc_notes = notesInput.getText().toString().trim();

                myDB.updateData(gc_date, gc_time, glucose_measure, gc_notes);
                finish();
            }
        });

        rtnButton = (ImageView) findViewById(R.id.returnButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("Date") && getIntent().hasExtra("Time") && getIntent().hasExtra("Gc") && getIntent().hasExtra("Notes")){

            gc_date = getIntent().getStringExtra("Date");
            gc_time = getIntent().getStringExtra("Time");
            glucose_measure = getIntent().getStringExtra("Gc");
            gc_notes = getIntent().getStringExtra("Notes");


            dateInput.setText(gc_date);
            timeInput.setText(gc_time);
            gcInput.setText(glucose_measure);
            notesInput.setText(gc_notes);
        }
        else{
            Toast.makeText(this, "Currently has no data.", Toast.LENGTH_SHORT).show();
        }
    }

//    void confirmDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete" + )
//    }
}