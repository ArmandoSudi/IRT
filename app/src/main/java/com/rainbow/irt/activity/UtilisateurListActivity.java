package com.rainbow.irt.activity;


import android.content.Context;
import android.content.Intent;
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
import com.rainbow.irt.entite.Utilisateur;
import com.rainbow.irt.utils.Constant;

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

        new GetUtilisateursTCV(this, mUtilisateurAdapter).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.utilisateur_list_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_ajouter_tcv) {
            Intent intent = new Intent(UtilisateurListActivity.this, RegisterUtilisateurActivity.class);
            intent.putExtra(Constant.KEY_AJOUTER_TCV, Constant.AJOUTER_TCV);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    class GetUtilisateursTCV extends AsyncTask<Void, Void, List<Utilisateur>>{
        Context mContext;
        private static final String TAG = "GetUtilisateursTCV";
        UtilisateurAdapter utilisateurAdapter;

        public GetUtilisateursTCV(Context mContext, UtilisateurAdapter utilisateurAdapter) {
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
            return IrtDatabase.getInstance(mContext).getIUtilisateurDao().getAllByProfileCode(Constant.PROFILE_CODE_TCV);
        }
    }
}
