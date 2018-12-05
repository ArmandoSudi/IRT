package com.rainbow.irt.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.activity.FermerIncidentActivity;
import com.rainbow.irt.entite.Incident;
import com.rainbow.irt.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sugar on 11/25/2018
 */
public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.VH> {

    Activity mActivity;
    List<Incident> mIncidentList = new ArrayList<>();

    static class VH extends RecyclerView.ViewHolder {
        TextView dateOuvertureTV, dateFermetureTV, panneTV, statuIncidentTV;
        public VH(View view) {
            super(view);
            dateOuvertureTV = view.findViewById(R.id.date_ouverture_tv);
            dateFermetureTV = view.findViewById(R.id.date_fermeture_tv);
            panneTV = view.findViewById(R.id.panne_tv);
            statuIncidentTV = view.findViewById(R.id.statu_incident);
        }
    }

    public IncidentAdapter(Activity activity, List<Incident> incidents) {
        this.mActivity = activity;
        this.mIncidentList = incidents;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
         //TODO implement the bindViewHolder method after implementing the incident_item
        final Incident incident = mIncidentList.get(position);
        holder.panneTV.setText(incident.codeLexiquePanne);

        if (incident.dateOuverture.equals(incident.dateFermeture) ) {
            holder.statuIncidentTV.setText("ouvert");
            holder.statuIncidentTV.setTextColor(mActivity.getResources().getColor(R.color.colorRed));
            holder.dateFermetureTV.setVisibility(View.INVISIBLE);
        } else {
            holder.statuIncidentTV.setText("ferme");
            holder.statuIncidentTV.setTextColor(mActivity.getResources().getColor(R.color.colorGreen));
            holder.dateFermetureTV.setVisibility(View.VISIBLE);
        }

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        holder.dateOuvertureTV.setText(sdf.format(incident.dateOuverture));
        holder.dateFermetureTV.setText(sdf.format(incident.dateFermeture));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "Aller fermer incident", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mActivity, FermerIncidentActivity.class);
                intent.putExtra(Constant.KEY_CODE_INCIDENT, incident.codeIncident );
                mActivity.startActivity(intent);
            }
        });
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

    public void addAll(List<Incident> incidents) {
        mIncidentList.addAll(incidents);
    }
}
