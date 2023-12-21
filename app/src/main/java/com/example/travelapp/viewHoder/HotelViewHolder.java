package com.example.travelapp.viewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;

public  class HotelViewHolder extends RecyclerView.ViewHolder {
        public TextView textHotelName , txtHotelAddress ,txtHotelPrice ;

        public ImageView imageView;

        public HotelViewHolder(View itemView) {
            super(itemView);
            txtHotelAddress = itemView.findViewById(R.id.txtHotelAddress);
            textHotelName = itemView.findViewById(R.id.txtHotelName);
            imageView = itemView.findViewById(R.id.bg_image_hotel);
            txtHotelPrice = itemView.findViewById(R.id.txtHotelPrice);
        }
    }