package com.example.travelapp.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;
import com.example.travelapp.model.Book;
import com.example.travelapp.model.Booking;
import com.example.travelapp.model.Destination;
import com.example.travelapp.model.Hotel;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailActivity extends AppCompatActivity  {

    TextView hotelDetailName , hotelDetailAddress, hotelDetailDescription, hotelDetailPrice  ;
    ImageView hotelDetailImage ;


    EditText dateFrom , dateTo , note , peopleCount , fullName;
    Button booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        hotelDetailName = findViewById(R.id.hotelDetailName);
        hotelDetailAddress = findViewById(R.id.hotelDetailAddress);
        hotelDetailPrice = findViewById(R.id.hotelDetailPrice);
        hotelDetailDescription = findViewById(R.id.hotelDetailDescription);
        hotelDetailImage = findViewById(R.id.hotelDetailImage);
        dateFrom= findViewById(R.id.dateFrom);
        dateTo= findViewById(R.id.dateTo);
        note= findViewById(R.id.note);
        peopleCount= findViewById(R.id.peopleCount);
        fullName= findViewById(R.id.fullName);
        booking= findViewById(R.id.booking);



        findViewById(R.id.booking).setOnClickListener(v -> saveData());

        RestApiService apiService = RetrofitInstance.getApiService();
        Call<Hotel.Data> call = apiService.getHotelById(id);
        call.enqueue(new Callback<Hotel.Data>() {
            @Override
            public void onResponse(Call<Hotel.Data> call, Response<Hotel.Data> response) {
               hotelDetailName.setText(response.body().getName());
               hotelDetailAddress.setText(response.body().getDistrict()+" - " + response.body().getProvince());
               hotelDetailDescription.setText(response.body().getDescription());
               hotelDetailPrice.setText(response.body().getPrice());
                String imageUrl = "https://trimmap-ohte.onrender.com/img/"+response.body().getImage();
                Picasso.get().load(imageUrl).into(hotelDetailImage);

            }
            @Override
            public void onFailure(Call<Hotel.Data> call, Throwable t) {
                System.out.println("Gọi Api thất bại");
            }
        });
    }

    public  void  saveData(){
        String txtDateFrom= dateFrom.getText().toString();
        String txtDateTo= dateTo.getText().toString();

        String txtNote= note.getText().toString();
        String txtPeople= peopleCount.getText().toString();
        String txtFullName=fullName.getText().toString();
        booking= findViewById(R.id.booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( txtDateTo.isEmpty()==false && txtDateFrom.isEmpty()==false  && !txtPeople.isEmpty() && txtFullName.isEmpty()==false){
//                    Float people = Float.parseFloat(txtPeople);
                    Book data = new Book("6580702ad6d6495218917538","6580702ad6d6495218917538",txtFullName,txtDateFrom,txtDateTo,7);
                    RestApiService apiService = RetrofitInstance.getApiService();
                    Call<Book> call = apiService.createBooking(data);

                    call.enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            if ( response.body() != null) {
                                Toast.makeText(HotelDetailActivity.this, "Dặt phòng thành công", Toast.LENGTH_LONG).show();
//                                closeActivity();
                            } else {
                                Toast.makeText(HotelDetailActivity.this, "Dặt phòng thất bại", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            // Handle failure
                            Toast.makeText(HotelDetailActivity.this, "Server error", Toast.LENGTH_LONG).show();

                        }
                    });
                    closeActivity();
                }else {
                    Toast.makeText(HotelDetailActivity.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
                }
            }
        });
    }




    private  void closeActivity (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}