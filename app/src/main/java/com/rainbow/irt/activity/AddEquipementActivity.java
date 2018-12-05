package com.rainbow.irt.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Equipement;
import com.rainbow.irt.utils.Constant;

public class AddEquipementActivity extends AppCompatActivity {

    Button mSaveBT, mCancelBT;
    EditText mNumeroSerieET, mDescriptionET, mCodeEquipementET;
    Spinner mTypeEquipementSP;
    String mTypeEquipement;
    String codeSiteFormation;

    SharedPreferences mSharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipement);

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        initScreen();
        
    }
    
    public void initScreen() {
        mCancelBT = findViewById(R.id.cancel_bt);
        mSaveBT = findViewById(R.id.save_bt);
        mCodeEquipementET = findViewById(R.id.code_equipement_et);
        mNumeroSerieET = findViewById(R.id.numero_serie_et);
        mDescriptionET = findViewById(R.id.description_et);
        mTypeEquipementSP = findViewById(R.id.type_equipement_sp);

        mTypeEquipementSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTypeEquipement = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTypeEquipement = (String) parent.getItemAtPosition(0);
            }
        });
        
        mCancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        
        mSaveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public Equipement collectData() {
        String codeEquipement = mCodeEquipementET.getText().toString();
        String numeroSerie = mNumeroSerieET.getText().toString();
        String description = mDescriptionET.getText().toString();
        codeSiteFormation = mSharedPref.getString(Constant.KEY_USER_CODE_SITE_FORMATION, null);
        return new Equipement(codeEquipement, numeroSerie, description, mTypeEquipement, codeSiteFormation);
    }
    
    public void cancel() {
        Toast.makeText(this, "Annuler enregistrement", Toast.LENGTH_SHORT).show();
        finish();
    }
    
    public void save() {
        new SaveEquipementAsyncTask(this).execute();
    }

    class SaveEquipementAsyncTask extends AsyncTask<Void, Void, Long> {
        Activity mActivity;

        public SaveEquipementAsyncTask(Activity mActivity) {
            this.mActivity = mActivity;
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            if (result != 0L) {
                Toast.makeText(mActivity, "Equipement ajoute", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(mActivity, CheckingActivity.class);
            mActivity.startActivity(intent);
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long[] row = IrtDatabase.getInstance(mActivity).getIEquipementDao().insert(collectData());
            return row[0];
        }
    }
}
