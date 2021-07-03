package com.kgec.whatsappstatusdownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kgec.whatsappstatusdownloader.model.Utill;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FacebookActivity extends AppCompatActivity {
    private EditText et_link;
    private Button download_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        et_link=findViewById(R.id.fb_url_link);
        download_btn=findViewById(R.id.download_fb_btn);


        download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String link=et_link.getText().toString();

                if (TextUtils.isEmpty(link)){

                    Toast.makeText(FacebookActivity.this, "Please Enter the link first. . .. ", Toast.LENGTH_SHORT).show();
                }
                else {

                    getDownloadData();
                }
            }
        });

        
    }

    private void getDownloadData() {

        URL url=null;
        try {

            url=new URL(et_link.getText().toString());
            String host=url.getHost();

            if (host.contains("facebook.com")){

                //Log.w("call","failed");
                callgetFbdata callgetFbdata=new callgetFbdata();
                callgetFbdata.execute(et_link.getText().toString());
            }
            else {
             //   Log.w("call","sucess");
                Toast.makeText(this, "Please provide a valid facebook link", Toast.LENGTH_SHORT).show();

            }
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
    }

    class callgetFbdata extends AsyncTask<String,Void, Document>{

      Document fbDoc;
        @Override
        protected Document doInBackground(String... strings) {

            try {

                fbDoc= Jsoup.connect(strings[0]).get();

            } catch (IOException e) {

                e.printStackTrace();
            }
            return fbDoc;
        }

        @Override
        protected void onPostExecute(Document document) {

            String videoUrl=document.select("meta[property=\"og:video\"]").last().attr("content");
           // Log.w("call","Antisucess");

            if (!videoUrl.equals("")){

                Utill.downloadFacebookVideo(videoUrl,Utill.RootDirectoryFacebook,FacebookActivity.this,System.currentTimeMillis()+".mp4");
            }

        }
    }


}