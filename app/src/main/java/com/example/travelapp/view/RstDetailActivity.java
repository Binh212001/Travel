package com.example.travelapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;
import com.example.travelapp.model.Destination;
import com.example.travelapp.model.Restaurant;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RstDetailActivity extends AppCompatActivity  {

    TextView rstDetailName , rstDetailAddress, rstDetailDescription;
    ImageView rstDetailImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

       rstDetailName = findViewById(R.id.rstDetailName);
       rstDetailAddress = findViewById(R.id.rstDetailAddress);
       rstDetailDescription = findViewById(R.id.rstDetailDescription);
       rstDetailImage = findViewById(R.id.rstDetailImage);



        RestApiService apiService = RetrofitInstance.getApiService();
        Call<Restaurant.Data> call = apiService.getRstById(id);
        call.enqueue(new Callback<Restaurant.Data>() {
            @Override
            public void onResponse(Call<Restaurant.Data> call, Response<Restaurant.Data> response) {
               rstDetailName.setText(response.body().getName());
               rstDetailAddress.setText(response.body().getAddress());
               rstDetailDescription.setText(response.body().getDescription());
                String imageUrl = "https://trimmap-ohte.onrender.com/img/"+response.body().getImage();
                Picasso.get().load(imageUrl).into(rstDetailImage);

            }
            @Override
            public void onFailure(Call<Restaurant.Data> call, Throwable t) {
            }
        });


        // đổi màu cho các icon mỗi khi click vào
        // bắt sự kiện khi click các item trong navigationBar_menu
    }


}