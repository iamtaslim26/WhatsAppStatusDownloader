package com.kgec.whatsappstatusdownloader.model;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class Utill {


    public static String RootDirectoryFacebook="/Storysaver/facebook/";

    public static File RootFileDirectory=new File(Environment.getExternalStorageDirectory()
            +"/Downloads/Storysaver/WhatsApp");


    // to create the folder

    public static void createFolder(){

        if (!RootFileDirectory.exists()){

            RootFileDirectory.mkdirs();
        }
    }

    public static void downloadFacebookVideo(String downloadpath, String destinationpath, Context context,String filename){

        Toast.makeText(context, "Downloading. . . ", Toast.LENGTH_SHORT).show();

        Uri uri=Uri.parse(downloadpath);
        DownloadManager.Request request=new DownloadManager.Request(uri);

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(filename);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,destinationpath+filename);
        ((DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);


    }
}
