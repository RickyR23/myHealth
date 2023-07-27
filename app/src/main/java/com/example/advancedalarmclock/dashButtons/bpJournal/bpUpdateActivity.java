package com.example.advancedalarmclock.dashButtons.bpJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;

public class bpUpdateActivity extends AppCompatActivity {
    EditText dateInput, timeInput, bpSysInput, bpDiaInput, bpPulseInput, bpNotesInput;
    Button updateButton, deleteButton;
    ImageView rtnButton;
    String bp_date, bp_time, bpSys_input, bpDia_input, bpPulse_input, bpNotes_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_update);

        dateInput = findViewById(R.id.updateBpDate);
        timeInput = findViewById(R.id.updateBpTime);
        bpSysInput = findViewById(R.id.updateSysGc);
        bpDiaInput = findViewById(R.id.updateDiaGc);
        bpPulseInput = findViewById(R.id.updatePulseGc);
        bpNotesInput = findViewById(R.id.updateBpNotes);
        updateButton = findViewById(R.id.updateBpButton);
        deleteButton = findViewById(R.id.deleteBpButton);
        getAndSetIntentData();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bpDbHelper myDb = new bpDbHelper(bpUpdateActivity.this);
                myDb.deleteRow(bp_date);
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bpDbHelper myDb = new bpDbHelper(bpUpdateActivity.this);
                bp_date = dateInput.getText().toString().trim();
                bp_time = timeInput.getText().toString().trim();
                bpSys_input = bpSysInput.getText().toString().trim();
                bpDia_input = bpDiaInput.getText().toString().trim();
                bpPulse_input = bpPulseInput.getText().toString().trim();
                bpNotes_input = bpNotesInput.getText().toString().trim();

                myDb.updateData(bp_date, bp_time, bpSys_input, bpDia_input, bpPulse_input, bpNotes_input);
                finish();
            }
        });

        rtnButton = (ImageView) findViewById(R.id.returnBpButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("Date") && getIntent().hasExtra("Time") && getIntent().hasExtra("Bp_Sys") && getIntent().hasExtra("Bp_Dia") && getIntent().hasExtra("Bp_Pulse") && getIntent().hasExtra("Notes")){
            bp_date = getIntent().getStringExtra("Date");
            bp_time = getIntent().getStringExtra("Time");
            bpSys_input = getIntent().getStringExtra("Bp_Sys");
            bpDia_input = getIntent().getStringExtra("Bp_Dia");
            bpPulse_input = getIntent().getStringExtra("Bp_Pulse");
            bpNotes_input = getIntent().getStringExtra("Notes");

            dateInput.setText(bp_date);
            timeInput.setText(bp_time);
            bpSysInput.setText(bpSys_input);
            bpDiaInput.setText(bpDia_input);
            bpPulseInput.setText(bpPulse_input);
            bpNotesInput.setText(bpNotes_input);
        }
        else{
            Toast.makeText(this, "Currently has no data.", Toast.LENGTH_SHORT).show();
        }
    }
}