package com.example.advancedalarmclock.dashButtons.hrJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class hrJournalAddData extends AppCompatActivity {

    EditText date_input, time_input, hr_input, notes_input;
    Button addBtn;
    ImageView rtnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_journal_add_data);

        date_input = findViewById(R.id.editHrDate);
        time_input = findViewById(R.id.editHrTime);
        hr_input = findViewById(R.id.editHr);
        notes_input = findViewById(R.id.editHrNotes);

        addBtn = findViewById(R.id.addHrButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrDbHelper myDb = new hrDbHelper(hrJournalAddData.this);
                myDb.addHr(Integer.valueOf(date_input.getText().toString().trim()),
                        Integer.valueOf(time_input.getText().toString().trim()),
                        Integer.valueOf(hr_input.getText().toString().trim()),
                        notes_input.getText().toString().trim());
                finish();
            }
        });

        rtnButton = findViewById(R.id.rtnHrButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}