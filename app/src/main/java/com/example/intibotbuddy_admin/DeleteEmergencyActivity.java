package com.example.intibotbuddy_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DeleteEmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_emergency);

        String title = getString(R.string.action_delete);
        setTitle(title.toUpperCase());
    }

    public void delete(View view) {
        EditText editTextTitle = findViewById(R.id.emergencyTitleETdel);
        String title = editTextTitle.getText().toString();
        new DeleteEmergencyBgActivity(this).execute(title);
    }
}
