package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.pass_username), MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.pass_username), "defaultValue:username");
        setTitle("Greetings " + username);
    }

    public void safety(View view){
        Intent intent = new Intent(this, SafetyActivity.class);
        startActivity(intent);
    }

    public void academic(View view){
        Intent intent = new Intent(this, AcademicActivity.class);
        startActivity(intent);
    }

    public void transportation(View view){
        Intent intent = new Intent(this, TransportationActivity.class);
        startActivity(intent);
    }

    public void facilities(View view){
        Intent intent = new Intent(this, FacilitiesActivity.class);
        startActivity(intent);
    }

    public void contacts(View view){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void activities(View view){
        Intent intent = new Intent(this, ActivitiesActivity.class);
        startActivity(intent);
    }

    public void helpdesks(View view){
        Intent intent = new Intent(this, HelpdesksActivity.class);
        startActivity(intent);
    }

    public void wifi(View view){
        Intent intent = new Intent(this, WifiActivity.class);
        startActivity(intent);
    }

    public void extraInfo(View view){
        Intent intent = new Intent(this, ExtraInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbuttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private static long back_pressed;

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        }
        else {
            Toast.makeText(this, "Press back again to exit app", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }
}
