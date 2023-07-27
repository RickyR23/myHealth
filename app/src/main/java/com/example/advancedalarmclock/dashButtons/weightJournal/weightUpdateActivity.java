package com.example.advancedalarmclock.dashButtons.weightJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;

public class weightUpdateActivity extends AppCompatActivity {

    EditText dateInput, timeInput, weightInput, notesInput;
    Button updateButton, deleteButton;
    ImageView rtnButton;
    String w_date, w_time, weight_measure, w_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_update);

        dateInput = findViewById(R.id.updateWeightDate);
        timeInput = findViewById(R.id.updateWeightTime);
        weightInput = findViewById(R.id.updateWeight);
        notesInput = findViewById(R.id.updateWeightNotes);
        updateButton = findViewById(R.id.updateWeightButton);
        deleteButton = findViewById(R.id.deleteWeightButton);
        getAndSetIntentData();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightDbHelper myDb = new weightDbHelper(weightUpdateActivity.this);
                myDb.deleteWeightRow(w_date);
                finish();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightDbHelper myDb = new weightDbHelper(weightUpdateActivity.this);
                w_date = dateInput.getText().toString().trim();
                w_time = timeInput.getText().toString().trim();
                weight_measure = weightInput.getText().toString().trim();
                w_notes = notesInput.getText().toString().trim();

                myDb.updateWeightData(w_date, w_time, weight_measure, w_notes);
                finish();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("Date") && getIntent().hasExtra("Time") && getIntent().hasExtra("Weight") && getIntent().hasExtra("Notes")){

            w_date = getIntent().getStringExtra("Date");
            w_time = getIntent().getStringExtra("Time");
            weight_measure = getIntent().getStringExtra("Weight");
            w_notes = getIntent().getStringExtra("Notes");


            dateInput.setText(w_date);
            timeInput.setText(w_time);
            weightInput.setText(weight_measure);
            notesInput.setText(w_notes);
        }
        else{
            Toast.makeText(this, "Currently has no data.", Toast.LENGTH_SHORT).show();
        }
    }
}