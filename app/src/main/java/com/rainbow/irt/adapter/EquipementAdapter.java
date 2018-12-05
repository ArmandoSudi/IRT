package com.rainbow.irt.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.activity.CheckingActivity;
import com.rainbow.irt.activity.DetailsEquipementActivity;
import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVoteEquipement;
import com.rainbow.irt.entite.Equipement;
import com.rainbow.irt.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sugar on 11/26/2018
 */
public class EquipementAdapter extends RecyclerView.Adapter<EquipementAdapter.VH> implements
        Filterable {

    Activity mActivity;
    List<Equipement> mEquipementList;
    List<Equipement> mEquipementAAffecter = new ArrayList<>();
    List<Equipement> mEquipementFiltered;
    boolean isAffected = false;

    static class VH extends RecyclerView.ViewHolder {
        TextView numeroSerieTV, typeEquipementTV, siteFormationTV;
        LinearLayout statusLL;
//        CheckBox checkbox;
//        Button desaffecterBT;
        public VH(View view) {
            super(view);
                numeroSerieTV = view.findViewById(R.id.numero_serie_tv);
                typeEquipementTV = view.findViewById(R.id.equipement_tv);
                siteFormationTV = view.findViewById(R.id.site_formation_tv);
                statusLL = view.findViewById(R.id.status_ll);
//                checkbox = view.findViewById(R.id.checkBox);
        }
    }

    public EquipementAdapter(Activity activity, List<Equipement> equipements) {
        this.mActivity = activity;
        this.mEquipementList = equipements;
        this.mEquipementFiltered = equipements;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {

        final Equipement equipement =  mEquipementFiltered.get(position);
        vh.numeroSerieTV.setText(equipement.numeroSerie + " ");
        vh.typeEquipementTV.setText(equipement.typeEquipement + " ");
        vh.siteFormationTV.setText(equipement.codeSiteFormation + " ");

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, DetailsEquipementActivity.class);
                intent.putExtra(Constant.KEY_CODE_EQUIPEMENT, equipement.codeEquipement);
                intent.putExtra(Constant.KEY_IS_AFFECTED, isAffected);
                mActivity.startActivity(intent);
            }
        });

        isAffected(equipement.codeEquipement, vh.statusLL);

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
        return mEquipementFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();

                if (query.isEmpty()) {
                    mEquipementFiltered = mEquipementList;
                } else {

                    List<Equipement> filteredList = new ArrayList<>();

                    for (Equipement equipement : mEquipementList) {
                        if (equipement.toString().toLowerCase().contains(query.toLowerCase())) {
                            filteredList.add(equipement);
                        }
                    }

                    mEquipementFiltered = filteredList;
                }

                FilterResults results = new FilterResults();
                results.values = mEquipementFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mEquipementFiltered = (ArrayList<Equipement>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public List<Equipement> getEquipementAAffecter() {
        return mEquipementAAffecter;
    }

    public void addAll(List<Equipement> equipements) {
        mEquipementFiltered.addAll(equipements);
    }

    public void clear() {
        mEquipementFiltered.clear();
    }

    public void isAffected(final String codeEquipement, final LinearLayout linearLayout){
        (new AsyncTask<String, Void, BureauVoteEquipement>(){

            @Override
            protected void onPostExecute(BureauVoteEquipement bureauVoteEquipement) {
                super.onPostExecute(bureauVoteEquipement);

                if (bureauVoteEquipement != null) {
                    // Equipement a deja ete affecte
                    linearLayout.setBackgroundColor(mActivity.getResources().getColor(R.color.colorGreen));
                    isAffected = true;
                } else {
                    linearLayout.setBackgroundColor(mActivity.getResources().getColor(R.color.colorGrey));
                    isAffected = false;
                }
            }

            @Override
            protected BureauVoteEquipement doInBackground(String... strings) {
                return IrtDatabase.getInstance(mActivity).getIBureauVoteEquipementDao().getByCodeEquipement(codeEquipement);
            }
        }).execute(codeEquipement);
    }
}
