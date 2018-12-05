package com.rainbow.irt.activity;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Incident;
import com.rainbow.irt.entite.Utilisateur;
import com.rainbow.irt.service.MobileApi;
import com.rainbow.irt.service.MobileApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SynchronizationActivity extends AppCompatActivity {

    private static final String TAG = "SynchronizationActivity";

    Button synchronizationBT;

    MobileApiInterface mobileApiInterface = MobileApi.getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronization);

        initScreen();
    }

    public void initScreen() {
        synchronizationBT = findViewById(R.id.synchronization_bt);
        synchronizationBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SynchronizationActivity.this, "Synchronisation", Toast.LENGTH_SHORT).show();
                synchroniser();
            }
        });
    }

    public void synchroniser(){
//        getIncidents();
//        getUtilisateursTCV();

        Utilisateur utilisateur1 = new Utilisateur("1007","Test 1", true, "1", "088888888");
        utilisateur1.codeProvince = "11";
        utilisateur1.codeTerritoireVille = "102";
        utilisateur1.codeSiteFormation = "10002";
        utilisateur1.codeBureauVote = "100002";
        Utilisateur utilisateur2 = new Utilisateur("1008","Test 2", true, "1", "09999999");
        utilisateur2.codeProvince = "11";
        utilisateur2.codeTerritoireVille = "102";
        utilisateur2.codeSiteFormation = "10002";
        utilisateur2.codeBureauVote = "100002";

        List<Utilisateur> testUtilisateurs = new ArrayList<>();
        testUtilisateurs.add(utilisateur1);
        testUtilisateurs.add(utilisateur2);

        postUtilisateurs(testUtilisateurs);
    }

    public void postIncidents(List<Incident> incidents) {
        if (incidents.size() > 0) {
            mobileApiInterface.postIncidentList(incidents).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()){
                        Log.e(TAG, "onResponse: " + response.body() );
                    } else {
                        Log.e(TAG, "onResponse: postIncidents body not successful : " + response.message());
                        Log.e(TAG, "onResponse: " + response.message() );
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e(TAG, "onFailure: postIncidents a echoue : " + t.getMessage());
                    Log.e(TAG, "onFailure: postIncidents a echoue : " + t.toString());
                }
            });
        }
    }

    public void postUtilisateurs(List<Utilisateur> utilisateurs) {

        if (utilisateurs.size() > 0) {
            mobileApiInterface.postUtilisateurs(utilisateurs).enqueue(new Callback<List<Utilisateur>>() {
                @Override
                public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                    if (response.isSuccessful()) {
                        List<Utilisateur> utilisateurs = response.body();
                        Log.e(TAG, "onResponse: " + response.message());
                        Log.e(TAG, "onResponse: Utilisateurs list size: " + utilisateurs.size() );
                    } else {
                        Log.e(TAG, "onResponse: " + response.message() );
                    }
                }

                @Override
                public void onFailure(Call<List<Utilisateur>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
    }


    public void getIncidents(){
        (new AsyncTask<Void, Void, List<Incident>>(){
            @Override
            protected void onPostExecute(List<Incident> incidents) {
                super.onPostExecute(incidents);

                if (incidents != null && incidents.size() > 0) {
                    postIncidents(incidents);
                }
            }

            @Override
            protected List<Incident> doInBackground(Void... voids) {
                return IrtDatabase.getInstance(SynchronizationActivity.this).getIIncidentDao().getAll();
            }
        }).execute();
    }

    public void getUtilisateursTCV(){
        (new AsyncTask<Void, Void, List<Utilisateur>>(){
            @Override
            protected void onPostExecute(List<Utilisateur> utilisateurs) {
                super.onPostExecute(utilisateurs);

                Log.e(TAG, "onPostExecute: size: " + utilisateurs.size() );

                if (utilisateurs != null && utilisateurs.size() > 0) {
                    postUtilisateurs(utilisateurs);
                }
            }

            @Override
            protected List<Utilisateur> doInBackground(Void... voids) {
                return IrtDatabase.getInstance(SynchronizationActivity.this).getIUtilisateurDao().getAllByProfileCode("1");
            }
        }).execute();
    }
}
