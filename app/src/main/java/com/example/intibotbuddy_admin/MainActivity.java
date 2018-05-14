package com.example.intibotbuddy_admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.save_rememberMe), MODE_PRIVATE);
        Boolean rememberMe = sharedPref.getBoolean(getString(R.string.save_rememberMe), false);

        // check if user "kept logged in"
        if (rememberMe.equals(true)) {
            SharedPreferences sharedPref2 = this.getSharedPreferences(getString(R.string.save_usernamePassword), MODE_PRIVATE);
            String username = sharedPref2.getString(getString(R.string.save_username), "defaultValue:username");

            // pass studentID - if user "kept logged in"
            passStudentID(username);

            Toast.makeText(this, "Welcome back to INTI Bot-Buddy Admin app" , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Main2Activity.class);
            this.startActivity(intent);
            this.finish();
        }

        // show autocomplete suggestions for studentID during Login
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            JSONArray jsonArray = new JSONArray(prefs.getString(getString(R.string.sp_preferenceName), "defaultValue:noPrefName"));
            String[] prefName = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                prefName[i] = jsonArray.getString(i);
                //Log.e("JSON Array", jsonArray.getString(i));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, prefName);
            AutoCompleteTextView textView = findViewById(R.id.userIDInput);
            textView.setAdapter(adapter);
            textView.setThreshold(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rememberMe(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        if (checked) {
            // save "keep me logged in" is checked
            SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.save_rememberMe), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(getString(R.string.save_rememberMe), checked);
            editor.commit();

            EditText editTextStudentID = findViewById(R.id.userIDInput);
            EditText editTextPassword = findViewById(R.id.passwordInput);
            String username = editTextStudentID.getText().toString();
            String password = editTextPassword.getText().toString();

            // save username and password
            SharedPreferences sharedPref2 = this.getSharedPreferences(getString(R.string.save_usernamePassword), MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sharedPref2.edit();
            editor2.putString(getString(R.string.save_username), username);
            editor2.putString(getString(R.string.save_password), password);
            editor2.commit();
        }
        else {
            SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.save_rememberMe), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(getString(R.string.save_rememberMe), checked);
            editor.commit();

            SharedPreferences sharedPref2 = this.getSharedPreferences(getString(R.string.save_usernamePassword), MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sharedPref2.edit();
            editor2.clear();
            editor2.commit();
        }
    }

    public void login(View view) {
        EditText editTextStudentID = findViewById(R.id.userIDInput);
        EditText editTextPassword = findViewById(R.id.passwordInput);
        String username = editTextStudentID.getText().toString();
        String password = editTextPassword.getText().toString();
        new LoginActivity(this).execute(username, password);

        // pass studentID
        passStudentID(username);

        // save studentIDs
        saveStudentID(username);

        SharedPreferences sharedPref2 = this.getSharedPreferences(getString(R.string.pass_connection), MODE_PRIVATE);
        Boolean connection = sharedPref2.getBoolean(getString(R.string.pass_connection), false);

        if (connection == false) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No internet connection");
            alertDialog.setMessage("Please connect to an active internet to login and use the app");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alertDialog.show();
            // TODO center alertTitle OR remove below code that center alertMessage
            //TextView alertMessage = alertDialog.findViewById(android.R.id.message);
            //alertMessage.setGravity(Gravity.CENTER);
            Button okButton = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
            okButton.setTextColor(this.getResources().getColor(R.color.dark_blue_text));
            LinearLayout.LayoutParams buttonLayout = (LinearLayout.LayoutParams) okButton.getLayoutParams();
            buttonLayout.width = ViewGroup.LayoutParams.MATCH_PARENT;
            buttonLayout.gravity = Gravity.CENTER;
            okButton.setLayoutParams(buttonLayout);
        }

        //Log.e("passValue", username);
    }

    public void passStudentID(String username) {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.pass_username), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.pass_username), username);
        editor.commit();
    }

    public void saveStudentID(String studentID) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(studentID);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(getString(R.string.sp_preferenceName), jsonArray.toString());
        prefsEditor.commit();
        //Log.e("input prefName Array", jsonArray.toString());
    }
}
