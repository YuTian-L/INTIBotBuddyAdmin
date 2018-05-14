package com.example.intibotbuddy_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddEmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency);

        String title = getString(R.string.action_add);
        setTitle(title.toUpperCase());
    }

    public void add(View view) {
        EditText editTextTitle = findViewById(R.id.emergencyTitleET);
        EditText editTextOperatingHours = findViewById(R.id.emergencyOperatingHoursET);
        EditText editTextContact = findViewById(R.id.emergencyContactET);
        EditText editTextLocation = findViewById(R.id.emergencyLocationET);
        String title = editTextTitle.getText().toString();
        String operatingHours = editTextOperatingHours.getText().toString();
        String contact = editTextContact.getText().toString();
        String location = editTextLocation.getText().toString();
        new AddEmergencyBgActivity(this).execute(title, operatingHours, contact, location);
    }
}
