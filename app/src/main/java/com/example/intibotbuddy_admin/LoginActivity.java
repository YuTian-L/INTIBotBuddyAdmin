package com.example.intibotbuddy_admin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by TIAN☺ on 26/01/2018.
 */

public class LoginActivity extends AsyncTask {

    private Context context;

    public LoginActivity(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        boolean connection = checkConnection();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.pass_connection), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getString(R.string.pass_connection), connection);
        editor.commit();
    }

    private boolean checkConnection() {
        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()==NetworkInfo.State.CONNECTED) {
            connected = true; //connected to a network
        }
        else {
            connected = false;
        }
        return connected;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        try {
            String username = (String) objects[0];
            String password = (String) objects[1];
            String link = "http://pkunite2.000webhostapp.com/adminlogin.php";

            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            // Read Server Response
            while ((line = reader.readLine()) != null) { // FIXME
                sb.append(line);
                break;
            }

            return sb.toString();
        } catch (Exception e) {
            Log.e("LoginActivityError", e.getLocalizedMessage());
            return  new String("Exception: " + e.getMessage());
        }
    }

    private void parseJSON(String line) throws JSONException {
        String title, message;
        JSONObject jsonObject = new JSONObject(line);
        title = jsonObject.getString("status");
        message = jsonObject.getString("message");

        if (title.equals("Welcome")) {
            Toast.makeText(context, title + "\n" + message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Main2Activity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        else {
            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.save_rememberMe), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(context.getString(R.string.save_rememberMe), false);
            editor.commit();

            SharedPreferences sharedPref2 = context.getSharedPreferences(context.getString(R.string.save_usernamePassword), MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sharedPref2.edit();
            editor2.clear();
            editor2.commit();

            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
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
            okButton.setTextColor(context.getResources().getColor(R.color.dark_blue_text));
            LinearLayout.LayoutParams buttonLayout = (LinearLayout.LayoutParams) okButton.getLayoutParams();
            buttonLayout.width = ViewGroup.LayoutParams.MATCH_PARENT;
            buttonLayout.gravity = Gravity.CENTER;
            okButton.setLayoutParams(buttonLayout);
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        try {
            // TODO dismiss keyboard
            parseJSON((String)o);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
