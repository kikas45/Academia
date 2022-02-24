package com.example.academia.adapter;


import com.example.academia.MainActivity2;
import com.example.academia.R;
import com.example.academia.ViewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderViewAdapter;

import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    // creating a variable for
    // context and array list.
    private Context context;
    private List<SliderData> mSliderItems = new ArrayList<>();

    // constructor for our adapter class.
    public SliderAdapter(Context context, List<SliderData> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        // inside the on Create view holder method we are
        // inflating our layout file which we have created.
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        // inside on bind view holder method we are
        // getting url of image from our modal class
        // and setting that url for image inside our
        // image view using Picasso.
        final SliderData sliderItem = mSliderItems.get(position);
      viewHolder.text_slider.setText(sliderItem.getName());

        viewHolder.text_slider.setTextSize(16);;
        viewHolder.text_slider.setTextColor(Color.WHITE);
        Picasso.get().load(sliderItem.getImgUrl()).into(viewHolder.imageView);


/*
        FirebaseDatabase.getInstance()
                .getReference().child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            SliderData user = snapshot.getValue(SliderData.class);
                            user.getImgUrl();
                            viewHolder.itemView.getContext();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
*/




        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///SliderData clickedItem = mSliderItems.get(position);
               // Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,MainActivity2.class);

        if (position == 0){
            intent.putExtra("key", "   Home   "+ sliderItem.getName());
            context.startActivity(intent);

        }
        else if (position ==1){
            intent.putExtra("key", "   News   "+ position);
            context.startActivity(intent);

        }
        else if (position == 2){
            intent.putExtra("key", "   Banking   "+ position);
            context.startActivity(intent);
        }

        else if (position == 3){
            intent.putExtra("key", "   Contacts   "+ position);
            context.startActivity(intent);
        }
        else if (position == 4){
            intent.putExtra("key", "   More   "+ position);
            context.startActivity(intent);
        }

        else if (position == 5){
            intent.putExtra("key", "   PEACE   "+ position);
            context.startActivity(intent);

        }    else if (position == 6){
            intent.putExtra("key", "   PEACE   "+ position);
            context.startActivity(intent);

        }  else if (position == 7){
            intent.putExtra("key", "   PEACE   "+ position);
            context.startActivity(intent);
        }


            }







           //    ClickedListener.onPictureClicked(getItemPosition(sliderItem));




                // Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();



             //  for (int i = -1; i < position; i++) {
                  //  Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();


               //    Intent intent = new Intent( context.getApplicationContext(), MainActivity2.class);
                  // getItemPosition(intent);


                  ///  Intent intent = new Intent(getItemPosition(position) )

                   // getItemPosition(position) = Intent(getItemPosition(position), ViewActivity.class);


             //  }







        });
    }

    @Override
    public int getCount() {
        // returning the size of our array list.
        return mSliderItems.size();
    }

    // view holder class for initializing our view holder.
    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        // variables for our view and image view.
        View itemView;
        ImageView imageView;
        TextView text_slider;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            // initializing our views.
            imageView = itemView.findViewById(R.id.idIVimage);
            text_slider = itemView.findViewById(R.id.text_slider);
            this.itemView = itemView;
        }



    }




}
