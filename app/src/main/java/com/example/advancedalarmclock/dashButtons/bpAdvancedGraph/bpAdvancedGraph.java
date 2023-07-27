package com.example.advancedalarmclock.dashButtons.bpAdvancedGraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advancedalarmclock.R;
import com.example.advancedalarmclock.dashButtons.bpJournal.bpDbHelper;
import com.example.advancedalarmclock.dashButtons.gcAdvancedGraph.GraphView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bpAdvancedGraph extends AppCompatActivity {

    private ImageView rtnButton;
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_advanced_graph);
        rtnButton = findViewById(R.id.return_GraphbuttonBp);
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
        } else {
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
        bpDbHelper dbHelper = new bpDbHelper(this);
        List<Integer> bpSysList = new ArrayList<>();
        List<Integer> bpDiaList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();

        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                int bpSys = cursor.getInt(cursor.getColumnIndex("bpSys_measure"));
                int bpDia = cursor.getInt(cursor.getColumnIndex("bpDia_measure"));
                String date = cursor.getString(cursor.getColumnIndex("bp_date"));
                bpSysList.add(bpSys);
                bpDiaList.add(bpDia);
                dateList.add(date);
            }
        }

        int dataSize = bpSysList.size();
        int limit = Math.min(dataSize, 30);

        if (limit == 0) {
            // Handle the case when bpSysList is empty or has fewer than 30 elements
            Toast.makeText(this, "Not enough data to display the graph.", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Integer> last30BPSysList = bpSysList.subList(dataSize - limit, dataSize);
        List<Integer> last30BPDiaList = bpDiaList.subList(dataSize - limit, dataSize);
        List<String> last30DateList = dateList.subList(dataSize - limit, dataSize);

        int graphWidth = limit * 100;

        int minBPSysValue = Collections.min(last30BPSysList);
        int maxBPSysValue = Collections.max(last30BPSysList);
        int minBPDiaValue = Collections.min(last30BPDiaList);
        int maxBPDiaValue = Collections.max(last30BPDiaList);

        bpGraphView graphView = findViewById(R.id.bpGraph);
        graphView.setGraphWidth(graphWidth);
        graphView.setBPData(last30DateList, last30BPSysList, last30BPDiaList,
                minBPSysValue, maxBPSysValue, minBPDiaValue, maxBPDiaValue);
    }
}