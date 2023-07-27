package com.example.advancedalarmclock.dashButtons.weightJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class weightJournalAddData extends AppCompatActivity {
    EditText date_Input, time_input, weight_input, notes_input;
    Button add_button;
    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_journal_add_data);
        date_Input = findViewById(R.id.editWeightDate);
        time_input = findViewById(R.id.editWeightTime);
        weight_input = findViewById(R.id.editWeight);
        notes_input = findViewById(R.id.editWeightNotes);

        add_button = findViewById(R.id.addWeightButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightDbHelper myDb = new weightDbHelper(weightJournalAddData.this);
                myDb.addWeight(Integer.valueOf(date_Input.getText().toString().trim()),
                        Integer.valueOf(time_input.getText().toString().trim()),
                        Integer.valueOf(weight_input.getText().toString().trim()),
                        notes_input.getText().toString().trim());
                finish();
            }
        });

        rtnButton = findViewById(R.id.rtnWeightButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}