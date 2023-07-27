package com.example.advancedalarmclock.dashButtons.gcAdvancedGraph;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.gcJournal.DBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gcAdvancedGraph extends AppCompatActivity {
    private ImageView rtnButton;
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gc_advanced_graph);
        rtnButton = findViewById(R.id.return_Graphbutton);
        rtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Request the READ_EXTERNAL_STORAGE permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        }
        else {
            // Permission is already granted, proceed with reading data from the database
            readDataFromDatabase();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == READ_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readDataFromDatabase();
            }
            else {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void readDataFromDatabase() {
        // Retrieve data from the database
        DBHelper dbHelper = new DBHelper(this);
        List<Integer> glucoseList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();

        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                int glucose = cursor.getInt(cursor.getColumnIndex("glucose_measure"));
                String date = cursor.getString(cursor.getColumnIndex("gc_date"));
                glucoseList.add(glucose);
                dateList.add(date);
            }
        }

        // Limit the data to the last 30 entries
        int dataSize = glucoseList.size();
        int limit = Math.min(dataSize, 30);
        List<Integer> last30GlucoseList = glucoseList.subList(dataSize - limit, dataSize);
        List<String> last30DateList = dateList.subList(dataSize - limit, dataSize);

        // Check if last30GlucoseList is empty before finding the min and max values
        if (last30GlucoseList.isEmpty()) {
            // Handle the case when glucoseList is empty or has fewer than 30 elements
            Toast.makeText(this, "Not enough data to display the graph.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate the desired width based on the number of data points
        int graphWidth = limit * 100; // Adjust the width calculation as needed

        // Prepare data for graph
        int minGlucoseValue = Collections.min(last30GlucoseList);
        int maxGlucoseValue = Collections.max(last30GlucoseList);

        // Draw the graph
        GraphView graphView = findViewById(R.id.graph);
        graphView.setGraphWidth(graphWidth);
        graphView.setGlucoseData(last30DateList, last30GlucoseList, minGlucoseValue, maxGlucoseValue, Color.RED);
    }
}