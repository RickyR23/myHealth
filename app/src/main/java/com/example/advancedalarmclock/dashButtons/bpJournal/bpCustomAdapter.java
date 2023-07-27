package com.example.advancedalarmclock.dashButtons.bpJournal;

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
import com.example.advancedalarmclock.dashButtons.gcJournal.updateActivity;

import java.util.ArrayList;

public class bpCustomAdapter extends RecyclerView.Adapter<bpCustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<String> bp_date, bp_time, bpSys_bloodpressure, bpDia_bloodpressure, bpPulse_bloodpressure, bp_notes;
    private ArrayList<String> filteredBpDate, filteredBpTime, filteredBpSysBloodpressure, filteredBpDiaBloodpressure, filteredBpPulseBloodpressure, filteredBpNotes;
    Activity activity;

    public bpCustomAdapter(Activity activity, Context context, ArrayList bp_date, ArrayList bp_time, ArrayList bpSys_bloodpressure, ArrayList bpDia_bloodpressure, ArrayList bpPulse_bloodpressure, ArrayList bp_notes){
        this.activity = activity;
        this.context = context;
        this.bp_date = bp_date;
        this.bp_time = bp_time;
        this.bpSys_bloodpressure = bpSys_bloodpressure;
        this.bpDia_bloodpressure = bpDia_bloodpressure;
        this.bpPulse_bloodpressure = bpPulse_bloodpressure;
        this.bp_notes = bp_notes;
    }

    @NonNull
    @Override
    public bpCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_bp, parent, false);
        return new bpCustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bpCustomAdapter.MyViewHolder holder, final int position) {

        holder.bpDateText.setText(String.valueOf(bp_date.get(position)));
        holder.bpTimeText.setText(String.valueOf(bp_time.get(position)));
        holder.bpSysBloodpressureText.setText(String.valueOf(bpSys_bloodpressure.get(position)));
        holder.bpDiaBloodpressureText.setText(String.valueOf(bpDia_bloodpressure.get(position)));
        holder.bpPulseBloodpressureText.setText(String.valueOf(bpPulse_bloodpressure.get(position)));
        holder.bpNotesText.setText(String.valueOf(bp_notes.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, bpUpdateActivity.class);
                intent.putExtra("Date", String.valueOf(bp_date.get(position)));
                intent.putExtra("Time", String.valueOf(bp_time.get(position)));
                intent.putExtra("Bp_Sys", String.valueOf(bpSys_bloodpressure.get(position)));
                intent.putExtra("Bp_Dia", String.valueOf(bpDia_bloodpressure.get(position)));
                intent.putExtra("Bp_Pulse", String.valueOf(bpPulse_bloodpressure.get(position)));
                intent.putExtra("Notes", String.valueOf(bp_notes.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (filteredBpDate != null) {
            return filteredBpDate.size();
        } else {
            return bp_date.size();
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
                    results.count = bp_date.size();
                    results.values = bp_date;
                    filteredBpDate = bp_date;
                    filteredBpTime = bp_time;
                    filteredBpSysBloodpressure = bpSys_bloodpressure;
                    filteredBpDiaBloodpressure = bpDia_bloodpressure;
                    filteredBpPulseBloodpressure = bpPulse_bloodpressure;
                    filteredBpNotes = bp_notes;
                } else {
                    // Filter the data and create new filtered lists
                    ArrayList<String> filteredDate = new ArrayList<>();
                    ArrayList<String> filteredTime = new ArrayList<>();
                    ArrayList<String> filteredBpSys = new ArrayList<>();
                    ArrayList<String> filteredBpDia = new ArrayList<>();
                    ArrayList<String> filteredBpPulse = new ArrayList<>();
                    ArrayList<String> filteredNotes = new ArrayList<>();

                    for (int i = 0; i < bp_date.size(); i++) {
                        String date = bp_date.get(i).toLowerCase();
                        String notes = bp_notes.get(i).toLowerCase();

                        // Check if the search text matches the date or notes
                        if (date.contains(searchText) || notes.contains(searchText)) {
                            filteredDate.add(bp_date.get(i));
                            filteredTime.add(bp_time.get(i));
                            filteredBpSys.add(bpSys_bloodpressure.get(i));
                            filteredBpDia.add(bpDia_bloodpressure.get(i));
                            filteredBpPulse.add(bpPulse_bloodpressure.get(i));
                            filteredNotes.add(bp_notes.get(i));
                        }
                    }

                    results.count = filteredDate.size();
                    results.values = filteredDate;
                    filteredBpDate = filteredDate;
                    filteredBpTime = filteredTime;
                    filteredBpSysBloodpressure = filteredBpSys;
                    filteredBpDiaBloodpressure = filteredBpDia;
                    filteredBpPulseBloodpressure = filteredBpPulse;
                    filteredBpNotes = filteredNotes;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Update the filtered data and notify the adapter
                filteredBpDate = (ArrayList<String>) results.values;
                filteredBpTime = filteredBpDate != null ? filteredBpTime : bp_time;
                filteredBpSysBloodpressure = filteredBpDate != null ? filteredBpSysBloodpressure : bpSys_bloodpressure;
                filteredBpDiaBloodpressure = filteredBpDate != null ? filteredBpDiaBloodpressure : bpDia_bloodpressure;
                filteredBpPulseBloodpressure = filteredBpDate != null ? filteredBpPulseBloodpressure : bpPulse_bloodpressure;
                filteredBpNotes = filteredBpDate != null ? filteredBpNotes : bp_notes;
                notifyDataSetChanged();
            }
        };
    }
// FINISH THE REST FROM CustomAdapter.java




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bpDateText, bpTimeText, bpSysBloodpressureText, bpDiaBloodpressureText, bpPulseBloodpressureText, bpNotesText;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bpDateText = itemView.findViewById(R.id.bpDateText);
            bpTimeText = itemView.findViewById(R.id.bpTimeText);
            bpSysBloodpressureText = itemView.findViewById(R.id.bpSysText);
            bpDiaBloodpressureText = itemView.findViewById(R.id.bpDiaText);
            bpPulseBloodpressureText = itemView.findViewById(R.id.bpPulseText);
            bpNotesText = itemView.findViewById(R.id.bpNotesText);
            mainLayout = itemView.findViewById(R.id.bpMainLayout);
        }
    }
}
