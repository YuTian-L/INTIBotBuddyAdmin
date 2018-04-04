package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AcademicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);
    }

    public void gettingInTouch(View view){
        Intent intent = new Intent(this, GetInTouchActivity.class);
        startActivity(intent);
    }

    public void websites(View view){
        Intent intent = new Intent(this, WebsitesActivity.class);
        startActivity(intent);
    }

    public void socialMedia(View view){
        Intent intent = new Intent(this, SocialMediaActivity.class);
        startActivity(intent);
    }

    public void academicWebsitesRes(View view){
        Intent intent = new Intent(this, AcademicWebsitesResActivity.class);
        startActivity(intent);
    }

    public void intimaFb(View view){
        Intent intent = new Intent(this, IntimaFbActivity.class);
        startActivity(intent);
    }
}
