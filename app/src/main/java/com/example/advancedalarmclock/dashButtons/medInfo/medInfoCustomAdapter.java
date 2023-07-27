package com.example.advancedalarmclock.dashButtons.medInfo;

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


import java.util.ArrayList;

public class medInfoCustomAdapter extends RecyclerView.Adapter<medInfoCustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> log_date, log_time, log_name, log_notes;
    private ArrayList<String> filteredLogDate, filteredLogTime, filteredLogName, filteredLogNotes;
    Activity activity;

    public medInfoCustomAdapter(Activity activity, Context context, ArrayList log_date, ArrayList log_time, ArrayList log_name, ArrayList log_notes){
        this.activity = activity;
        this.context = context;
        this.log_date = log_date;
        this.log_time = log_time;
        this.log_name = log_name;
        this.log_notes = log_notes;
    }

    @NonNull
    @Override
    public medInfoCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_med_info, parent, false);
        return new medInfoCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull medInfoCustomAdapter.MyViewHolder holder, final int position) {

        holder.logDateText.setText(String.valueOf(log_date.get(position)));
        holder.logTimeText.setText(String.valueOf(log_time.get(position)));
        holder.logNameText.setText(String.valueOf(log_name.get(position)));
        holder.logNotesText.setText(String.valueOf(log_notes.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, medInfoUpdateActivity.class);
                intent.putExtra("Date", String.valueOf(log_date.get(position)));
                intent.putExtra("Time", String.valueOf(log_time.get(position)));
                intent.putExtra("Name", String.valueOf(log_name.get(position)));
                intent.putExtra("Notes", String.valueOf(log_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
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
                    results.count = log_date.size();
                    results.values = log_date;
                    filteredLogDate = log_date;
                    filteredLogTime = log_time;
                    filteredLogName = log_name;
                    filteredLogNotes = log_notes;
                } else {
                    // Filter the data and create new filtered lists
                    ArrayList<String> filteredDate = new ArrayList<>();
                    ArrayList<String> filteredTime = new ArrayList<>();
                    ArrayList<String> filteredName = new ArrayList<>();
                    ArrayList<String> filteredNotes = new ArrayList<>();

                    for (int i = 0; i < log_date.size(); i++) {
                        String date = log_date.get(i).toLowerCase();
                        String notes = log_notes.get(i).toLowerCase();

                        // Check if the search text matches the date or notes
                        if (date.contains(searchText) || notes.contains(searchText)) {
                            filteredDate.add(log_date.get(i));
                            filteredTime.add(log_time.get(i));
                            filteredName.add(log_name.get(i));
                            filteredNotes.add(log_notes.get(i));
                        }
                    }

                    results.count = filteredDate.size();
                    results.values = filteredDate;
                    filteredLogDate = filteredDate;
                    filteredLogTime = filteredTime;
                    filteredLogName = filteredName;
                    filteredLogNotes = filteredNotes;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Update the filtered data and notify the adapter
                filteredLogDate = (ArrayList<String>) results.values;
                filteredLogTime = filteredLogDate != null ? filteredLogTime : log_time;
                filteredLogName = filteredLogDate != null ? filteredLogName : log_name;
                filteredLogNotes = filteredLogDate != null ? filteredLogNotes : log_notes;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        if (filteredLogDate != null) {
            return filteredLogDate.size();
        } else {
            return log_date.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView logDateText, logTimeText, logNameText, logNotesText;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            logDateText = itemView.findViewById(R.id.logDateText);
            logTimeText = itemView.findViewById(R.id.logTimeText);
            logNameText = itemView.findViewById(R.id.logMedText);
            logNotesText = itemView.findViewById(R.id.logNotesText);
            mainLayout = itemView.findViewById(R.id.mainLogLayout);
        }
    }
}
