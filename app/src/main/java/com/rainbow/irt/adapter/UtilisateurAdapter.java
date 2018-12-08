package com.rainbow.irt.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Utilisateur;
import com.rainbow.irt.service.MobileApi;
import com.rainbow.irt.service.MobileApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sugar on 11/27/2018
 */
public class UtilisateurAdapter extends RecyclerView.Adapter<UtilisateurAdapter.VH> {

    private static final String TAG = "UtilisateurAdapter";

    Activity mActivity;
    List<Utilisateur> mUtilisateurList;
    MobileApiInterface mobileApiInterface = MobileApi.getService();

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
    public void onBindViewHolder(@NonNull VH vh, final int i) {
        final Utilisateur utilisateur = mUtilisateurList.get(i);

        vh.nomCompletTV.setText(utilisateur.nom + " " + utilisateur.postnom + " " + utilisateur.prenom );
        vh.profilTV.setText(utilisateur.codeProfil);
        vh.telephoneTV.setText(utilisateur.telephone + " ");

        if ( utilisateur.actif ) {
            vh.itemView.setBackgroundColor(Color.parseColor("#DCDCDC"));
            vh.validerBT.setVisibility(View.GONE);
        }

        vh.validerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validerUtilisateur(utilisateur);
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

    public void saveInDB(final Utilisateur utilisateur) {
        (new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e(TAG, "saveInDB: Utilisateur enregistre dans la base des donnees");
                notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                IrtDatabase.getInstance(mActivity).getIUtilisateurDao().insert(utilisateur);
                return null;
            }
        }).execute();
    }

    /**
     * Quand on valide un utilisateur, on le cree en ligne, puis recupere sa cle nouvellement
     * genere par le serveur pour le persister dans notre base des donees en local
     * @param utilisateur
     */
    public void validerUtilisateur(final Utilisateur utilisateur){
        if (utilisateur != null) {
            mobileApiInterface.postUtilisateur(utilisateur).enqueue(new Callback<Utilisateur>() {
                @Override
                public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null) {
                            Log.e(TAG, "validerUtilisateur: codeUtilisateur: "
                                    + utilisateur.codeUtilisateur + " isActif: " + utilisateur.actif);
                            saveInDB(utilisateur);
                        } else {
                            Log.e(TAG, "validerUtilisateur: Response body is null");
                        }
                    } else {
                        Log.e(TAG, "validerUtilisateur: Response body is not successful");
                    }
                }

                @Override
                public void onFailure(Call<Utilisateur> call, Throwable t) {
                    Log.e(TAG, "postUtilisateur() Failed");
                }
            });
        }
    }

}
