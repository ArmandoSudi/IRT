package com.rainbow.irt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.rainbow.irt.adapter.EquipementAdapter;
import com.rainbow.irt.entite.Equipement;

import java.util.ArrayList;
import java.util.List;

public class CheckingActivity extends AppCompatActivity {

    RecyclerView mEquipementRV;
    EquipementAdapter mEquipementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking);

        mEquipementRV = findViewById(R.id.equipement_rv);

        mEquipementAdapter = new EquipementAdapter(this, populateEquipement());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mEquipementRV.setLayoutManager(linearLayoutManager);
        mEquipementRV.setHasFixedSize(true);
        mEquipementRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        mEquipementRV.setAdapter(mEquipementAdapter);

    }

    public List<Equipement> populateEquipement() {
        List<Equipement> equipements = new ArrayList<>();

        equipements.add(new Equipement("1001", "en bon etat", "Batterie", "1000"));
        equipements.add(new Equipement("1002", "couleur rouge", "TSV", "1000"));
        equipements.add(new Equipement("1003", "couleur blanche", "TSV", "1000"));
        equipements.add(new Equipement("1004", "en bon etat", "TSV", "1000"));
        equipements.add(new Equipement("1005", "ne fonctionne pas", "Batterie", "1000"));
        equipements.add(new Equipement("1006", "en bon etat", "Batterie", "1000"));

        return equipements;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.checkquing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_populate_equipement) {

        }

        return super.onOptionsItemSelected(item);
    }


}
