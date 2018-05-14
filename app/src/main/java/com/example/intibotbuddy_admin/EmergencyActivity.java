package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmergencyActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Info> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        String category = getString(R.string.button_safetyHealthEmergency);
        setTitle(category.toUpperCase());

        listView = findViewById(R.id.emergencyListView);
        getJSON();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, Object> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Void... voids) {
                try {
                    String link = "http://pkunite2.000webhostapp.com/getID12.php";

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    return sb.toString();

                } catch (Exception e) {
                    Log.e("EmergencyActivityError", e.getLocalizedMessage());
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                try {
                    loadIntoListView((String)o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String line) throws JSONException {
        items = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(line);
        String[] emergency = new String[jsonObject.length()];
        Map<String, String> details = new HashMap<>();

        for (int i=0; jsonObject.has(String.valueOf(i+1)); i++){
            emergency[i] = jsonObject.getString(String.valueOf(i+1));
            for (int j=1; jsonObject.has(String.valueOf(i+1)+"info"+String.valueOf(j)); j++) {
                details.put(String.valueOf(i+1)+"info"+String.valueOf(j),jsonObject.getString(String.valueOf(i+1)+"info"+String.valueOf(j)));
            }
        }

        for (int i=1; jsonObject.has(String.valueOf(i)); i++) {
            Info item = new Info(jsonObject.getString(String.valueOf(i)), details.get(String.valueOf(i)+"info"+String.valueOf(1)), details.get(String.valueOf(i)+"info"+String.valueOf(2)), details.get(String.valueOf(i)+"info"+String.valueOf(3)));
            items.add(item);
        }

        ListViewAdapter adapter = new ListViewAdapter(this, items);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbuttons_add_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent_add = new Intent(this, AddEmergencyActivity.class);
                startActivity(intent_add);
                return true;

            case R.id.action_edit:
                Intent intent_edit = new Intent(this, EditEmergencyActivity.class);
                startActivity(intent_edit);
                return true;

            case R.id.action_delete:
                Intent intent_del = new Intent(this, DeleteEmergencyActivity.class);
                startActivity(intent_del);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
