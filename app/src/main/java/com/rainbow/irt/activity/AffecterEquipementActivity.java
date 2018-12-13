package com.rainbow.irt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.rainbow.irt.R;
import com.rainbow.irt.adapter.BureauVoteAdapter;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVote;
import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.SiteVote;
import com.rainbow.irt.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class AffecterEquipementActivity extends AppCompatActivity {

    BureauVoteAdapter mBureauVoteAdapter;
    RecyclerView mBureauVoteRV;
    Spinner mSiteVoteSP;
    String mCodeEquipement;
    SharedPreferences mSharedPref;
    boolean mIsAffected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affecter_equipement);

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String codeSiteFormation = mSharedPref.getString(Constant.KEY_USER_CODE_SITE_FORMATION, null);
        populerSite(codeSiteFormation);

        Intent intent = getIntent();
        mCodeEquipement = intent.getStringExtra(Constant.KEY_CODE_EQUIPEMENT);
        mIsAffected = intent.getBooleanExtra(Constant.KEY_IS_AFFECTED, false);

        initScreen(mCodeEquipement);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.affecter_equipement_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_populer_site) {
//            populateSiteVote();
        } else if (id == R.id.action_afficher_site) {
//            afficherSite();
        }
        return true;
    }

    public void initScreen(String codeEquipement) {
        mBureauVoteAdapter = new BureauVoteAdapter(this, new ArrayList<BureauVote>(), codeEquipement, mIsAffected);
        mBureauVoteRV = findViewById(R.id.bureau_vote_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBureauVoteRV.setLayoutManager(linearLayoutManager);
        mBureauVoteRV.setHasFixedSize(true);
        mBureauVoteRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        mBureauVoteRV.setAdapter(mBureauVoteAdapter);

        mSiteVoteSP = findViewById(R.id.site_formation_sp);
        mSiteVoteSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SiteVote siteVote = (SiteVote) parent.getItemAtPosition(position);
                populateBureauVote(siteVote);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void populerSite(final String codeSiteFormation) {
        (new AsyncTask<String, Void, List<SiteVote>>(){
            @Override
            protected void onPostExecute(List<SiteVote> siteVotes) {
                super.onPostExecute(siteVotes);
                if (siteVotes != null && siteVotes.size() > 0 ) {
                    mSiteVoteSP.setAdapter(new ArrayAdapter<SiteVote>(
                            AffecterEquipementActivity.this,
                            android.R.layout.simple_spinner_item,
                            siteVotes
                    ));
                }
            }

            @Override
            protected List<SiteVote> doInBackground(String... strings) {
                return IrtDatabase.getInstance(AffecterEquipementActivity.this)
                        .getISiteVoteDao().getSiteVoteBySiteFormation(codeSiteFormation);
            }
        }).execute(codeSiteFormation);
    }

    public void populateBureauVote(final SiteVote siteVote) {
        (new AsyncTask<SiteVote, Void, List<BureauVote>>(){
            @Override
            protected void onPostExecute(List<BureauVote> bureauVotes) {
                super.onPostExecute(bureauVotes);

                mBureauVoteAdapter.clearAll();
                mBureauVoteAdapter.addAll(bureauVotes);
                mBureauVoteAdapter.notifyDataSetChanged();
            }

            @Override
            protected List<BureauVote> doInBackground(SiteVote... siteVotes) {
                return IrtDatabase.getInstance(AffecterEquipementActivity.this)
                        .getIBureauVoteDao().getBureauVoteBySiteVote(siteVote.codeSiteVote);
            }
        }).execute(siteVote);
    }

}
