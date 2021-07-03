package com.kgec.whatsappstatusdownloader.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kgec.whatsappstatusdownloader.Adapter.WhatsAppAdapter;
import com.kgec.whatsappstatusdownloader.R;
import com.kgec.whatsappstatusdownloader.model.Utill;
import com.kgec.whatsappstatusdownloader.model.WhatsaAppModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class VideosFragment extends Fragment {

    private View videosView;
    private ArrayList<WhatsaAppModel> list;
    private WhatsAppAdapter whatsAppAdapter;
    private SwipeRefreshLayout refreshLayout;

    private RecyclerView recyclerView;

    public VideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        videosView= inflater.inflate(R.layout.fragment_videos, container, false);

        recyclerView=videosView.findViewById(R.id.video_recycler_view);
        refreshLayout=videosView.findViewById(R.id.refresh_layout_videos);

        list=new ArrayList<>();

        getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                list=new ArrayList<>();
                getData();
                refreshLayout.setRefreshing(false);
            }
        });



        return videosView;



    }

    private void getData() {

         WhatsaAppModel model;

        String targetpath= Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/WhatsApp/Media/.Statuses";

        File targetDirectory=new File(targetpath);
        File []allFiles=targetDirectory.listFiles();

      /*  String targetpathBusiness= Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/WhatsApp/Media/.Statuses";

        File targetDirectoryBusiness=new File(targetpathBusiness);
        File []allFilesBusiness=targetDirectoryBusiness.listFiles();

       */

        Arrays.sort(allFiles,(o1, o2) -> {

            if (o1.lastModified()<o2.lastModified()) return -1;
            else if (o1.lastModified()>o2.lastModified()) return 1;
            else return 0;

        });

        for(int i=0;i<allFiles.length;i++){

            File file=allFiles[i];

            if (Uri.fromFile(file).toString().endsWith(".mp4")){

                model=new WhatsaAppModel("Whats "+i,
                        file.getPath(),
                        allFiles[i].getAbsolutePath(),
                        Uri.fromFile(file));

             list.add(model);
            }
        }


     /*   Arrays.sort(allFilesBusiness,(o1, o2) -> {

            if (o1.lastModified()<o2.lastModified()) return -1;
            else if (o1.lastModified()>o2.lastModified()) return 1;
            else return 0;

        });

        for(int i=0;i<allFilesBusiness.length;i++){

            File file=allFilesBusiness[i];

            if (Uri.fromFile(file).toString().endsWith(".mp4")){

                model=new WhatsaAppModel("Whats "+i,
                        file.getPath(),
                        allFiles[i].getAbsolutePath(),
                        Uri.fromFile(file));

                list.add(model);
            }
        }

      */

        whatsAppAdapter=new WhatsAppAdapter(getContext(),list);
        recyclerView.setAdapter(whatsAppAdapter);
    }


}
