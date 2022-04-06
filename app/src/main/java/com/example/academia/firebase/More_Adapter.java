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
import com.example.academia.Fire_Models.More_model;
import com.example.academia.R;

import java.util.ArrayList;

public class More_Adapter extends RecyclerView.Adapter<More_Adapter.Viewholder> {
    private Context mContext_vh;
    private ArrayList<More_model> more_models_list;

    public More_Adapter(Context mContext_vh, ArrayList<More_model> more_models_list) {
        this.mContext_vh = mContext_vh;
        this.more_models_list = more_models_list;
    }

    @NonNull
    @Override
    public More_Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_recycler, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull More_Adapter.Viewholder holder, int position) {
        holder.title_rm.setText(more_models_list.get(position).getTitle());
        holder.descrip_rm.setText(more_models_list.get(position).getDes());

        Glide.with(mContext_vh).load(more_models_list.get(position).getImage()).centerCrop().into(holder.image_rm);


    }

    @Override
    public int getItemCount() {
        return more_models_list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title_rm, descrip_rm;
        ImageView image_rm;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title_rm = itemView.findViewById(R.id.title_vh);
            descrip_rm = itemView.findViewById(R.id.descripe_vh);
            image_rm = itemView.findViewById(R.id.image_vh);
        }
    }

}
