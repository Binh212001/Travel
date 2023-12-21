package com.example.travelapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance3rd {
    private static Retrofit retrofit = null;
    public static RestApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl("https://provinces.open-api.vn/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    //checking authentication.
                    .build();
        }
        return retrofit.create(RestApiService.class);
    }
}
