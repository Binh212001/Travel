package com.example.travelapp.viewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;


public  class RestaurantViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageRestaurant;
    public    TextView editTextRestaurantName;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            editTextRestaurantName = itemView.findViewById(R.id.editTextRestaurantName);
            imageRestaurant = itemView.findViewById(R.id.imageRestaurant);
//            editTextRestaurantType = itemView.findViewById(R.id.editTextRestaurantType);
        }
}