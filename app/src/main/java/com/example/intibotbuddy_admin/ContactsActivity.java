package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }

    public void generalContacts(View view){
        Intent intent = new Intent(this, ContactsGeneralActivity.class);
        startActivity(intent);
    }

    public void academicBlockContacts(View view){
        Intent intent = new Intent(this, ContactsAcademicActivity.class);
        startActivity(intent);
    }

    public void hostelAreaContacts(View view){
        Intent intent = new Intent(this, ContactsHostelActivity.class);
        startActivity(intent);
    }

    public void others(View view){
        Intent intent = new Intent(this, ContactsOthersActivity.class);
        startActivity(intent);
    }
}
