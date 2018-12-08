package com.rainbow.irt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVote;
import com.rainbow.irt.entite.Province;
import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.SiteVote;
import com.rainbow.irt.entite.TerritoireVille;
import com.rainbow.irt.entite.Utilisateur;
import com.rainbow.irt.entite.UtilisateurL1;
import com.rainbow.irt.service.MobileApi;
import com.rainbow.irt.service.MobileApiInterface;
import com.rainbow.irt.utils.Constant;

import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUtilisateurActivity extends AppCompatActivity {

    private static final String TAG = "RegisterUtilisateurActi";

    EditText mNomET, mPrenomET, mPostnomET, mPasswordOneET, mPasswordTwoET, mTelephoneET;
    Spinner mProvinceSP, mTerritoireVilleSP, mSiteFormationSP, mSiteVoteSP, mBureauVoteSP;
    LinearLayout mSiteVoteLL, mBureauVoteLL;
    Button mLoginBT;
    TextView mTitleTV;

    Province mProvince;
    TerritoireVille mTerritoireVille;
    SiteFormation mSiteFormation;
    SiteVote mSiteVote;
    BureauVote mBureauVote;

    MobileApiInterface mobileApiInterface = MobileApi.getService();
    SharedPreferences mSharedPref;
    SharedPreferences.Editor mEditor;

    String mCodeSiteVote, mCodeBureauVote;
    int mCodeAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_utilisateur);

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPref.edit();

        Intent intent = getIntent();
        mCodeAction = intent.getIntExtra(Constant.KEY_AJOUTER_TCV, 0);

        initScreen(mCodeAction);
    }

    public void initScreen(final int codeAction) {

        mNomET = findViewById(R.id.nom_et);
        mPrenomET = findViewById(R.id.prenom_et);
        mPostnomET = findViewById(R.id.postnom_et);
        mTelephoneET = findViewById(R.id.telephone_et);
        mPasswordOneET = findViewById(R.id.password_one_et);
        mPasswordTwoET = findViewById(R.id.password_two_et);

        mProvinceSP = findViewById(R.id.province_sp);
        mTerritoireVilleSP = findViewById(R.id.ville_territoire_sp);
        mSiteFormationSP = findViewById(R.id.site_formation_sp);
        mSiteVoteSP = findViewById(R.id.site_vote_sp);
        mBureauVoteSP = findViewById(R.id.bureau_vote_sp);

        mSiteVoteLL = findViewById(R.id.site_vote_ll);
        mBureauVoteLL = findViewById(R.id.bureau_vote_ll);

        mLoginBT = findViewById(R.id.login_bt);

        mTitleTV = findViewById(R.id.title_tv);

        populateProvince();

        if (codeAction == 0) {
            //Enregistrer un Utilisateur L1
            mSiteVoteSP.setVisibility(View.GONE);
            mBureauVoteSP.setVisibility(View.GONE);
            mBureauVoteLL.setVisibility(View.GONE);
            mSiteVoteLL.setVisibility(View.GONE);
        } else {
            mTitleTV.setText("Enregistrer un technicien TCV");
        }

        mLoginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(mCodeAction);
            }
        });
    }

    public void register(int codeAction){
        String nom = mNomET.getText().toString();
        String prenom = mPrenomET.getText().toString();
        String postnom = mPostnomET.getText().toString();
        String telephone = mTelephoneET.getText().toString();
        String passwordOne = mPasswordOneET.getText().toString();
        String passwordTwo = mPasswordTwoET.getText().toString();

        Utilisateur utilisateur = new Utilisateur("100", nom, postnom, prenom, passwordOne, true, "1",telephone );
        utilisateur.codeProvince = mProvince.codeProvince;
        utilisateur.codeTerritoireVille = mTerritoireVille.codeTerritoireVille;
        utilisateur.codeSiteFormation = mSiteFormation.codeSiteFormation;

        if (!passwordOne.equals(passwordTwo)){
            Toast.makeText(this, "Vos deux mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            return;
        }
        if (codeAction == 0 ){
            // L1 n'a pas de site de vote ni de bureau de vote
            utilisateur.codeSiteVote = "00000";
            utilisateur.codeBureauVote = "000000";
            utilisateur.codeProfil = "2";

        } else {
            // TCV pour le TCV
            utilisateur.codeSiteVote = mCodeSiteVote;
            utilisateur.codeBureauVote = mCodeBureauVote;
            utilisateur.codeProfil = "1";
        }

        postUser(utilisateur);

    }

    public void postUser(final Utilisateur utilisateur){

        mobileApiInterface.postUtilisateur(utilisateur).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: Response is successful");
                    if(response.body() != null) {
                        Log.e(TAG, "onResponse: Response body is not null");
                        Utilisateur utilisateur1 = response.body();

                        saveInDB(utilisateur1);

                        if (mCodeAction == 0 ) {
                            // Creation L1 avec succes
                            saveInPref(utilisateur1);
                            Toast.makeText(RegisterUtilisateurActivity.this, "L1 cree", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterUtilisateurActivity.this, CheckingActivity.class);
                            RegisterUtilisateurActivity.this.startActivity(intent);
                        } else {
                            // Creation TCV avec succes
                            Toast.makeText(RegisterUtilisateurActivity.this, "TCV cree", Toast.LENGTH_SHORT).show();
                            RegisterUtilisateurActivity.this.finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {

            }
        });

    }

    public void saveInDB(final Utilisateur utilisateur) {
        (new AsyncTask<Utilisateur, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e(TAG, "onPostExecute: Utilisateur insere");
            }

            @Override
            protected Void doInBackground(Utilisateur... utilisateurs) {
                IrtDatabase.getInstance(RegisterUtilisateurActivity.this).getIUtilisateurDao().insert(utilisateur);
                return null;
            }
        }).execute(utilisateur);
    }

    public void saveInPref(Utilisateur utilisateur) {
        mEditor.putString(Constant.KEY_USERNAME, utilisateur.nom);
        mEditor.putString(Constant.KEY_PASSWORD, utilisateur.password);
        mEditor.putString(Constant.KEY_USER_CODE_PROVINCE, mProvince.codeProvince);
        mEditor.putString(Constant.KEY_USER_CODE_TERRITOIRE_VILLE, mTerritoireVille.codeTerritoireVille);
        mEditor.putString(Constant.KEY_USER_CODE_SITE_FORMATION, mSiteFormation.codeSiteFormation);
        mEditor.commit();
    }

    public void populateProvince() {
        (new AsyncTask<Void, Void, List<Province>>() {
            @Override
            protected void onPostExecute(List<Province> provinces) {
                super.onPostExecute(provinces);

                if (provinces != null && provinces.size() > 0) {
                    mProvinceSP.setAdapter(new ArrayAdapter<Province>(
                            RegisterUtilisateurActivity.this,
                            android.R.layout.simple_spinner_item,
                            provinces
                    ));

                    mProvinceSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            mProvince = (Province)parent.getItemAtPosition(position);
                            populateTerritoireVille(mProvince);
                            Log.e(TAG, "Province selectionne: " + mProvince.codeProvince +  " " + mProvince);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            protected List<Province> doInBackground(Void... voids) {
                return IrtDatabase.getInstance(RegisterUtilisateurActivity.this)
                        .getIProvinceDao().getAll();
            }
        }).execute();
    }

    public void populateTerritoireVille(final Province province) {
        if (province == null) {
            mTerritoireVilleSP.setAdapter(null);
            return;
        }

        (new AsyncTask<Province, Void, List<TerritoireVille>>() {
            @Override
            protected void onPostExecute(List<TerritoireVille> territoireVilles) {
                super.onPostExecute(territoireVilles);

                if (territoireVilles != null && territoireVilles.size() > 0) {
                    mTerritoireVilleSP.setAdapter(new ArrayAdapter<TerritoireVille>(
                            RegisterUtilisateurActivity.this,
                            android.R.layout.simple_spinner_item,
                            territoireVilles
                    ));

                    mTerritoireVilleSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            mTerritoireVille = (TerritoireVille) parent.getItemAtPosition(position);
                            Log.e(TAG, "Territoire/Ville selectione: " + mTerritoireVille.codeTerritoireVille + " " + mTerritoireVille);
                            populateSiteFormation(mTerritoireVille);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    mTerritoireVilleSP.setAdapter(null);
                    mSiteFormationSP.setAdapter(null);
                    mSiteVoteSP.setAdapter(null);
                    mBureauVoteSP.setAdapter(null);
                }
            }

            @Override
            protected List<TerritoireVille> doInBackground(Province... provinces) {
                List<TerritoireVille> territoireVilles =  IrtDatabase.getInstance(RegisterUtilisateurActivity.this)
                        .getITerritoireVilleDao().getTerritoireVilleByProvince(province.codeProvince);
                return territoireVilles;
            }
        }).execute(province);
    }

    public void populateSiteFormation(final TerritoireVille territoireVille) {
        if (territoireVille == null) {
            mSiteFormationSP.setAdapter(null);
            return;
        }

        (new AsyncTask<TerritoireVille, Void, List<SiteFormation>>() {
            @Override
            protected void onPostExecute(List<SiteFormation> siteFormations) {
                super.onPostExecute(siteFormations);

                if (siteFormations != null && siteFormations.size() > 0) {
                    mSiteFormationSP.setAdapter(new ArrayAdapter<SiteFormation>(
                            RegisterUtilisateurActivity.this,
                            android.R.layout.simple_spinner_item,
                            siteFormations
                    ));

                    mSiteFormationSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            mSiteFormation = (SiteFormation) parent.getItemAtPosition(position);
                            Log.e(TAG, "SiteFormation: " + mSiteFormation.codeSiteFormation + " " + mSiteFormation);
                            populateSiteVote(mSiteFormation);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    mSiteFormationSP.setAdapter(null);
                    mSiteVoteSP.setAdapter(null);
                    mBureauVoteSP.setAdapter(null);
                }
            }

            @Override
            protected List<SiteFormation> doInBackground(TerritoireVille... territoireVilles) {
                return IrtDatabase.getInstance(RegisterUtilisateurActivity.this)
                        .getISiteFormationDao().getSiteFormationByTerritoireVille(territoireVille.codeTerritoireVille);
            }
        }).execute(territoireVille);
    }

    public void populateSiteVote(final SiteFormation siteFormation) {
        if (siteFormation != null) {
            mSiteVoteSP.setAdapter(null);
            return;
        }

        (new AsyncTask<SiteFormation, Void, List<SiteVote>>(){
            @Override
            protected void onPostExecute(List<SiteVote> siteVotes) {
                super.onPostExecute(siteVotes);

                if (siteVotes != null && siteVotes.size() > 0) {
                    mSiteVoteSP.setAdapter(new ArrayAdapter<SiteVote>(
                            RegisterUtilisateurActivity.this,
                            android.R.layout.simple_spinner_item,
                            siteVotes
                    ));

                    mSiteVoteSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            mSiteVote = (SiteVote) parent.getItemAtPosition(position);
                            mCodeSiteVote = mSiteVote.codeSiteVote;
                            Log.e(TAG, "SiteVote selectionne: " + mCodeSiteVote + " " + mSiteVote);
                            populateBureauVote(mSiteVote);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    mSiteVoteSP.setAdapter(null);
                    mBureauVoteSP.setAdapter(null);
                }
            }

            @Override
            protected List<SiteVote> doInBackground(SiteFormation... siteFormations) {
                return IrtDatabase.getInstance(RegisterUtilisateurActivity.this).getISiteVoteDao().getSiteVoteBySiteFormation(siteFormation.codeSiteFormation);
            }
        }).execute(siteFormation);
    }

    public void populateBureauVote(final SiteVote siteVote) {
        if (siteVote != null) {
            mBureauVoteSP.setAdapter(null);
            return;
        }

        (new AsyncTask<SiteVote, Void, List<BureauVote>>(){
            @Override
            protected void onPostExecute(List<BureauVote> bureauVotes) {
                super.onPostExecute(bureauVotes);

                if (bureauVotes != null && bureauVotes.size() > 0){
                    mBureauVoteSP.setAdapter(new ArrayAdapter<BureauVote>(
                            RegisterUtilisateurActivity.this,
                            android.R.layout.simple_spinner_item,
                            bureauVotes
                    ));

                    mBureauVoteSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            mBureauVote = (BureauVote)parent.getItemAtPosition(position);
                            mCodeBureauVote = mBureauVote.codeBureauVote;
                            Log.e(TAG, "BureauVote selectionne: " + mCodeBureauVote + " " + mBureauVote);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    mBureauVoteSP.setAdapter(null);
                }
            }

            @Override
            protected List<BureauVote> doInBackground(SiteVote... siteVotes) {
                return IrtDatabase.getInstance(RegisterUtilisateurActivity.this).getIBureauVoteDao()
                        .getBureauVoteBySiteVote(siteVote.codeSiteVote);
            }
        }).execute(siteVote);
    }

}
