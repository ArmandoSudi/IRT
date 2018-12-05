package com.rainbow.irt.adapter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVote;
import com.rainbow.irt.entite.BureauVoteEquipement;
import com.rainbow.irt.entite.Equipement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sugar on 12/5/2018
 */
public class BureauVoteEquipementAdapter extends RecyclerView.Adapter<BureauVoteEquipementAdapter.VH> {

    Activity mActivity;
    List<BureauVoteEquipement> mAffecationList = new ArrayList<>();

    static class VH extends RecyclerView.ViewHolder {
        TextView equipementNumeroSerieTV, bureauVoteLibelleTV, dateAffectationTV;
        Button desaffecterBT;
        public VH(View view) {
            super(view);
            equipementNumeroSerieTV = view.findViewById(R.id.equipement_numero_serie_tv);
            bureauVoteLibelleTV = view.findViewById(R.id.bureau_vote_tv);
            dateAffectationTV = view.findViewById(R.id.date_affectation_tv);
            desaffecterBT = view.findViewById(R.id.desaffecter_bt);
        }
    }

    public BureauVoteEquipementAdapter(Activity mActivity, List<BureauVoteEquipement> mAffecationList) {
        this.mActivity = mActivity;
        this.mAffecationList = mAffecationList;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final BureauVoteEquipement affecation = mAffecationList.get(position);

        getBureauVote(affecation.codeBureauVote, holder.bureauVoteLibelleTV);
        getEquipement(affecation.codeEquipement, holder.equipementNumeroSerieTV);

        updateLabel(holder.dateAffectationTV, affecation.dateJour);

        holder.desaffecterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desaffecter(affecation);
            }
        });
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.affectation_item, viewGroup, false);
        return new BureauVoteEquipementAdapter.VH(view);
    }

    @Override
    public int getItemCount() {
        return mAffecationList.size();
    }

    public void addAll(List<BureauVoteEquipement> affectations) {
        mAffecationList.addAll(affectations);
    }

    public void desaffecter(BureauVoteEquipement affectation){
        Toast.makeText(mActivity, "code : " + affectation.codeEquipement, Toast.LENGTH_SHORT).show();

        (new AsyncTask<BureauVoteEquipement, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

            }

            @Override
            protected Void doInBackground(BureauVoteEquipement... bureauVoteEquipements) {
                IrtDatabase.getInstance(mActivity).getIBureauVoteEquipementDao().delete(bureauVoteEquipements);
                return null;
            }
        }).execute(affectation);
    }

    public void updateLabel(TextView view, Date date) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        view.setText(sdf.format(date));
    }

    public void getEquipement(final String codeEquipement, final TextView equipementTV) {
        (new AsyncTask<String, Void, Equipement>(){
            @Override
            protected void onPostExecute(Equipement equipement) {
                super.onPostExecute(equipement);

                equipementTV.setText(equipement.numeroSerie);
            }

            @Override
            protected Equipement doInBackground(String... strings) {
                return IrtDatabase.getInstance(mActivity).getIEquipementDao().get(codeEquipement);
            }
        }).execute(codeEquipement);
    }

    public void getBureauVote(final String codeBureauVote, final TextView bureauVoteTV){
        (new AsyncTask<String, Void, BureauVote>() {
            @Override
            protected void onPostExecute(BureauVote bureauVote) {
                super.onPostExecute(bureauVote);

                bureauVoteTV.setText(bureauVote.libelle);
            }

            @Override
            protected BureauVote doInBackground(String... strings) {
                return IrtDatabase.getInstance(mActivity).getIBureauVoteDao().getBureauVoteByCode(codeBureauVote);
            }
        }).execute(codeBureauVote);
    }
}
