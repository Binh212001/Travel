package com.example.travelapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.model.Restaurant;
import com.example.travelapp.viewHoder.RestaurantViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private List<Restaurant.Data> restaurantList;
    private Context context;

    private LayoutInflater inflater;
    private OnItemClickListener itemClickListener;

    public RestaurantAdapter(Context context, List<Restaurant.Data> restaurantList , OnItemClickListener itemClickListener) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.inflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rcv_restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        final Restaurant.Data item = restaurantList.get(position);

//        holder..setText(item.getImage());
        holder.editTextRestaurantName.setText(item.getName());
//        holder.textViewProvince.setText(item.getProvince());
        String imageUrl = "https://trimmap-ohte.onrender.com/img/"+item.getImage();
        Picasso.get().load(imageUrl).into(holder.imageRestaurant);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClickRestaurant(item.getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

}
