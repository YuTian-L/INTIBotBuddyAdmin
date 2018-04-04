package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
    }

    public void sports(View view){
        Intent intent = new Intent(this, SportsActivity.class);
        startActivity(intent);
    }

    public void clubsSocieties(View view){
        Intent intent = new Intent(this, ClubsSocietiesActivity.class);
        startActivity(intent);
    }

    public void events(View view){
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }
}
