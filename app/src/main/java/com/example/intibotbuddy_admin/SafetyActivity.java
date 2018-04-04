package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SafetyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
    }

    public void injured(View view){
        Intent intent = new Intent(this, InjuredActivity.class);
        startActivity(intent);
    }

    public void emergency(View view){
        Intent intent = new Intent(this, EmergencyActivity.class);
        startActivity(intent);
    }

    public void counselling(View view){
        Intent intent = new Intent(this, CounsellingActivity.class);
        startActivity(intent);
    }
}
