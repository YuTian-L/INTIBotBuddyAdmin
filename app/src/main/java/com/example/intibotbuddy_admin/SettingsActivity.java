package com.example.intibotbuddy_admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.pass_username), MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.pass_username), "defaultValue:username");
        setTitle("INTI Bot-Buddy        " + username);
    }

    public void save(View view){
        EditText editTextCurrentPassword = findViewById(R.id.currentPasswordInput);
        EditText editTextNewPassword = findViewById(R.id.newPasswordInput);
        EditText editTextConfirmNewPassword = findViewById(R.id.confirmNewPasswordInput);
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.pass_username), MODE_PRIVATE);
        String username = sharedPref.getString(getString(R.string.pass_username), "defaultValue:username");
        String currentPassword = editTextCurrentPassword.getText().toString();
        String newPassword = editTextNewPassword.getText().toString();
        String confirmNewPassword = editTextConfirmNewPassword.getText().toString();
        new SettingsBgActivity(this).execute(username, currentPassword, newPassword, confirmNewPassword);

        //Log.e("getValue", username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbuttonsettingslogout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.save_rememberMe), MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.clear();
                                editor.commit();
                                SharedPreferences sharedPref2 = getApplicationContext().getSharedPreferences(getString(R.string.save_usernamePassword), MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sharedPref2.edit();
                                editor2.clear();
                                editor2.commit();
                                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); //clear all previous activities
                                startActivity(intent);
                                break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialogInterface.dismiss();
                                    break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Logging out");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES", dialogClickListener);
                builder.setNegativeButton("NO", dialogClickListener);
                AlertDialog alert = builder.create();
                alert.show();

                // TODO center alertTitle OR remove below code that center alertMessage
                //TextView alertMessage = alertDialog.findViewById(android.R.id.message);
                //alertMessage.setGravity(Gravity.CENTER);
                Button yesButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                yesButton.setTextColor(this.getResources().getColor(R.color.dark_blue_text));

                Button noButton = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
                noButton.setTextColor(this.getResources().getColor(R.color.dark_blue_text));

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
