package com.example.advancedalarmclock.dashButtons.gcJournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.advancedalarmclock.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class journalAddData extends AppCompatActivity {

    EditText date_input, time_input, gc_input, notes_input;
    Button add_button;

    ImageView rtnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_add_data);
        date_input = findViewById(R.id.editDate);
        time_input = findViewById(R.id.editTime);
        gc_input = findViewById(R.id.editGc);
        notes_input = findViewById(R.id.editNotes);




        add_button = findViewById(R.id.addButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper mydb = new DBHelper(journalAddData.this);
                mydb.addGc(Integer.valueOf(date_input.getText().toString().trim()),
                        Integer.valueOf(time_input.getText().toString().trim()),
                        Integer.valueOf(gc_input.getText().toString().trim()),
                        notes_input.getText().toString().trim());
                finish();
            }
        });

        rtnButton = findViewById(R.id.rtnButton);

        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}