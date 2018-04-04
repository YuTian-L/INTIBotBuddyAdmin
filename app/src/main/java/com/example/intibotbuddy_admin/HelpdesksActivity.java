package com.example.intibotbuddy_admin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpdesksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpdesks);
    }

    public void IThelpdesk(View view){
        Uri uriUrl = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSedpripd4cdv_Wjw1kSvlTdTvZC54B64QJ_TUUUECLVe3efgg/viewform?embedded=true&formkey=dENSeXlTdl9yc0tOX0lyaWJFT01RRmc6MA");
        Intent openUrl = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(openUrl);
        /*Intent intent = new Intent(this, ItHelpdeskActivity.class);
        startActivity(intent);*/
    }

    public void AFMhelpdesk(View view){
        Uri uriUrl = Uri.parse("http://www.intimal.edu.my/AFMSR/Default.aspx");
        Intent openUrl = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(openUrl);
        /*Intent intent = new Intent(this, AfmHelpdeskActivity.class);
        startActivity(intent);*/
    }

    public void onlineSuggestion(View view){
        Uri uriUrl = Uri.parse("http://suggestion.iu.newinti.edu.my/");
        Intent openUrl = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(openUrl);
        /*Intent intent = new Intent(this, OnlineSuggestionActivity.class);
        startActivity(intent);*/
    }

    public void makeEnquiry(View view){
        Uri uriUrl = Uri.parse("http://newinti.edu.my/main/request-information");
        Intent openUrl = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(openUrl);
        /*Intent intent = new Intent(this, MakeEnquiryActivity.class);
        startActivity(intent);*/
    }
}
