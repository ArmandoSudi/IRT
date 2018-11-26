package com.rainbow.irt.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.rainbow.irt.R;
import com.rainbow.irt.entite.Incident;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sugar on 11/25/2018
 */
public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.VH> {

    Activity mActivity;
    List<Incident> mIncidentList = new ArrayList<>();

    static class VH extends RecyclerView.ViewHolder {
        TextView dateOuvertureTV, dateFermetureTV, panneTV;
        public VH(View view) {
            super(view);
            dateOuvertureTV = view.findViewById(R.id.date_ouverture_tv);
            dateFermetureTV = view.findViewById(R.id.date_fermeture_tv);
            panneTV = view.findViewById(R.id.panne_tv);
        }
    }

    public IncidentAdapter(Activity activity, List<Incident> incidents) {
        this.mActivity = activity;
        this.mIncidentList = incidents;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
         //TODO implement the bindViewHolder method after implementing the incident_item
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.incident_item, parent, false);
        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return mIncidentList.size();
    }
}
