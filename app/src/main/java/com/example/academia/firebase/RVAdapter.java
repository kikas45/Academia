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
import com.example.academia.Fire_Models.Employee;
import com.example.academia.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Employee> list = new ArrayList<>();

    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Employee> emp)
    {
        list.addAll(emp);
    }


    // try check the old branch

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.more_recycler,parent,false);
        return new EmployeeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeVH vh = (EmployeeVH) holder;
        Employee emp = list.get(position);
        vh.title_rm.setText(emp.getTitle());
        vh.descrip_rm.setText(emp.getDes());
        Glide.with(context).load(emp.getImage()).centerCrop().into(vh.image_rm);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmployeeVH extends RecyclerView.ViewHolder {
        TextView title_rm, descrip_rm;
        ImageView image_rm;
        public EmployeeVH(@NonNull View itemView) {
            super(itemView);
            title_rm = itemView.findViewById(R.id.title_vh);
            descrip_rm = itemView.findViewById(R.id.descripe_vh);
            image_rm = itemView.findViewById(R.id.image_vh);
        }
    }
}