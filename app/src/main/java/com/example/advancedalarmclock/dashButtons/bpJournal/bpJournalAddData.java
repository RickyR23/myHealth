package com.example.advancedalarmclock.dashButtons.bpJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class bpJournalAddData extends AppCompatActivity {
    EditText date_input, time_input, bpSysInput, bpDiaInput, bpPulseInput, bpNotesInput;
    Button add_button;
    ImageView rtnButton;

    // NOW THAT WE HAVE TO DO THE ADD INTO BP JOURNAL
    // ADD BPSYS BPDIA AND BPPULSE, ALSO EDIT THE XML FILE TO INCLUDE THOSE EDIT TEXT ALSO WITHIN THE ONCREATE METHOD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_journal_add_data);

        date_input = findViewById(R.id.editBpDate);
        time_input = findViewById(R.id.editBpTime);
        bpSysInput = findViewById(R.id.editSysBp);
        bpDiaInput = findViewById(R.id.editDiaBp);
        bpPulseInput = findViewById(R.id.editPulseBp);
        bpNotesInput = findViewById(R.id.editBpNotes);

        add_button = findViewById(R.id.addBpButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bpDbHelper myDb = new bpDbHelper(bpJournalAddData.this);
                myDb.addBc(Integer.valueOf(date_input.getText().toString().trim()),
                        Integer.valueOf(time_input.getText().toString().trim()),
                        Integer.valueOf(bpSysInput.getText().toString().trim()),
                        Integer.valueOf(bpDiaInput.getText().toString().trim()),
                        Integer.valueOf(bpPulseInput.getText().toString().trim()),
                        bpNotesInput.getText().toString().trim());
                finish();
            }
        });

        rtnButton = findViewById(R.id.rtnBpButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}