package com.rainbow.irt.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Incident;
import com.rainbow.irt.entite.LexiquePanne;
import com.rainbow.irt.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LogIncidentActivity extends AppCompatActivity {

    private static final String TAG = "LogIncidentActivity";

    Spinner mTypePanneSP;
    TextView mDateTV;
    ImageView mCalendarIV;
    Date mDateIncident;
    Button mSaveIncidentBT, mCancelBT;
    private Calendar mCalendar = Calendar.getInstance();

    String mCodeEquipement, mCodeLexiquePanne, mCodeUtilisateur;
    SharedPreferences mSharedPref;

    boolean isAffected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_incident);

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        mCodeUtilisateur = mSharedPref.getString(Constant.KEY_CODE_UTILISATEUR, null);
        isAffected = mSharedPref.getBoolean(Constant.KEY_IS_AFFECTED, false);

        Intent intent = getIntent();
        mCodeEquipement = intent.getStringExtra(Constant.KEY_CODE_EQUIPEMENT);

        initView();
    }

    public void initView() {

        showPanne();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        mDateTV = findViewById(R.id.date_tv);
        mCalendarIV = findViewById(R.id.calendar_iv);
        mSaveIncidentBT = findViewById(R.id.save_incident_bt);
        mCancelBT = findViewById(R.id.cancel_bt);

        updateLabel();

        mSaveIncidentBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIncident();
            }
        });

        mCancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIncidentActivity.this.finish();
            }
        });

        mCalendarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LogIncidentActivity.this, date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mTypePanneSP = findViewById(R.id.type_incident_sp);
    }

    public void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        mDateTV.setText(sdf.format(mCalendar.getTime()));
        mDateIncident = mCalendar.getTime();
    }


    public void showPanne() {
        (new AsyncTask<Void, Void, List<LexiquePanne>>() {
            @Override
            protected void onPostExecute(List<LexiquePanne> lexiquePannes) {
                super.onPostExecute(lexiquePannes);

                if (lexiquePannes != null && lexiquePannes.size() > 0) {
                    mTypePanneSP.setAdapter(new ArrayAdapter<LexiquePanne>(
                            LogIncidentActivity.this,
                            android.R.layout.simple_spinner_item,
                            lexiquePannes
                    ));

                    mTypePanneSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            LexiquePanne lexiquePanne = (LexiquePanne) parent.getItemAtPosition(position);
                            mCodeLexiquePanne = lexiquePanne.codeLexiquePanne;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            protected List<LexiquePanne> doInBackground(Void... voids) {
                return IrtDatabase.getInstance(LogIncidentActivity.this).getILexiquePanneDao().get();
            }
        }).execute();
    }

    public void saveIncident() {

        Incident incident = new Incident(mCodeEquipement, mCodeUtilisateur, mDateIncident, mCodeLexiquePanne, mDateIncident, mDateIncident, "Non Resolu" );

        (new AsyncTask<Incident, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Log.d(TAG, "saveIncident: INCIDENT ENREGISTRE AVEC SUCCES");
                Intent intent = new Intent(LogIncidentActivity.this, DetailsEquipementActivity.class);
                intent.putExtra(Constant.KEY_CODE_EQUIPEMENT, mCodeEquipement);
                intent.putExtra(Constant.KEY_IS_AFFECTED, isAffected);
                LogIncidentActivity.this.startActivity(intent);
            }

            @Override
            protected Void doInBackground(Incident... incidents) {
                IrtDatabase.getInstance(LogIncidentActivity.this).getIIncidentDao().insert(incidents);
                return null;
            }
        }).execute(incident);
    }
}
