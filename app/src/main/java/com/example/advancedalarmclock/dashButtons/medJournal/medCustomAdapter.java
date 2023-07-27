package com.example.advancedalarmclock.dashButtons.medJournal;

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

public class medCustomAdapter extends RecyclerView.Adapter<medCustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> med_Name, med_Frequency, med_Notes;
    private ArrayList<String> filteredMedName, filteredMedFrequency, filteredMedNotes;
    Activity activity;

    public medCustomAdapter(Activity activity, Context context, ArrayList med_Name, ArrayList med_Frequency , ArrayList med_Notes){
        this.activity = activity;
        this.context = context;
        this.med_Name = med_Name;
        this.med_Frequency = med_Frequency;
        this.med_Notes = med_Notes;
    }

    @NonNull
    @Override
    public medCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_med, parent, false);
        return new medCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull medCustomAdapter.MyViewHolder holder, final int position) {

        holder.medNameText.setText(String.valueOf(med_Name.get(position)));
        holder.medFrequencyText.setText(String.valueOf(med_Frequency.get(position)));
        holder.medNotesText.setText(String.valueOf(med_Notes.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, medUpdateActivity.class);
                intent.putExtra("Name", String.valueOf(med_Name.get(position)));
                intent.putExtra("Frequency", String.valueOf(med_Frequency.get(position)));
                intent.putExtra("Notes", String.valueOf(med_Notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (filteredMedNotes != null) {
            return filteredMedNotes.size();
        } else {
            return med_Notes.size();
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
                    results.count = med_Name.size();
                    results.values = med_Name;
                    filteredMedName = med_Name;
                    filteredMedFrequency = med_Frequency;
                    filteredMedNotes = med_Notes;
                } else {
                    // Filter the data and create new filtered lists
                    ArrayList<String> filteredName = new ArrayList<>();
                    ArrayList<String> filteredFrequency = new ArrayList<>();
                    ArrayList<String> filteredNotes = new ArrayList<>();

                    for (int i = 0; i < med_Name.size(); i++) {
                        String date = med_Name.get(i).toLowerCase();
                        String notes = med_Notes.get(i).toLowerCase();

                        // Check if the search text matches the date or notes
                        if (date.contains(searchText) || notes.contains(searchText)) {
                            filteredName.add(med_Name.get(i));
                            filteredFrequency.add(med_Frequency.get(i));
                            filteredNotes.add(med_Notes.get(i));
                        }
                    }

                    results.count = filteredName.size();
                    results.values = filteredName;
                    filteredMedName = filteredName;
                    filteredMedFrequency = filteredFrequency;
                    filteredMedNotes = filteredNotes;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Update the filtered data and notify the adapter
                filteredMedName = (ArrayList<String>) results.values;
                filteredMedFrequency = filteredMedName != null ? filteredMedFrequency : med_Frequency;
                filteredMedNotes = filteredMedName != null ? filteredMedNotes : med_Notes;
                notifyDataSetChanged();
            }
        };
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView medNameText, medFrequencyText, medNotesText;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            medNameText = itemView.findViewById(R.id.medNameText);
            medFrequencyText = itemView.findViewById(R.id.medFrequencyText);
            medNotesText = itemView.findViewById(R.id.medNotesText);
            mainLayout = itemView.findViewById(R.id.medMainLayout);
        }
    }
}
