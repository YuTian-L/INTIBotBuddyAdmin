package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TransportationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);
    }

    public void byINTI(View view){
        Intent intent = new Intent(this, ByIntiActivity.class);
        startActivity(intent);
    }

    public void nonINTI(View view){
        Intent intent = new Intent(this, NonIntiActivity.class);
        startActivity(intent);
    }

    public void whereToWaitForBus(View view){
        Intent intent = new Intent(this, WhereToWaitForBusActivity.class);
        startActivity(intent);
    }
}
