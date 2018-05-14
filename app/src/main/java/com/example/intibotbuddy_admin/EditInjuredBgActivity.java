package com.example.intibotbuddy_admin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class EditInjuredBgActivity extends AsyncTask {

    private Context context;

    public EditInjuredBgActivity(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            String title = (String) objects[0];
            String newTitle = (String) objects[1];
            String newOperatingHours = (String) objects[2];
            String newContact = (String) objects[3];
            String newLocation = (String) objects[4];
            String link = "http://pkunite2.000webhostapp.com/editID11.php";

            if (!title.equals("")) {
                if (!newTitle.equals("") || !newOperatingHours.equals("") || !newContact.equals("") || !newLocation.equals("")) {
                    String data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");
                    data += "&" + URLEncoder.encode("newTitle", "UTF-8") + "=" + URLEncoder.encode(newTitle, "UTF-8");
                    data += "&" + URLEncoder.encode("newOperatingHours", "UTF-8") + "=" + URLEncoder.encode(newOperatingHours, "UTF-8");
                    data += "&" + URLEncoder.encode("newContact", "UTF-8") + "=" + URLEncoder.encode(newContact, "UTF-8");
                    data += "&" + URLEncoder.encode("newLocation", "UTF-8") + "=" + URLEncoder.encode(newLocation, "UTF-8");

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
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    return sb.toString();
                } else {
                    return "{\"status\":\"Invalid\",\"message\":\"At least ONE(1) field is required other than Title to successfully make changes\"}";
                }
            } else {
                return "{\"status\":\"Invalid\",\"message\":\"Title is required to locate data\"}";
            }

        } catch (Exception e) {
            Log.e("EdtInjuredActivityError", e.getLocalizedMessage());
            return new String("Exception: " + e.getMessage());
        }
    }

    private void parseJSON(String line) throws JSONException {
        String title, message;
        JSONObject jsonObject = new JSONObject(line);
        title = jsonObject.getString("status");
        message = jsonObject.getString("message");

        if (title.equals("Edited")) {
            Toast.makeText(context, title + "\n" + message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, InjuredActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        } else {
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
            parseJSON((String) o);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
