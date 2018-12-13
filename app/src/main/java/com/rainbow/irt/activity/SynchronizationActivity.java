package com.rainbow.irt.activity;

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
        getIncidents();

    }

    public void postIncidents(List<Incident> incidents) {
        if (incidents.size() > 0) {
            mobileApiInterface.postIncidentList(incidents).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
//                            Toast.makeText(SynchronizationActivity.this, "Données synchronisées", Toast.LENGTH_SHORT).show();
                            String message = response.body();
                            Log.e(TAG, "onResponse: Message: " + message );
                        }
                    } else {
                        Log.e(TAG, "onResponse: " + response.message() );
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e(TAG, "onFailure: postIncidents a echoue : " + t.getMessage());
                    Toast.makeText(SynchronizationActivity.this,
                            "Echec de synchronization, vérifier la connexion et réesayer", Toast.LENGTH_LONG).show();
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
                    for (Incident incident : incidents) {
                        Log.e(TAG, "onPostExecute: INCIDENTS: " + incident);
                    }
                }
            }

            @Override
            protected List<Incident> doInBackground(Void... voids) {
                return IrtDatabase.getInstance(SynchronizationActivity.this).getIIncidentDao().getAll();
            }
        }).execute();
    }


}
