package com.example.advancedalarmclock.dashButtons.medJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.gcJournal.DBHelper;
import com.example.advancedalarmclock.dashButtons.gcJournal.journalAddData;

public class medJournalAddData extends AppCompatActivity {

    EditText name_input, frequency_input, notes_input;
    Button addBtn;
    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_journal_add_data);

        name_input = findViewById(R.id.editMedName);
        frequency_input = findViewById(R.id.editMedFrequency);
        notes_input = findViewById(R.id.editMedNotes);

        addBtn = findViewById(R.id.addMedButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medDbHelper myDb = new medDbHelper(medJournalAddData.this);
                myDb.addMed(name_input.getText().toString().trim(),
                        frequency_input.getText().toString().trim(),
                        notes_input.getText().toString().trim());
                finish();
            }
        });

        rtnButton = findViewById(R.id.rtnMedButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}