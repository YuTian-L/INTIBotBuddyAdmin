package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ByIntiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_inti);

        String category = getString(R.string.button_transportationServices);
        setTitle(category.toUpperCase());
    }

    public void intiShuttle(View view){
        Intent intent = new Intent(this, IntiShuttleActivity.class);
        startActivity(intent);
    }
}
