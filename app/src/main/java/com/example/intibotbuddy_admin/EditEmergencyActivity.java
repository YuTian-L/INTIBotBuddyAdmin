package com.example.intibotbuddy_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditEmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emergency);

        String title = getString(R.string.action_edit);
        setTitle(title.toUpperCase());
    }

    public void edit(View view) {
        EditText editTextTitle = findViewById(R.id.emergencyTitleETedit);
        EditText editTextNewTitle = findViewById(R.id.emergencyNewTitleET);
        EditText editTextNewOperatingHours = findViewById(R.id.emergencyNewOperatingHoursET);
        EditText editTextNewContact = findViewById(R.id.emergencyNewContactET);
        EditText editTextNewLocation = findViewById(R.id.emergencyNewLocationET);
        String title = editTextTitle.getText().toString();
        String newTitle = editTextNewTitle.getText().toString();
        String newOperatingHours = editTextNewOperatingHours.getText().toString();
        String newContact = editTextNewContact.getText().toString();
        String newLocation = editTextNewLocation.getText().toString();
        new EditEmergencyBgActivity(this).execute(title, newTitle, newOperatingHours, newContact, newLocation);
    }
}
