package com.example.advancedalarmclock.dashButtons.medInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

public class medInfoAddData extends AppCompatActivity {

    EditText date_input, time_input, name_input, notes_input;
    Button addBtn;
    ImageView rtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_info_add_data);
        date_input = findViewById(R.id.editLogDate);
        time_input = findViewById(R.id.editLogTime);
        name_input = findViewById(R.id.editLogName);
        notes_input = findViewById(R.id.editLogNotes);

        addBtn = findViewById(R.id.addLogButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medInfoDbHelper myDb = new medInfoDbHelper(medInfoAddData.this);
                myDb.addLog(Integer.valueOf(date_input.getText().toString().trim()),
                        Integer.valueOf(time_input.getText().toString().trim()),
                        name_input.getText().toString().trim(),
                        notes_input.getText().toString().trim());
                finish();
            }
        });

        rtnButton = findViewById(R.id.rtnLogButton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}