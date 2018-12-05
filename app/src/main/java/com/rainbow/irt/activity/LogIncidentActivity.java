package com.rainbow.irt.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
    Button mSaveIncidentBT;
    private Calendar mCalendar = Calendar.getInstance();

    String mCodeEquipement, mCodeLexiquePanne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_incident);

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

        updateLabel();

        mSaveIncidentBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIncident();
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

    public void populatePanne() {
        (new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                List<LexiquePanne> lexiquePannes = new ArrayList<>();
                lexiquePannes.add(new LexiquePanne("LEX100", "Panne A"));
                lexiquePannes.add(new LexiquePanne("LEX101", "Panne B"));
                lexiquePannes.add(new LexiquePanne("LEX102", "Panne C"));
                lexiquePannes.add(new LexiquePanne("LEX103", "Panne D"));

                IrtDatabase.getInstance(LogIncidentActivity.this).getILexiquePanneDao().insertAll(lexiquePannes);

                return null;
            }
        }).execute();
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

        Incident incident = new Incident(mCodeEquipement, "10001", mDateIncident, mCodeLexiquePanne, mDateIncident, mDateIncident, "Non Resolu" );

        (new AsyncTask<Incident, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                LogIncidentActivity.this.finish();
                Toast.makeText(LogIncidentActivity.this, "Incident saved successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Void doInBackground(Incident... incidents) {
                IrtDatabase.getInstance(LogIncidentActivity.this).getIIncidentDao().insert(incidents);
                return null;
            }
        }).execute(incident);
    }
}
