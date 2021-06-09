package com.example.erpsystem_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnstatus, btnthana, btndistrict, btnposition, btncountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstatus = findViewById(R.id.status);
        btnthana = findViewById(R.id.thana);
        btndistrict = findViewById(R.id.distric);
        btnposition = findViewById(R.id.position);
        btncountry = findViewById(R.id.country);
        btnstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewStatus_RV.class);
                startActivity(intent);
            }
        });
    btnthana.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intentThana = new Intent(MainActivity.this, ViewThana_RV.class);
        startActivity(intentThana);

    }
});

    btncountry.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentcountry = new Intent(MainActivity.this, ViewCountry_RV.class);
            startActivity(intentcountry);
        }
    });


    btnposition.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentposition = new Intent(MainActivity.this,ViewPosition_RV.class);
            startActivity(intentposition);
        }
    });

        btndistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdistrict = new Intent(MainActivity.this,ViewDistrict_RV.class);
                startActivity(intentdistrict);
            }
        });


    }
}