package com.rainbow.irt.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Province;
import com.rainbow.irt.entite.Utilisateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sugar on 11/27/2018
 */
public class UtilisateurAdapter extends RecyclerView.Adapter<UtilisateurAdapter.VH> {

    Activity mActivity;
    List<Utilisateur> mUtilisateurList;

    static class VH extends RecyclerView.ViewHolder {
        TextView nomCompletTV, profilTV, telephoneTV;
        Button validerBT;
        public VH(View view) {
            super(view);
            nomCompletTV = view.findViewById(R.id.nom_complet_tv);
            profilTV = view.findViewById(R.id.profil_tv);
            telephoneTV = view.findViewById(R.id.telephone_tv);
            validerBT = view.findViewById(R.id.affecter_desaffecter_bt);
        }

    }

    public UtilisateurAdapter(Activity mActivity, List<Utilisateur> mUtilisateurList) {
        this.mActivity = mActivity;
        this.mUtilisateurList = new ArrayList<>();
    }

    @NonNull
    @Override
    public UtilisateurAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.utilisateur_item, viewGroup, false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        final Utilisateur utilisateur = mUtilisateurList.get(i);

        //TODO Ajouter prenom et post-nom
        vh.nomCompletTV.setText(utilisateur.nom );
        vh.profilTV.setText(utilisateur.codeProfil);
        vh.telephoneTV.setText(utilisateur.telephone + " ");

        if ( utilisateur.actif ) {
            vh.itemView.setBackgroundColor(Color.parseColor("#DCDCDC"));
            vh.validerBT.setText("Invalider");
            vh.validerBT.setBackgroundColor(mActivity.getResources().getColor(R.color.colorGrey));
        }

        vh.validerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO mettre a jour le statu actif de l'utilisateur dans l'array list mUtilisateurList
                if (utilisateur.actif) {

                    utilisateur.actif = false;
//                    mUtilisateurList[1].act =
                    persist(utilisateur);
                } else {
                    utilisateur.actif = true;
                    persist(utilisateur);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUtilisateurList.size();
    }

    public void addAll(List<Utilisateur> utilisateurs) {
        mUtilisateurList.addAll(utilisateurs);
    }

    public void persist(final Utilisateur utilisateur) {
        (new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                Utilisateur utilisateur1 = IrtDatabase.getInstance(mActivity).getIUtilisateurDao().get(utilisateur.codeUtilisateur);
                if (utilisateur1.actif) {
                    utilisateur1.actif = false;
                } else {
                    utilisateur1.actif = true;
                }
                IrtDatabase.getInstance(mActivity).getIUtilisateurDao().update(utilisateur1);
                return null;
            }
        }).execute();
    }

    //TODO FINISH IMPLEMENTING THIS WHEN ACTIVATING A USER
    public void loadData(final Context context) {
        (new AsyncTask<Void, Void, Void>(){
            List<Province> provinces;

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                provinces = IrtDatabase.getInstance(context).getIProvinceDao().getAll();

                return null;
            }
        }).execute();
    }
}
