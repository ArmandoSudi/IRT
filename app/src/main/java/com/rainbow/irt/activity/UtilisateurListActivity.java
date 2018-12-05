package com.rainbow.irt.activity;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.rainbow.irt.R;
import com.rainbow.irt.adapter.UtilisateurAdapter;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Profil;
import com.rainbow.irt.entite.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurListActivity extends AppCompatActivity {

    RecyclerView mUtilisateurRV;
    UtilisateurAdapter mUtilisateurAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur_list);

        mUtilisateurRV = findViewById(R.id.utilisateur_rv);
        mUtilisateurAdapter = new UtilisateurAdapter(this, null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mUtilisateurRV.setLayoutManager(linearLayoutManager);
        mUtilisateurRV.setHasFixedSize(true);
        mUtilisateurRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        mUtilisateurRV.setAdapter(mUtilisateurAdapter);

        new GetUtilisateurs(this, mUtilisateurAdapter).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.utilisateur_list_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_populer_utilisateurs) {
            new PopulateDBAsyncTask(this).execute();
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Utilisateur> populateUtilisateur() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        utilisateurs.add(new Utilisateur("10001","John Doe", true, "PRO1004", "088888888"));
        utilisateurs.add(new Utilisateur("10002","Alain K.", true, "PRO1001", "088888888"));
        utilisateurs.add(new Utilisateur("1003","David Muhunga", false, "PRO1003", "088888888"));
        utilisateurs.add(new Utilisateur("1004","Armando Sudi", false, "PRO1002", "088888888"));
        utilisateurs.add(new Utilisateur("1005","Julie Kabenga", true, "PRO1004", "088888888"));
        utilisateurs.add(new Utilisateur("1006","Winny B.", false, "PRO1004", "088888888"));
        utilisateurs.add(new Utilisateur("1007","Bienvenu Bagunda", true, "PRO1001", "088888888"));

        return utilisateurs;
    }

    public List<Profil> populateProfil() {
        List<Profil> profils = new ArrayList<>();

        profils.add(new Profil("PRO1001", "L3"));
        profils.add(new Profil("PRO1002", "L2"));
        profils.add(new Profil("PRO1003", "L1"));
        profils.add(new Profil("PRO1004", "TCV"));
        return profils;
    }

    class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {

        Context mContext;
        private static final String TAG = "PopulateDBAsyncTask";

        public PopulateDBAsyncTask(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            IrtDatabase.getInstance(mContext).getIProfileDao().insertAll(populateProfil());
            IrtDatabase.getInstance(mContext).getIUtilisateurDao().insertAll(populateUtilisateur());
            return null;
        }
    }

    class GetUtilisateurs extends AsyncTask<Void, Void, List<Utilisateur>>{
        Context mContext;
        private static final String TAG = "GetUtilisateurs";
        UtilisateurAdapter utilisateurAdapter;

        public GetUtilisateurs(Context mContext, UtilisateurAdapter utilisateurAdapter) {
            this.mContext = mContext;
            this.utilisateurAdapter = utilisateurAdapter;
        }

        @Override
        protected void onPostExecute(List<Utilisateur> utilisateurs) {
            super.onPostExecute(utilisateurs);

            utilisateurAdapter.addAll(utilisateurs);
            utilisateurAdapter.notifyDataSetChanged();
        }

        @Override
        protected List<Utilisateur> doInBackground(Void... voids) {
            return IrtDatabase.getInstance(mContext).getIUtilisateurDao().getAll();
        }
    }
}
