package com.example.travelapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.travelapp.R;
import com.example.travelapp.adapter.DestinationAdapter;
import com.example.travelapp.adapter.OnItemClickListener;
import com.example.travelapp.model.Destination;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationDetailActivity extends AppCompatActivity  {

    TextView locationDetailName , locationDetailAddress, locationDetailDescription;
    ImageView locationDetailImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        locationDetailName = findViewById(R.id.locationDetailName);
        locationDetailAddress = findViewById(R.id.locationDetailAddress);
        locationDetailDescription = findViewById(R.id.locationDetailDescription);
        locationDetailImage = findViewById(R.id.LocationDetailImage);



        RestApiService apiService = RetrofitInstance.getApiService();
        Call<Destination.Data> call = apiService.getDestinationById(id);
        call.enqueue(new Callback<Destination.Data>() {
            @Override
            public void onResponse(Call<Destination.Data> call, Response<Destination.Data> response) {
               locationDetailName.setText(response.body().getName());
               locationDetailAddress.setText(response.body().getDistrict()+" - " + response.body().getProvince());
               locationDetailDescription.setText(response.body().getDescription());
                String imageUrl = "https://trimmap-ohte.onrender.com/img/"+response.body().getImage();
                Picasso.get().load(imageUrl).into(locationDetailImage);

            }
            @Override
            public void onFailure(Call<Destination.Data> call, Throwable t) {
            }
        });


        // đổi màu cho các icon mỗi khi click vào
        // bắt sự kiện khi click các item trong navigationBar_menu
    }


}