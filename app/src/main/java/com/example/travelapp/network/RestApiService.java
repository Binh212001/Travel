package com.example.travelapp.network;

import com.example.travelapp.model.Book;
import com.example.travelapp.model.Booking;
import com.example.travelapp.model.Destination;
import com.example.travelapp.model.Hotel;
import com.example.travelapp.model.Restaurant;
import com.example.travelapp.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApiService {
    @GET("destination/all")
    Call<Destination> getAllDestination();

    @GET("destination/get/{id}")
    Call<Destination.Data> getDestinationById(@Path("id") String id);
    @GET("destination/get/name/{name}")
    Call<Destination> getDestinationByName(@Path("name") String name);
    @GET("hotel/get/{id}")
    Call<Hotel.Data> getHotelById(@Path("id") String id);
    @GET("restaurant/get/{id}")
    Call<Restaurant.Data> getRstById(@Path("id") String id);
    @GET("hotel/all")
    Call<Hotel> getAllHotel();


    @Multipart
    @POST("destination/create")
    Call<Destination.Data> createDestination(
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("province") RequestBody province,
            @Part("district") RequestBody district,
            @Part("type") RequestBody type,
            @Part MultipartBody.Part image
    );
    @Multipart
    @POST("hotel/create")
    Call<Hotel.Data> createHotel(
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("province") RequestBody province,
            @Part("district") RequestBody district,
            @Part("type") RequestBody type,
            @Part("price") RequestBody price,
            @Part MultipartBody.Part image
    );


    @POST("booking/create")
    Call<Book> createBooking(
            @Body   Book booking
    );
    @Multipart
    @POST("restaurant/create")
    Call<Restaurant.Data> createRst(
            @Part("name") RequestBody name,
            @Part("description") RequestBody description,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part image
    );
    @POST("user/register")
    Call<User> register(
            @Body User user
    );
    @POST("user/login")
    Call<User> login(
            @Body User user
    );
    @GET("restaurant/all")
    Call<Restaurant> getAllRst();
}
