package com.example.advancedalarmclock.dashButtons.hrJournal;

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
import com.example.advancedalarmclock.dashButtons.gcJournal.CustomAdapter;
import com.example.advancedalarmclock.dashButtons.gcJournal.updateActivity;

import java.util.ArrayList;

public class hrCustomAdapter extends RecyclerView.Adapter<hrCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> hr_date, hr_time, hr_measure, hr_notes;
    private ArrayList<String> filteredHrDate, filteredHrTime, filteredHrMeasure, filteredHrNotes;
    Activity activity;

    public hrCustomAdapter(Activity activity, Context context, ArrayList hr_date, ArrayList hr_time, ArrayList hr_measure, ArrayList hr_notes){
        this.activity = activity;
        this.context = context;
        this.hr_date = hr_date;
        this.hr_time = hr_time;
        this.hr_measure = hr_measure;
        this.hr_notes = hr_notes;
    }

    @NonNull
    @Override
    public hrCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_hr, parent, false);
        return new hrCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hrCustomAdapter.MyViewHolder holder, final int position) {

        holder.hrDateText.setText(String.valueOf(hr_date.get(position)));
        holder.hrTimeText.setText(String.valueOf(hr_time.get(position)));
        holder.hrMeasureText.setText(String.valueOf(hr_measure.get(position)));
        holder.hrNotesText.setText(String.valueOf(hr_notes.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, hrUpdateActivity.class);
                intent.putExtra("Date", String.valueOf(hr_date.get(position)));
                intent.putExtra("Time", String.valueOf(hr_time.get(position)));
                intent.putExtra("Hr", String.valueOf(hr_measure.get(position)));
                intent.putExtra("Notes", String.valueOf(hr_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (filteredHrDate != null) {
            return filteredHrDate.size();
        } else {
            return hr_date.size();
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
                    results.count = hr_date.size();
                    results.values = hr_date;
                    filteredHrDate = hr_date;
                    filteredHrTime = hr_time;
                    filteredHrMeasure = hr_measure;
                    filteredHrNotes = hr_notes;
                } else {
                    // Filter the data and create new filtered lists
                    ArrayList<String> filteredDate = new ArrayList<>();
                    ArrayList<String> filteredTime = new ArrayList<>();
                    ArrayList<String> filteredHr = new ArrayList<>();
                    ArrayList<String> filteredNotes = new ArrayList<>();

                    for (int i = 0; i < hr_date.size(); i++) {
                        String date = hr_date.get(i).toLowerCase();
                        String notes = hr_notes.get(i).toLowerCase();

                        // Check if the search text matches the date or notes
                        if (date.contains(searchText) || notes.contains(searchText)) {
                            filteredDate.add(hr_date.get(i));
                            filteredTime.add(hr_time.get(i));
                            filteredHr.add(hr_measure.get(i));
                            filteredNotes.add(hr_notes.get(i));
                        }
                    }

                    results.count = filteredDate.size();
                    results.values = filteredDate;
                    filteredHrDate = filteredDate;
                    filteredHrTime = filteredTime;
                    filteredHrMeasure = filteredHr;
                    filteredHrNotes = filteredNotes;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Update the filtered data and notify the adapter
                filteredHrDate = (ArrayList<String>) results.values;
                filteredHrTime = filteredHrDate != null ? filteredHrTime : hr_time;
                filteredHrMeasure = filteredHrDate != null ? filteredHrMeasure : hr_measure;
                filteredHrNotes = filteredHrDate != null ? filteredHrNotes : hr_notes;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hrDateText, hrTimeText, hrMeasureText, hrNotesText;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hrDateText = itemView.findViewById(R.id.hrDateText);
            hrTimeText = itemView.findViewById(R.id.hrTimeText);
            hrMeasureText = itemView.findViewById(R.id.hrText);
            hrNotesText = itemView.findViewById(R.id.hrNotesText);
            mainLayout = itemView.findViewById(R.id.hrMainLayout);
        }
    }
}
