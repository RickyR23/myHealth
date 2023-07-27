package com.example.advancedalarmclock.dashButtons.hrJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;

public class hrUpdateActivity extends AppCompatActivity {

    EditText dateInput, timeInput, hrInput, notesInput;
    Button updateButton, deleteButton;
    ImageView rtnButton;
    String hr_date, hr_time, hr_measure, hr_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_update);

        dateInput = findViewById(R.id.updateHrDate);
        timeInput = findViewById(R.id.updateHrTime);
        hrInput = findViewById(R.id.updateHr);
        notesInput = findViewById(R.id.updateHrNotes);

        updateButton = findViewById(R.id.updateHrButton);
        deleteButton = findViewById(R.id.deleteHrButton);
        getAndSetIntentData();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrDbHelper myDb = new hrDbHelper(hrUpdateActivity.this);
                myDb.deleteRow(hr_date);
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrDbHelper myDb = new hrDbHelper(hrUpdateActivity.this);
                hr_date = dateInput.getText().toString().trim();
                hr_time = timeInput.getText().toString().trim();
                hr_measure = hrInput.getText().toString().trim();
                hr_notes = notesInput.getText().toString().trim();

                myDb.updateData(hr_date, hr_time, hr_measure, hr_notes);
                finish();
            }
        });

        rtnButton = (ImageView) findViewById(R.id.returnHrButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("Date") && getIntent().hasExtra("Time") && getIntent().hasExtra("Hr") && getIntent().hasExtra("Notes")){

            hr_date = getIntent().getStringExtra("Date");
            hr_time = getIntent().getStringExtra("Time");
            hr_measure = getIntent().getStringExtra("Hr");
            hr_notes = getIntent().getStringExtra("Notes");


            dateInput.setText(hr_date);
            timeInput.setText(hr_time);
            hrInput.setText(hr_measure);
            notesInput.setText(hr_notes);
        }
        else{
            Toast.makeText(this, "Currently has no data.", Toast.LENGTH_SHORT).show();
        }
    }
}