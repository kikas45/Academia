package com.example.academia.firebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.academia.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String Tag = "RecylerView"; //dedugging
    private Context mContext;
    private ArrayList<Message> messagesList;

    public RecyclerAdapter(Context mContext, ArrayList<Message> messagesList) {
        this.mContext = mContext;
        this.messagesList = messagesList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messalist_item22, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //TextView


        holder.title.setText(messagesList.get(position).getTitle());
        holder.type.setText(messagesList.get(position).getType());

        Glide.with(mContext).load(messagesList.get(position).getIcon()).centerCrop().into(holder.icon);

     Glide.with(mContext)
                .load(messagesList.get(position).getImage()).centerCrop()
                .into(holder.imageView);

        holder.description.setText(messagesList.get(position).getDes());

       holder.date.setText(messagesList.get(position).getDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "working" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //widgets

      TextView title, type, description, date;
      ImageView imageView;
      ImageView icon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

         icon = itemView.findViewById(R.id.profile_image);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            imageView = itemView.findViewById(R.id.imageView);
           description = itemView.findViewById(R.id.description);
           date = itemView.findViewById(R.id.date);

        }
    }

/*
        public  String timeconversion (long value){
        String videoTime;

        int duration = (int) value;
        int hrs = (duration/3600000);
        int mns = (duration/60000) % 60000;
        int sec = duration % 60000 /1000;
        if (hrs > 0) {
            videoTime = String.format("%02d:%02d", hrs, mns, sec);
        }else {
            videoTime = String.format("%02d:%02d", mns, sec);
        }

        return  videoTime;
        }
*/


}



