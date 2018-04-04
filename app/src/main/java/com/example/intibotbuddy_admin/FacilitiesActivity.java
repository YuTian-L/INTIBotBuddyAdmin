package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FacilitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);
    }

    public void general(View view){
        Intent intent = new Intent(this, FacilitiesGeneralActivity.class);
        startActivity(intent);
    }

    public void academicBlock(View view){
        Intent intent = new Intent(this, FacilitiesAcademicActivity.class);
        startActivity(intent);
    }

    public void hostelArea(View view){
        Intent intent = new Intent(this, FacilitiesHostelActivity.class);
        startActivity(intent);
    }

    public void sportsRecreational(View view){
        Intent intent = new Intent(this, SportsRecreationalActivity.class);
        startActivity(intent);
    }
}
