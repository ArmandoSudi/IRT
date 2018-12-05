package com.rainbow.irt.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.rainbow.irt.R;
import com.rainbow.irt.adapter.BureauVoteEquipementAdapter;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.BureauVoteEquipement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AffectationListActivity extends AppCompatActivity {

    private static final String TAG = "AffectationListActivity";
    List<BureauVoteEquipement> mAffectationList = new ArrayList<>();
    BureauVoteEquipementAdapter mAffectationAdapter;
    RecyclerView mAffectationRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affectation_list);

        mAffectationAdapter = new BureauVoteEquipementAdapter(this, mAffectationList);

        mAffectationRV = findViewById(R.id.affectation_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAffectationRV.setLayoutManager(linearLayoutManager);
        mAffectationRV.setHasFixedSize(true);
        mAffectationRV.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        mAffectationRV.setAdapter(mAffectationAdapter);

        loadAffectation();

    }

    public void loadAffectation() {
        (new AsyncTask<Void, Void, List<BureauVoteEquipement>>(){
            @Override
            protected void onPostExecute(List<BureauVoteEquipement> bureauVoteEquipements) {
                super.onPostExecute(bureauVoteEquipements);

                mAffectationAdapter.addAll(bureauVoteEquipements);
            }

            @Override
            protected List<BureauVoteEquipement> doInBackground(Void... voids) {
                return IrtDatabase.getInstance(AffectationListActivity.this).getIBureauVoteEquipementDao().getAll();
            }
        }).execute();
    }

    public void updateLabel(TextView view, Date date) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        view.setText(sdf.format(date));
    }
}
