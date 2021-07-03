package com.kgec.whatsappstatusdownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoardActivity extends AppCompatActivity {
    private Button facebook_btn,whatsapp_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        facebook_btn=findViewById(R.id.facebook_page_link);
        whatsapp_btn=findViewById(R.id.whatsapp_page_link);

//        facebook_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),FacebookActivity.class));
//            }
//        });

        whatsapp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}