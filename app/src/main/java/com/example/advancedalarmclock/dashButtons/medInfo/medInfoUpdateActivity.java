package com.example.advancedalarmclock.dashButtons.medInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;

public class medInfoUpdateActivity extends AppCompatActivity {

    EditText dateInput, timeInput, nameInput, notesInput;
    Button updateBtn, deleteBtn;
    ImageView returnBtn;
    String log_date, log_time, log_name, log_notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_info_update);

        dateInput = findViewById(R.id.updateLogDate);
        timeInput = findViewById(R.id.updateLogTime);
        nameInput = findViewById(R.id.updateLogName);
        notesInput = findViewById(R.id.updateLogNotes);
        updateBtn = findViewById(R.id.updateLogButton);
        deleteBtn = findViewById(R.id.deleteLogButton);
        getAndSetIntentData();
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medInfoDbHelper myDb = new medInfoDbHelper(medInfoUpdateActivity.this);
                myDb.deleteRow(log_date);
                finish();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medInfoDbHelper myDb = new medInfoDbHelper(medInfoUpdateActivity.this);

                log_date = dateInput.getText().toString().trim();
                log_time = timeInput.getText().toString().trim();
                log_name = nameInput.getText().toString().trim();
                log_notes = notesInput.getText().toString().trim();
                myDb.updateData(log_date, log_time, log_name, log_notes);
                finish();
            }
        });

        returnBtn = findViewById(R.id.returnLogButton);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("Date") && getIntent().hasExtra("Time") && getIntent().hasExtra("Name") && getIntent().hasExtra("Notes")){

            log_date = getIntent().getStringExtra("Date");
            log_time = getIntent().getStringExtra("Time");
            log_name = getIntent().getStringExtra("Name");
            log_notes = getIntent().getStringExtra("Notes");


            dateInput.setText(log_date);
            timeInput.setText(log_time);
            nameInput.setText(log_name);
            notesInput.setText(log_notes);
        }
        else{
            Toast.makeText(this, "Currently has no data.", Toast.LENGTH_SHORT).show();
        }
    }
}