package com.rainbow.irt.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVote;
import com.rainbow.irt.entite.Equipement;
import com.rainbow.irt.entite.LexiquePanne;
import com.rainbow.irt.entite.Profil;
import com.rainbow.irt.entite.Province;
import com.rainbow.irt.entite.SiteFormation;
import com.rainbow.irt.entite.SiteVote;
import com.rainbow.irt.entite.TerritoireVille;
import com.rainbow.irt.service.MobileApi;
import com.rainbow.irt.service.MobileApiInterface;
import com.rainbow.irt.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    EditText mUserNameET, mPasswordET;
    Button mLoginBT;

    MobileApiInterface mobileApiInterface = MobileApi.getService();

    List<Province> mProvinces = new ArrayList<>();
    List<TerritoireVille> mTerritoireVilles = new ArrayList<>();
    List<SiteFormation> mSiteFormations = new ArrayList<>();
    List<SiteVote> mSiteVotes = new ArrayList<>();
    List<BureauVote> mBureauVotes = new ArrayList<>();
    List<Equipement> mEquipements= new ArrayList<>();
    List<LexiquePanne> mLexiquePannes = new ArrayList<>();
    List<Profil> mProfils = new ArrayList<>();

    SharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        initScreen();
    }

    public void initScreen() {
        mUserNameET = findViewById(R.id.username_et);
        mPasswordET = findViewById(R.id.password_et);
        mLoginBT = findViewById(R.id.login_bt);

        mLoginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
//                loginStub();
            }
        });
    }

    public void testApi() {
        Log.e(TAG, "Starting testing testApi");
        getLexiquePanne();
    }

    public void loginStub(){
        Intent intent = new Intent(HomeActivity.this, RegisterUtilisateurActivity.class);
        startActivity(intent);
    }

    public void login(){
        String username = mUserNameET.getText().toString();
        String password = mPasswordET.getText().toString();

        if (username.equals(Constant.DEFAULT_USERNAME) && password.equals(Constant.DEFAULT_PASSWORD)) {
            getDefaultData();
        } else {
            if (isAuthenticated(username, password)) {

                //TODO  Remove when checking if is active method is implemented properly
                Intent intent = new Intent(HomeActivity.this, CheckingActivity.class);
                startActivity(intent);

                if (isActive()) {
                    intent = new Intent(HomeActivity.this, CheckingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Utilisateur n'est pas encore actif", Toast.LENGTH_SHORT).show();
                    //TODO Implement method for checking if the user is active online and storing his status locally
                }
            } else {
                Toast.makeText(this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getDefaultData(){
        getProvince();
        getTerritoireVille();
        getSiteFormation();
        getSiteVote();
        getBureauVote();
        getEquipement();
        getLexiquePanne();
        getProfil();

        Intent intent = new Intent(this, RegisterUtilisateurActivity.class);
        startActivity(intent);
    }

    public void canInsert(){
        if (mProvinces.size() > 0 && mTerritoireVilles.size() > 0 && mSiteFormations.size() > 0 &&
                mBureauVotes.size() > 0 && mEquipements.size() > 0 && mLexiquePannes.size() > 0) {

            Log.e(TAG, "canInsert:  provinces : " + mProvinces.size() );
            Log.e(TAG, "canInsert:  territoires : " + mTerritoireVilles.size() );
            Log.e(TAG, "canInsert:  site formations : " + mSiteFormations.size() );
            Log.e(TAG, "canInsert:  bureau vote : " + mBureauVotes.size() );
            Log.e(TAG, "canInsert:  equipements : " + mEquipements.size() );
            Log.e(TAG, "canInsert:  lexique : " + mProfils.size() );

          new InitializeDB(this).execute();
        }
    }

    class InitializeDB extends AsyncTask<Void, Void, Void> {

        Context mContext;

        public InitializeDB(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(mContext, "Base des donnees initialisees", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            IrtDatabase.getInstance(mContext).getIProvinceDao().insertAll(mProvinces);
            IrtDatabase.getInstance(mContext).getITerritoireVilleDao().insertAll(mTerritoireVilles);
            IrtDatabase.getInstance(mContext).getISiteFormationDao().insertAll(mSiteFormations);
            IrtDatabase.getInstance(mContext).getISiteVoteDao().insert(mSiteVotes);
            IrtDatabase.getInstance(mContext).getIBureauVoteDao().insertAll(mBureauVotes);
            IrtDatabase.getInstance(mContext).getIEquipementDao().insertAll(mEquipements);
            IrtDatabase.getInstance(mContext).getILexiquePanneDao().insertAll(mLexiquePannes);
            IrtDatabase.getInstance(mContext).getIProfileDao().insertAll(mProfils);
            return null;
        }

    }

    public void getProvince() {
        mobileApiInterface.getProvinceList().enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        List<Province> provinces = response.body();
                        mProvinces.addAll(provinces);
                        canInsert();
                        Log.e(TAG, "onResponse: Provinces List Size :" + mProvinces.size());
                    } else {
                        Log.d(TAG, "onResponse: Provinces Response Body is NULL");
                    }
                } else {
                    Log.d(TAG, "onResponse: Province Response Body not Successful");
                }
            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                Log.d(TAG, "onFailure: getProvince Failed");
            }
        });
    }

    public void getTerritoireVille() {
        mobileApiInterface.getTerritoireVilleList().enqueue(new Callback<List<TerritoireVille>>() {
            @Override
            public void onResponse(Call<List<TerritoireVille>> call, Response<List<TerritoireVille>> response) {
                if (response.isSuccessful()){
                    if(response.body() != null) {
                        List<TerritoireVille> territoireVilles = response.body();
                        mTerritoireVilles.addAll(territoireVilles);
                        canInsert();
                        Log.e(TAG, "onResponse: TerritoireVille List size :" + mTerritoireVilles.size());
                    } else {
                        Log.d(TAG, "onResponse: TerritoireVille Body is NULL");
                    }
                } else {
                    Log.d(TAG, "onResponse: getTerritoireVile Response Body not Successful");
                }
            }

            @Override
            public void onFailure(Call<List<TerritoireVille>> call, Throwable t) {
                Log.d(TAG, "onFailure: getTerritoire Failed");
            }
        });
    }

    public void getSiteFormation() {
        mobileApiInterface.getSiteFormationList().enqueue(new Callback<List<SiteFormation>>() {
            @Override
            public void onResponse(Call<List<SiteFormation>> call, Response<List<SiteFormation>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        List<SiteFormation> siteFormations = response.body();
                        mSiteFormations.addAll(siteFormations);
                        canInsert();
                        Log.e(TAG, "onResponse: SiteFormation List Size :" + mSiteFormations.size());
                    } else {
                        Log.d(TAG, "onResponse: SiteFormation Body is NULL");
                    }
                } else {
                    Log.d(TAG, "onResponse: getSiteFormation Response Body not Successful");
                }
            }

            @Override
            public void onFailure(Call<List<SiteFormation>> call, Throwable t) {
                Log.e(TAG, "onFailure: getSiteFormation Failed");
            }
        });
    }

    public void getSiteVote() {
        mobileApiInterface.getSiteVoteList().enqueue(new Callback<List<SiteVote>>() {
            @Override
            public void onResponse(Call<List<SiteVote>> call, Response<List<SiteVote>> response) {
                if (response.isSuccessful()) {
                    if(response.body() != null) {
                        List<SiteVote> siteVotes = response.body();
                        mSiteVotes.addAll(siteVotes);
                        canInsert();
                        Log.e(TAG, "onResponse: SiteVote liste : " + mSiteVotes.size() );
                    } else {
                        Log.d(TAG, "onResponse: SiteVote body is NULL");
                    }
                } else {
                    Log.d(TAG, "onResponse: getSiteVote response body is not successful");
                }
            }

            @Override
            public void onFailure(Call<List<SiteVote>> call, Throwable t) {
                Log.e(TAG, "onFailure: getSiteVote FAILED");
            }
        });
    }

    public void getBureauVote() {
        mobileApiInterface.getBureauVoteList().enqueue(new Callback<List<BureauVote>>() {
            @Override
            public void onResponse(Call<List<BureauVote>> call, Response<List<BureauVote>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        List<BureauVote> bureauVotes = response.body();
                        mBureauVotes.addAll(bureauVotes);
                        canInsert();
                        Log.e(TAG, "onResponse: BureauVote Liste :" + mBureauVotes.size());
                    } else {
                        Log.d(TAG, "onResponse: BureauVote body is NULL");
                    }
                } else {
                    Log.d(TAG, "onResponse: getBureauVote Response Body not successful");
                }
            }

            @Override
            public void onFailure(Call<List<BureauVote>> call, Throwable t) {
                Log.e(TAG, "onFailure: getBureauVote FAILED");
            }
        });
    }

    public void getEquipement() {
        mobileApiInterface.getEquipementList().enqueue(new Callback<List<Equipement>>() {
            @Override
            public void onResponse(Call<List<Equipement>> call, Response<List<Equipement>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Equipement> equipements = response.body();
                        mEquipements.addAll(equipements);
                        canInsert();
                        Log.e(TAG, "onResponse: Equipement List size :" + mEquipements.size());
                    } else {
                        Log.d(TAG, "onResponse: getEquipement Response Body is NULL");
                    }
                } else {
                    Log.d(TAG, "onResponse: getEquipement Response body not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Equipement>> call, Throwable t) {
                Log.e(TAG, "onFailure: getEquipement FAILED");
            }
        });
    }

    public void getLexiquePanne() {
        mobileApiInterface.getLexiquePanneList().enqueue(new Callback<List<LexiquePanne>>() {
            @Override
            public void onResponse(Call<List<LexiquePanne>> call, Response<List<LexiquePanne>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        List<LexiquePanne> lexiquePannes = response.body();
                        mLexiquePannes.addAll(lexiquePannes);
                        canInsert();
                        Log.e(TAG, "onResponse: Lexique Panne List size :" + mLexiquePannes.size());
                    } else {
                        Log.d(TAG, "onResponse: getLexiquePanne Response Body is NULL");
                    }
                } else {
                    Log.d(TAG, "onResponse: getLexiquePanne Response Body is not successful");
                }
            }

            @Override
            public void onFailure(Call<List<LexiquePanne>> call, Throwable t) {
                Log.d(TAG, "onFailure: getLexiquePanne FAILED");
            }
        });
    }

    public void getProfil() {
        mProfils.add(new Profil("1", "TCV"));
        mProfils.add(new Profil("2", "L1"));
        mProfils.add(new Profil("3", "L2"));
        mProfils.add(new Profil("4", "L3"));
        mProfils.add(new Profil("5", "ADM"));
    }

    public boolean isAuthenticated(String username, String password){

        String storedUsername = mSharedPref.getString(Constant.KEY_USERNAME, null);
        String storedPassword = mSharedPref.getString(Constant.KEY_PASSWORD, null);

        if (username.equals(storedUsername) && password.equals(storedPassword)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isActive() {
        return mSharedPref.getBoolean(Constant.KEY_IS_ACTIVE, false);
    }
}
