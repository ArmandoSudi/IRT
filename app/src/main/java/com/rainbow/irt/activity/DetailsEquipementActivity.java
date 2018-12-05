package com.rainbow.irt.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.adapter.IncidentAdapter;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVoteEquipement;
import com.rainbow.irt.entite.Equipement;
import com.rainbow.irt.entite.Incident;
import com.rainbow.irt.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.rainbow.irt.database.IrtDatabase.getInstance;

public class DetailsEquipementActivity extends AppCompatActivity {

    private static final String TAG = "DetailsEquipementActivi";

    ImageButton mLogIncidentIBT, mAffectIBT;
    Spinner mSiteFormationSP;
    TextView mNumeroSerieTV, mDescriptionTV, mTypeEquipementTV, mIncidentLabelTV;
    Button mLogIncidentBT, mAffecterBT;
    RecyclerView mIncidentRV;

    IncidentAdapter mIncidentAdapter;

    String mCodeEquipement;
    boolean isAffected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_equipement);

        Intent intent = getIntent();
        mCodeEquipement = intent.getStringExtra(Constant.KEY_CODE_EQUIPEMENT);
        isAffected = intent.getBooleanExtra(Constant.KEY_IS_AFFECTED, false);

        if (mCodeEquipement != null) {
            loadEquipement(mCodeEquipement);
            loadIncident(mCodeEquipement);
        }

        Toast.makeText(this, "code equipement :" + mCodeEquipement, Toast.LENGTH_SHORT).show();


    }

    public void initScreen(Equipement equipement){
        mNumeroSerieTV = findViewById(R.id.numero_serie_tv);
        mDescriptionTV = findViewById(R.id.description_tv);
        mTypeEquipementTV = findViewById(R.id.type_equipement_tv);
        mIncidentLabelTV = findViewById(R.id.incident_label_tv);
        mIncidentRV = findViewById(R.id.incidents_rv);
        mAffecterBT = findViewById(R.id.affect_bt);

        mNumeroSerieTV.setText(equipement.numeroSerie);
        mDescriptionTV.setText(equipement.description);
        mTypeEquipementTV.setText(equipement.typeEquipement);

        LinearLayoutManager ll = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        mIncidentRV.setLayoutManager(ll);
        mIncidentRV.setHasFixedSize(true);
        mIncidentRV.addItemDecoration(new DividerItemDecoration(this, ll.getOrientation()));
        mIncidentRV.setAdapter(mIncidentAdapter);

        isAffected(mCodeEquipement, mAffecterBT);

    }

    public void logIncident(View v) {
        Toast.makeText(this, "Enregistrer incident", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LogIncidentActivity.class);
        intent.putExtra(Constant.KEY_CODE_EQUIPEMENT, mCodeEquipement);
        startActivity(intent);
    }

    public void affectEquipement(View v) {
        Toast.makeText(this, "Affecter equipement", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AffecterEquipementActivity.class);
        intent.putExtra(Constant.KEY_CODE_EQUIPEMENT, mCodeEquipement);
        startActivity(intent);
    }

    public void loadEquipement(final String codeEquipement) {
        (new AsyncTask<String, Void, Equipement>() {
            @Override
            protected void onPostExecute(Equipement equipement) {
                super.onPostExecute(equipement);

                if (equipement != null) {
                    initScreen(equipement);
                }
            }

            @Override
            protected Equipement doInBackground(String... strings) {
                return getInstance(DetailsEquipementActivity.this).getIEquipementDao().get(codeEquipement);
            }
        }).execute(codeEquipement);
    }

    public void loadIncident(final String codeEquipement) {

        mIncidentAdapter = new IncidentAdapter(this, new ArrayList<Incident>());

        (new AsyncTask<String, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                mIncidentAdapter.notifyDataSetChanged();
                if (mIncidentAdapter.getItemCount() == 0 ){
                    mIncidentLabelTV.setText("Pas d'incident pour cet equipement");
                }
            }

            @Override
            protected Void doInBackground(String... strings) {
                List<Incident> incidents = IrtDatabase.getInstance(DetailsEquipementActivity.this).getIIncidentDao().getIncidentByEquipement(codeEquipement);
                Log.e(TAG, "doInBackground: Incidents list size: " + incidents.size() );
                mIncidentAdapter.addAll(incidents);
                return null;
            }
        }).execute(codeEquipement);
    }

    public void isAffected(final String codeEquipement, final Button affecterBT){
        (new AsyncTask<String, Void, BureauVoteEquipement>(){

            @Override
            protected void onPostExecute(BureauVoteEquipement bureauVoteEquipement) {
                super.onPostExecute(bureauVoteEquipement);

                if (bureauVoteEquipement != null) {
                    // Equipement a deja ete affecte
                    affecterBT.setEnabled(false);
                } else {
                    affecterBT.setEnabled(true);
                }
            }

            @Override
            protected BureauVoteEquipement doInBackground(String... strings) {
                return IrtDatabase.getInstance(DetailsEquipementActivity.this).getIBureauVoteEquipementDao().getByCodeEquipement(codeEquipement);
            }
        }).execute(codeEquipement);
    }

}
