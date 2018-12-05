package com.rainbow.irt.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rainbow.irt.R;

public class CheckApprobationActivity extends AppCompatActivity {

    private static final String TAG = "CheckApprobationActivit";

    TextView mNomCompletTV, mTelephoneTV, mProfileTV, mStatuTV;
    Button mCheckBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_approbation);

        initScreen();
    }

    public void initScreen() {
        mNomCompletTV = findViewById(R.id.nom_complet_tv);
        mTelephoneTV = findViewById(R.id.telephone_tv);
        mProfileTV = findViewById(R.id.profile_tv);
        mStatuTV = findViewById(R.id.status_tv);

        mCheckBT = findViewById(R.id.check_bt);
        mCheckBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkApproval();
            }
        });
    }

    public void checkApproval(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Verification en cours...")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CheckApprobationActivity.this, CheckingActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}
