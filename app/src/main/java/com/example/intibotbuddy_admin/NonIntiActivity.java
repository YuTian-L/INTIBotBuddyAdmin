package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NonIntiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_inti);

        String category = getString(R.string.button_transportationServices);
        setTitle(category.toUpperCase());
    }

    public void localShuttle(View view){
        Intent intent = new Intent(this, LocalShuttleActivity.class);
        startActivity(intent);
    }

    public void taxiService(View view){
        Intent intent = new Intent(this, TaxiServiceActivity.class);
        startActivity(intent);
    }

    public void otherOptions(View view){
        Intent intent = new Intent(this, OtherOptionsActivity.class);
        startActivity(intent);
    }
}
