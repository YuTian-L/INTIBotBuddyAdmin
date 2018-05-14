package com.example.intibotbuddy_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DeleteInjuredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_injured);

        String title = getString(R.string.action_delete);
        setTitle(title.toUpperCase());
    }

    public void delete(View view) {
        EditText editTextTitle = findViewById(R.id.injuredTitleETdel);
        String title = editTextTitle.getText().toString();
        new DeleteInjuredBgActivity(this).execute(title);
    }
}
