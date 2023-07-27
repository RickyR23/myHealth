package com.example.advancedalarmclock.dashButtons.userSettings;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.advancedalarmclock.R;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class userSettings extends AppCompatActivity {

    private EditText nameEditText, dobEditText, heightEditText, emergencyNameEditText, emergencyPhoneEditText, emergencyEmailEditText, emergencyRelationshipEditText;
    private Button saveButton;
    private ImageView rtnButton;
    private settingsDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        dbHelper = new settingsDbHelper(this);

        rtnButton = findViewById(R.id.returnUserSettings);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        nameEditText = findViewById(R.id.nameEditText);
        dobEditText = findViewById(R.id.dobEditText);
        heightEditText = findViewById(R.id.heightEditText);
        emergencyNameEditText = findViewById(R.id.emergencyNameEditText);
        emergencyPhoneEditText = findViewById(R.id.emergencyPhoneEditText);
        emergencyEmailEditText = findViewById(R.id.emergencyEmailEditText);
        emergencyRelationshipEditText = findViewById(R.id.emergencyRelationshipEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserSettings();
            }
        });
    }

    private void saveUserSettings() {
        String name = nameEditText.getText().toString().trim();
        int dob = Integer.parseInt(dobEditText.getText().toString().trim());
        int height = Integer.parseInt(heightEditText.getText().toString().trim());
        String emergencyName = emergencyNameEditText.getText().toString().trim();
        int emergencyPhone = Integer.parseInt(emergencyPhoneEditText.getText().toString().trim());
        String emergencyEmail = emergencyEmailEditText.getText().toString().trim();
        String emergencyRelationship = emergencyRelationshipEditText.getText().toString().trim();

        dbHelper.addUserSettings(name, dob, height, emergencyName, emergencyPhone, emergencyEmail, emergencyRelationship);

        Toast.makeText(this, "User settings saved.", Toast.LENGTH_SHORT).show();

        // Display the saved information in the EditText fields
        nameEditText.setText(name);
        dobEditText.setText(String.valueOf(dob));
        heightEditText.setText(String.valueOf(height));
        emergencyNameEditText.setText(emergencyName);
        emergencyPhoneEditText.setText(String.valueOf(emergencyPhone));
        emergencyEmailEditText.setText(emergencyEmail);
        emergencyRelationshipEditText.setText(emergencyRelationship);
    }
}