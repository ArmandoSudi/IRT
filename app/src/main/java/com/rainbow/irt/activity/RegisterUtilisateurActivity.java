package com.rainbow.irt.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Province;
import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.TerritoireVille;
import com.rainbow.irt.entite.Utilisateur;
import com.rainbow.irt.entite.UtilisateurL1;
import com.rainbow.irt.service.MobileApi;
import com.rainbow.irt.service.MobileApiInterface;
import com.rainbow.irt.utils.Constant;

import java.util.List;

public class RegisterUtilisateurActivity extends AppCompatActivity {

    private static final String TAG = "RegisterUtilisateurActi";

    EditText mNomET, mPrenomET, mPostnomET, mPasswordOneET, mPasswordTwoET, mTelephoneET;
    Spinner mProvinceSP, mTerritoireVilleSP, mSiteFormationSP;
    Button mLoginBT;

    Province mProvince;
    TerritoireVille mTerritoireVille;
    SiteFormation mSiteFormation;

    MobileApiInterface mobileApiInterface = MobileApi.getService();
    SharedPreferences mSharedPref;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_utilisateur);

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPref.edit();

        initScreen();
    }

    public void initScreen() {
        mNomET = findViewById(R.id.nom_et);
        mPrenomET = findViewById(R.id.prenom_et);
        mPostnomET = findViewById(R.id.postnom_et);
        mTelephoneET = findViewById(R.id.telephone_et);
        mPasswordOneET = findViewById(R.id.password_one_et);
        mPasswordTwoET = findViewById(R.id.password_two_et);

        mProvinceSP = findViewById(R.id.province_sp);
        mTerritoireVilleSP = findViewById(R.id.ville_territoire_sp);
        mSiteFormationSP = findViewById(R.id.site_formation_sp);

        populateProvince();

        mLoginBT = findViewById(R.id.login_bt);
        mLoginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
//                registerDemo();
            }
        });
    }

    public void registerDemo(final Utilisateur utilisateur) {
        //Store the username and password only if created successfully online
        mEditor.putString(Constant.KEY_USERNAME, utilisateur.nom);
        mEditor.putString(Constant.KEY_PASSWORD, utilisateur.password);
        mEditor.putString(Constant.KEY_USER_CODE_PROVINCE, mProvince.codeProvince);
        mEditor.putString(Constant.KEY_USER_CODE_TERRITOIRE_VILLE, mTerritoireVille.codeTerritoireVille);
        mEditor.putString(Constant.KEY_USER_CODE_SITE_FORMATION, mSiteFormation.codeSiteFormation);
        mEditor.commit();

        Intent intent = new Intent(RegisterUtilisateurActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void register(){
        String nom = mNomET.getText().toString();
        String prenom = mPrenomET.getText().toString();
        String postnom = mPostnomET.getText().toString();
        String telephone = mTelephoneET.getText().toString();
        String passwordOne = mPasswordOneET.getText().toString();
        String passwordTwo = mPasswordTwoET.getText().toString();

        if (!passwordOne.equals(passwordTwo)){
            Toast.makeText(this, "Vos deux mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO Get the code profil from the database.
        Utilisateur utilisateur = new Utilisateur("100", nom, postnom, prenom, passwordOne, true, "2",telephone );
        postUser(utilisateur);

    }

    public void postUser(final Utilisateur utilisateur){

        // On enregistre d'abord le codeProvince, codeTerritoireVille, codeSiteFormation dans sharedPref
        registerDemo(utilisateur);

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
                            populateSiteFormation(mTerritoireVille);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            protected List<TerritoireVille> doInBackground(Province... provinces) {
                List<TerritoireVille> territoireVilles =  IrtDatabase.getInstance(RegisterUtilisateurActivity.this).getITerritoireVilleDao().getTerritoireVilleByProvince(province.codeProvince);
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
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            protected List<SiteFormation> doInBackground(TerritoireVille... territoireVilles) {
                return IrtDatabase.getInstance(RegisterUtilisateurActivity.this)
                        .getISiteFormationDao().getSiteFormationByTerritoireVille(territoireVille.codeTerritoireVille);
            }
        }).execute(territoireVille);
    }

    //TODO Affecter utilisateur a un site de formation. Creer un UtiliateurL1
    public void affecterUtilisateur(Utilisateur utilisateur){
        UtilisateurL1 affecation = new UtilisateurL1();
//        affecation.codeUtilisateur
    }
}
