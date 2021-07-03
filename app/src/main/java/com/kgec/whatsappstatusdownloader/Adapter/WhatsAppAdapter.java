package com.kgec.whatsappstatusdownloader.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.kgec.whatsappstatusdownloader.R;
import com.kgec.whatsappstatusdownloader.model.Utill;
import com.kgec.whatsappstatusdownloader.model.WhatsaAppModel;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class WhatsAppAdapter extends RecyclerView.Adapter<WhatsAppAdapter.viewHolder>{

    private Context mContext;
    private List<WhatsaAppModel>list;
     String saveFilePath=Utill.RootFileDirectory+"/";

    public WhatsAppAdapter(Context mContext,List<WhatsaAppModel>list){

        this.mContext=mContext;
        this.list=list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.whatsapp_item_layout,parent,false);
        return new WhatsAppAdapter.viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final WhatsaAppModel item=list.get(position);

        if (item.getUri().toString().endsWith(".mp4")){

            holder.play_image.setVisibility(View.VISIBLE);
        }
        else {
            holder.play_image.setVisibility(View.GONE);

        }

        Glide.with(mContext).load(item.getPath()).into(holder.whatsapp_image);

        holder.download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create the folder
                Utill.createFolder();

                // Take the path

                final String path=item.getPath();

                // Create file

               final File file=new File(path);

                // Create a destination file where the file will be saved

                final File destFile=new File(saveFilePath);

                // use Commons io(FileUtills) as file to be stored at destfile

                try {

                    FileUtils.copyFileToDirectory(file,destFile);

                } catch (IOException e) {

                    e.printStackTrace();
                }

                Toast.makeText(mContext, "Saved  to :  "+saveFilePath, Toast.LENGTH_SHORT).show();
            }


        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView whatsapp_image,play_image;
        Button download_btn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            whatsapp_image=itemView.findViewById(R.id.whatsapp_status_image);
            play_image=itemView.findViewById(R.id.whatsaap_play_image);
            download_btn=itemView.findViewById(R.id.download_btn);


        }
    }

}
