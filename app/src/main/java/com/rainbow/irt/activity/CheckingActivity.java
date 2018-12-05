package com.rainbow.irt.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rainbow.irt.R;
import com.rainbow.irt.adapter.EquipementAdapter;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Equipement;
import com.rainbow.irt.entite.Province;
import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.TerritoireVille;

import java.util.ArrayList;
import java.util.List;

public class CheckingActivity extends AppCompatActivity {

    private static final String TAG = "CheckingActivity";

    RecyclerView mEquipementRV;
    EquipementAdapter mEquipementAdapter;
    FloatingActionButton mFab;

    List<Equipement> mEquipementAAffecter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        mEquipementRV = findViewById(R.id.equipement_rv);

        mEquipementAdapter = new EquipementAdapter(this, new ArrayList<Equipement>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mEquipementRV.setLayoutManager(linearLayoutManager);
        mEquipementRV.setHasFixedSize(true);
        mEquipementRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        mEquipementRV.setAdapter(mEquipementAdapter);

        loadEquipement();

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckingActivity.this, AddEquipementActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.checkquing_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));;
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mEquipementAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mEquipementAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_list_tcv) {
            Intent intent =  new Intent(this, UtilisateurListActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_view_affectation) {
            Intent intent =  new Intent(this, AffectationListActivity.class);
            startActivity(intent);
        } else if(id == R.id.action_synchronization) {
            Intent intent =  new Intent(this, SynchronizationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadEquipement(){
        new LoadEquipementData(this).execute();
    }

    public void AffecterEquipement(List<Equipement> equipements) {
        //TODO Affecter la liste des equipements passee en parametre
        for (Equipement equipement : equipements) {
            Log.e(TAG, "AffecterEquipement: " + equipement.numeroSerie );
        }
    }

    class LoadEquipementData extends AsyncTask<Void, Void, Void> {
        Context mContext;
        private static final String TAG = "LoadEquipementData";

        List<Equipement> equipements;

        public LoadEquipementData(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mEquipementAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            equipements = IrtDatabase.getInstance(CheckingActivity.this).getIEquipementDao().getAll();
            mEquipementAdapter.clear();
            mEquipementAdapter.addAll(equipements);

            return null;
        }
    }

}
