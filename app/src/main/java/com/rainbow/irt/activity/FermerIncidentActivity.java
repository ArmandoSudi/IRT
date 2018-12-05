package com.rainbow.irt.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rainbow.irt.R;
import com.rainbow.irt.database.IrtDatabase;
import com.rainbow.irt.entite.Incident;
import com.rainbow.irt.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FermerIncidentActivity extends AppCompatActivity {

    private static final String TAG = "FermerIncidentActivity";

    Date mDateFermeture;
    private Calendar mCalendar = Calendar.getInstance();

    TextView mDateTV;
    EditText mDescriptionET;
    ImageView mCalendarIV;
    Incident mIncident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fermer_incident);

        updateLabel();

        Intent intent = getIntent();
        int codeIncident = intent.getIntExtra(Constant.KEY_CODE_INCIDENT, -1);

        if (codeIncident >= 0) {
            loadIncident(codeIncident);
        }

        initScreen();

    }

    public void initScreen(){
        mDescriptionET = findViewById(R.id.description_et);
        Button fermerBT = findViewById(R.id.fermer_incident_bt);
        fermerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collect();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        mCalendarIV = findViewById(R.id.calendar_iv);
        mCalendarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FermerIncidentActivity.this, date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void collect() {
        mIncident.detailsResolution = mDescriptionET.getText().toString();
        mIncident.dateFermeture = mDateFermeture;

        updateIncident(mIncident);
    }

    public void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        mDateTV = findViewById(R.id.date_tv);
        mDateTV.setText(sdf.format(mCalendar.getTime()));
        mDateFermeture = mCalendar.getTime();
    }

    public void loadIncident(final int codeIncident) {
        (new AsyncTask<Integer, Void, Incident>(){
            @Override
            protected void onPostExecute(Incident incident) {
                super.onPostExecute(incident);

                mIncident = incident;
            }

            @Override
            protected Incident doInBackground(Integer... integers) {
                return IrtDatabase.getInstance(FermerIncidentActivity.this).getIIncidentDao().getByCodeIncident(codeIncident);
            }
        }).execute(codeIncident);
    }

    public void updateIncident(final Incident incident) {

        (new AsyncTask<Incident, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(FermerIncidentActivity.this, "Incident ferme", Toast.LENGTH_SHORT).show();
                FermerIncidentActivity.this.finish();
            }

            @Override
            protected Void doInBackground(Incident... incidents) {
                IrtDatabase.getInstance(FermerIncidentActivity.this).getIIncidentDao().update(incidents);
                return null;
            }
        }).execute(incident);
    }

}
