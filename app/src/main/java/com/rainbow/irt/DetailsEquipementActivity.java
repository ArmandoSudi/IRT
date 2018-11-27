package com.rainbow.irt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class DetailsEquipementActivity extends AppCompatActivity {

    ImageButton mLogIncidentIBT, mAffectIBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_equipement);
        initScreen();
    }

    public void initScreen(){


    }

    public void logIncident(View v) {
        Toast.makeText(this, "Enregistrer incident", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LogIncidentActivity.class);
        startActivity(intent);
    }

    public void affectEquipement(View v) {
        Toast.makeText(this, "Affecter equipement", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AffecterEquipementActivity.class);
        startActivity(intent);
    }
}
