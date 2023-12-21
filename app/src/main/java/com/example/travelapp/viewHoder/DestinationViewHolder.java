package com.example.travelapp.viewHoder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;

public  class DestinationViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewProvince;
        public TextView textViewDistrict;
        public TextView textViewDescription;
        public TextView textViewType;
        public ImageView imageView;

        public DestinationViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.location_name);
            textViewType = itemView.findViewById(R.id.location_type);
            imageView = itemView.findViewById(R.id.bg_location_image);
            textViewProvince = itemView.findViewById(R.id.location_province);
        }
    }