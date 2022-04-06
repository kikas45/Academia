package com.example.academia.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.academia.Fire_Models.Reconmmended_model;
import com.example.academia.R;

import java.util.ArrayList;


public class Recomended_Adapter extends RecyclerView.Adapter<Recomended_Adapter.Viewholder> {
    private Context mContext;
    private ArrayList<Reconmmended_model> recon_list;

    public Recomended_Adapter(Context mContext, ArrayList<Reconmmended_model> recon_list) {
        this.mContext = mContext;
        this.recon_list = recon_list;
    }

    @NonNull
    @Override
    public Recomended_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reconmended_item, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recomended_Adapter.Viewholder holder, int position) {
        holder.title_rm.setText(recon_list.get(position).getTitle());
        holder.descrip_rm.setText(recon_list.get(position).getDes());

        Glide.with(mContext).load(recon_list.get(position).getImage()).centerCrop().into(holder.image_rm);


    }

    @Override
    public int getItemCount() {
        return recon_list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title_rm, descrip_rm;
        ImageView image_rm;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title_rm = itemView.findViewById(R.id.title_rm);
            descrip_rm = itemView.findViewById(R.id.descripe_rm);
            image_rm = itemView.findViewById(R.id.image_rm);
        }
    }

}
