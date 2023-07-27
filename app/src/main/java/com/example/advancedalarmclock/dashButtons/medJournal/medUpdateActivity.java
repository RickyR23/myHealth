package com.example.advancedalarmclock.dashButtons.medJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;

public class medUpdateActivity extends AppCompatActivity {

    EditText nameInput, frequencyInput, notesInput;
    Button updateBtn, deleteBtn;
    ImageView rtnBtn;
    String med_Name, med_Frequency, med_Notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_update);

        nameInput = findViewById(R.id.updateName);
        frequencyInput = findViewById(R.id.updateFrequency);
        notesInput = findViewById(R.id.updateMedNotes);
        updateBtn = findViewById(R.id.updateMedButton);
        deleteBtn = findViewById(R.id.deleteMedButton);
        getAndSetIntentData();
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medDbHelper myDb = new medDbHelper(medUpdateActivity.this);
                myDb.deleteRow(med_Name);
                finish();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medDbHelper myDb = new medDbHelper(medUpdateActivity.this);

                med_Name = nameInput.getText().toString().trim();
                med_Frequency = frequencyInput.getText().toString().trim();
                med_Notes = notesInput.getText().toString().trim();

                myDb.updateData(med_Name, med_Frequency, med_Notes);
                finish();
            }
        });

        rtnBtn = (ImageView) findViewById(R.id.returnMedButton);
        rtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("Name") && getIntent().hasExtra("Frequency") && getIntent().hasExtra("Notes")){

            med_Name = getIntent().getStringExtra("Name");
            med_Frequency = getIntent().getStringExtra("Frequency");
            med_Notes = getIntent().getStringExtra("Notes");


            nameInput.setText(med_Name);
            frequencyInput.setText(med_Frequency);
            notesInput.setText(med_Notes);
        }
        else{
            Toast.makeText(this, "Currently has no data.", Toast.LENGTH_SHORT).show();
        }
    }
}