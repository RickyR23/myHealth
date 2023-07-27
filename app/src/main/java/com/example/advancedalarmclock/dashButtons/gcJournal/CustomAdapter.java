package com.example.advancedalarmclock.dashButtons.gcJournal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advancedalarmclock.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

   private Context context;
   private ArrayList<String> gc_date, gc_time, gc_glucose, gc_notes;
    private ArrayList<String> filteredGcDate, filteredGcTime, filteredGcGlucose, filteredGcNotes;

   Activity activity;

    public CustomAdapter(Activity activity, Context context, ArrayList gc_date, ArrayList gc_time, ArrayList gc_glucose, ArrayList gc_notes){
        this.activity = activity;
        this.context = context;
        this.gc_date = gc_date;
        this.gc_time = gc_time;
        this.gc_glucose = gc_glucose;
        this.gc_notes = gc_notes;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy"); // Input format of your date data from the database
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy"); // Output format with slashes

        try {
            Date date = inputFormat.parse(gc_date.get(position));
            String formattedDate = outputFormat.format(date);
            holder.gcDateText.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            holder.gcDateText.setText(gc_date.get(position));
        }

        holder.gcTimeText.setText(String.valueOf(gc_time.get(position)));
        holder.gcGlucoseText.setText(String.valueOf(gc_glucose.get(position)));
        holder.gcNotesText.setText(String.valueOf(gc_notes.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updateActivity.class);
                intent.putExtra("Date", String.valueOf(gc_date.get(position)));
                intent.putExtra("Time", String.valueOf(gc_time.get(position)));
                intent.putExtra("Gc", String.valueOf(gc_glucose.get(position)));
                intent.putExtra("Notes", String.valueOf(gc_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (filteredGcDate != null) {
            return filteredGcDate.size();
        } else {
            return gc_date.size();
        }
    }




    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = constraint.toString().toLowerCase();
                FilterResults results = new FilterResults();

                // Filter the data based on the search text
                if (searchText.isEmpty()) {
                    // No filter applied, return the original data
                    results.count = gc_date.size();
                    results.values = gc_date;
                    filteredGcDate = gc_date;
                    filteredGcTime = gc_time;
                    filteredGcGlucose = gc_glucose;
                    filteredGcNotes = gc_notes;
                } else {
                    // Filter the data and create new filtered lists
                    ArrayList<String> filteredDate = new ArrayList<>();
                    ArrayList<String> filteredTime = new ArrayList<>();
                    ArrayList<String> filteredGlucose = new ArrayList<>();
                    ArrayList<String> filteredNotes = new ArrayList<>();

                    for (int i = 0; i < gc_date.size(); i++) {
                        String date = gc_date.get(i).toLowerCase();
                        String notes = gc_notes.get(i).toLowerCase();

                        // Check if the search text matches the date or notes
                        if (date.contains(searchText) || notes.contains(searchText)) {
                            filteredDate.add(gc_date.get(i));
                            filteredTime.add(gc_time.get(i));
                            filteredGlucose.add(gc_glucose.get(i));
                            filteredNotes.add(gc_notes.get(i));
                        }
                    }

                    results.count = filteredDate.size();
                    results.values = filteredDate;
                    filteredGcDate = filteredDate;
                    filteredGcTime = filteredTime;
                    filteredGcGlucose = filteredGlucose;
                    filteredGcNotes = filteredNotes;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Update the filtered data and notify the adapter
                filteredGcDate = (ArrayList<String>) results.values;
                filteredGcTime = filteredGcDate != null ? filteredGcTime : gc_time;
                filteredGcGlucose = filteredGcDate != null ? filteredGcGlucose : gc_glucose;
                filteredGcNotes = filteredGcDate != null ? filteredGcNotes : gc_notes;
                notifyDataSetChanged();
            }
        };
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView gcDateText, gcTimeText, gcGlucoseText, gcNotesText;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gcDateText = itemView.findViewById(R.id.gcDateText);
            gcTimeText = itemView.findViewById(R.id.gcTimeText);
            gcGlucoseText = itemView.findViewById(R.id.gcGlucoseText);
            gcNotesText = itemView.findViewById(R.id.gcNotesText);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
