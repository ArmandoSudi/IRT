package com.rainbow.irt.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rainbow.irt.R;

public class MainActivity extends AppCompatActivity {

    Button mButtonChecking, mDesignBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonChecking = findViewById(R.id.checking_bt);
        mButtonChecking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckingActivity.class);
                startActivity(intent);
            }
        });

        mDesignBT = findViewById(R.id.design_bt);
        mDesignBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);
            }
        });
    }
}
