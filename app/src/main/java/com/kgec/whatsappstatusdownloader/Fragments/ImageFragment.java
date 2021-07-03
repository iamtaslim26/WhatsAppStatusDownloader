package com.kgec.whatsappstatusdownloader.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgec.whatsappstatusdownloader.Adapter.WhatsAppAdapter;
import com.kgec.whatsappstatusdownloader.R;
import com.kgec.whatsappstatusdownloader.model.WhatsaAppModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageFragment extends Fragment {

    private View view;
    private ArrayList<WhatsaAppModel>list;
    private WhatsAppAdapter whatsAppAdapter;
    private SwipeRefreshLayout refreshLayout;

    private RecyclerView recyclerView;


    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view= inflater.inflate(R.layout.fragment_image, container, false);

         recyclerView=view.findViewById(R.id.image_recycler_view);
         refreshLayout=view.findViewById(R.id.refresh_layout);


         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         recyclerView.setHasFixedSize(true);

          list=new ArrayList<>();


         refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {

                 list=new ArrayList<>();
                 getData();
                 refreshLayout.setRefreshing(false);
             }
         });

        getData();

        return view;
    }

    private void getData(){

        WhatsaAppModel model;

        String targetPath= Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/whatsApp/Media/.Statuses";

        File targetDirector=new File(targetPath);

        // To take all the files

        File[]allFiles=targetDirector.listFiles();
        Log.w("modified","="+allFiles[0].lastModified());

//        if(allFiles[1]!=null){
//            Log.e("allfiles","yes");
//        }
//        else {
//            Log.e("allfiles","No");
//
//        }

        // Now do for Whatsapp Businees Account

        String targetPathBusines= Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/WhatsApp Business/Media/.Statuses";

        File targetDirectorBusiness=new File(targetPathBusines);

        File[]allFilesBusineess=targetDirectorBusiness.listFiles();


        //Now do sort just we need the last one

        Arrays.sort(allFiles,((o1, o2) -> {

            if (o1.lastModified()>o2.lastModified()) return -1;
           else if (o1.lastModified()<o2.lastModified()) return 1;
           else return 0;

        }));

        // add in allFiles
        for (int i=0;i<allFiles.length;i++){

            File file=allFiles[i];
            if (Uri.fromFile(file).toString().endsWith(".png")|| Uri.fromFile(file).toString().endsWith(".jpg")){

                model=new WhatsaAppModel("whats"+i,
                        file.getPath(),
                        allFiles[i].getAbsolutePath(),
                        Uri.fromFile(file));

                list.add(model);
            }
        }



     /*   Arrays.sort(allFilesBusineess,((o1, o2) -> {

            if (o1.lastModified()>o2.lastModified()) return -1;
            else if (o1.lastModified()<o2.lastModified()) return +1;
            else return 0;

        }));

        // add in allFiles
        for (int i=0;i<allFilesBusineess.length;i++){

            File file=allFilesBusineess[i];
            if (Uri.fromFile(file).toString().endsWith(".png")|| Uri.fromFile(file).toString().endsWith(".jpg")){

                model=new WhatsaAppModel("whats"+i,
                        file.getPath(),
                        allFilesBusineess[i].getAbsolutePath(),
                        Uri.fromFile(file));

                list.add(model);
            }
        }


      */
        whatsAppAdapter=new WhatsAppAdapter(getContext(),list);
        recyclerView.setAdapter(whatsAppAdapter);
    }
}