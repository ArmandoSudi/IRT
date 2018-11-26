package com.rainbow.irt.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rainbow.irt.DetailsEquipementActivity;
import com.rainbow.irt.R;
import com.rainbow.irt.entite.Equipement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sugar on 11/26/2018
 */
public class EquipementAdapter extends RecyclerView.Adapter<EquipementAdapter.VH> {

    Activity mActivity;
    List<Equipement> mEquipementList = new ArrayList<>();

    static class VH extends RecyclerView.ViewHolder {
        TextView numeroSerieTV, typeEquipementTV, siteFormationTV;
        public VH(View view) {
            super(view);
                numeroSerieTV = view.findViewById(R.id.numero_serie_tv);
                typeEquipementTV = view.findViewById(R.id.equipement_tv);
                siteFormationTV = view.findViewById(R.id.site_formation_tv);
        }
    }

    public EquipementAdapter(Activity activity, List<Equipement> equipements) {
        this.mActivity = activity;
        this.mEquipementList = equipements;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {

        final Equipement equipement =  mEquipementList.get(position);
        vh.numeroSerieTV.setText(equipement.numeroSerie + " ");
        vh.typeEquipementTV.setText(equipement.typeEquipement + " ");
        vh.siteFormationTV.setText(equipement.codeSiteFormation + " ");

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, DetailsEquipementActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipement_item, parent, false);
        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return mEquipementList.size();
    }
}
