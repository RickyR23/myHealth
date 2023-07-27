package com.example.advancedalarmclock.dashButtons.weightAdvancedGraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.gcAdvancedGraph.GraphView;
import com.example.advancedalarmclock.dashButtons.gcJournal.DBHelper;
import com.example.advancedalarmclock.dashButtons.weightJournal.weightDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class weightAdvancedGraph extends AppCompatActivity {

    private ImageView rtnButton;
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_advanced_graph);
        rtnButton = findViewById(R.id.return_GraphbuttonWeight);
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
        weightDbHelper dbHelper = new weightDbHelper(this);
        List<Integer> weightList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();

        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                int weight = cursor.getInt(cursor.getColumnIndex("w_weight"));
                String date = cursor.getString(cursor.getColumnIndex("w_date"));
                weightList.add(weight);
                dateList.add(date);
            }
        }

        // Limit the data to the last 30 entries
        int dataSize = weightList.size();
        int limit = Math.min(dataSize, 30);
        List<Integer> last30WeightList = weightList.subList(dataSize - limit, dataSize);
        List<String> last30DateList = dateList.subList(dataSize - limit, dataSize);

        // Calculate the desired width based on the number of data points
        int graphWidth = limit * 100; // Adjust the width calculation as needed

        // Prepare data for graph
        int minWeightValue = Collections.min(last30WeightList);
        int maxWeightValue = Collections.max(last30WeightList);

        // Draw the graph
        weightGraphView graphView = findViewById(R.id.weightGraph);
        graphView.setGraphWidth(graphWidth);
        graphView.setWeightData(last30DateList, last30WeightList, minWeightValue, maxWeightValue, Color.RED);
    }
}