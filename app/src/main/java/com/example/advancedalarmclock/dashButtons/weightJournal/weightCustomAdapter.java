package com.example.advancedalarmclock.dashButtons.weightJournal;

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

public class weightCustomAdapter extends RecyclerView.Adapter<weightCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> w_date, w_time, w_weight, w_notes;
    private ArrayList<String> filteredWDate, filteredWTime, filteredWWeight, filteredWNotes;
    Activity activity;

    public weightCustomAdapter(Activity activity, Context context, ArrayList w_date, ArrayList w_time, ArrayList w_weight, ArrayList w_notes){
        this.activity = activity;
        this.context = context;
        this.w_date = w_date;
        this.w_time = w_time;
        this.w_weight = w_weight;
        this.w_notes = w_notes;
    }

    @NonNull
    @Override
    public weightCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_weight, parent, false);
        return new weightCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull weightCustomAdapter.MyViewHolder holder, final int position) {

        holder.weightDateText.setText(String.valueOf(w_date.get(position)));
        holder.weightTimeText.setText(String.valueOf(w_time.get(position)));
        holder.weightGlucoseText.setText(String.valueOf(w_weight.get(position)));
        holder.weightNotesText.setText(String.valueOf(w_notes.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, weightUpdateActivity.class);
                intent.putExtra("Date", String.valueOf(w_date.get(position)));
                intent.putExtra("Time", String.valueOf(w_time.get(position)));
                intent.putExtra("Weight", String.valueOf(w_weight.get(position)));
                intent.putExtra("Notes", String.valueOf(w_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (filteredWDate != null) {
            return filteredWDate.size();
        } else {
            return w_date.size();
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
                    results.count = w_date.size();
                    results.values = w_date;
                    filteredWDate = w_date;
                    filteredWTime = w_time;
                    filteredWWeight = w_weight;
                    filteredWNotes = w_notes;
                } else {
                    // Filter the data and create new filtered lists
                    ArrayList<String> filteredDate = new ArrayList<>();
                    ArrayList<String> filteredTime = new ArrayList<>();
                    ArrayList<String> filteredWeight = new ArrayList<>();
                    ArrayList<String> filteredNotes = new ArrayList<>();

                    for (int i = 0; i < w_date.size(); i++) {
                        String date = w_date.get(i).toLowerCase();
                        String notes = w_notes.get(i).toLowerCase();

                        // Check if the search text matches the date or notes
                        if (date.contains(searchText) || notes.contains(searchText)) {
                            filteredDate.add(w_date.get(i));
                            filteredTime.add(w_time.get(i));
                            filteredWeight.add(w_weight.get(i));
                            filteredNotes.add(w_notes.get(i));
                        }
                    }

                    results.count = filteredDate.size();
                    results.values = filteredDate;
                    filteredWDate = filteredDate;
                    filteredWTime = filteredTime;
                    filteredWWeight = filteredWeight;
                    filteredWNotes = filteredNotes;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Update the filtered data and notify the adapter
                filteredWDate = (ArrayList<String>) results.values;
                filteredWTime = filteredWDate != null ? filteredWTime : w_time;
                filteredWWeight = filteredWDate != null ? filteredWWeight : w_weight;
                filteredWNotes = filteredWDate != null ? filteredWNotes : w_notes;
                notifyDataSetChanged();
            }
        };
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView weightDateText, weightTimeText, weightGlucoseText, weightNotesText;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weightDateText = itemView.findViewById(R.id.weightDateText);
            weightTimeText = itemView.findViewById(R.id.weightTimeText);
            weightGlucoseText = itemView.findViewById(R.id.weightGlucoseText);
            weightNotesText = itemView.findViewById(R.id.weightNotesText);
            mainLayout = itemView.findViewById(R.id.weightMainLayout);
        }
    }

}
