package com.example.intibotbuddy_admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by TIAN☺ on 29/03/2018.
 */

public class LoggedInActivity extends AsyncTask {

    private Context context;

    public LoggedInActivity(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        boolean connection = checkConnection();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.pass_connection), context.MODE_PRIVATE);
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
    protected Object doInBackground(Object[] objects) {
        try {
            String username = (String) objects[0];
            String password = (String) objects[1];
            String link = "http://pkunite2.000webhostapp.com/login.php";

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
            Toast.makeText(context, "Welcome back to INTI Bot-Buddy" , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Main2Activity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
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
