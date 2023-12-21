package com.example.travelapp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;
import com.example.travelapp.Utils.RealPathUtil;
import com.example.travelapp.model.Destination;
import com.example.travelapp.model.Hotel;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHotel extends AppCompatActivity {

    ImageView hotelArrowBack ;
    Button backAddHotel , buttonAddImageHotel;



    TextView editTextHotelName, textHotelDescription, editTextHotelPrice;

    Spinner  spinnerHotelType ,spinnerHotelProvince, spinnerHotelDistrict;

    ImageView imageViewHotel;
    String name ,desc,district , province,type ,imageName,price ;

    Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editTextHotelName = findViewById(R.id.editTextHotelName);
        editTextHotelPrice  = findViewById(R.id.editTextHotelPrice);
        textHotelDescription = findViewById(R.id.editTextHotelDesc);
        spinnerHotelDistrict = findViewById(R.id.spinnerHotelDistrict);
        spinnerHotelProvince = findViewById(R.id.spinnerHotelProvince);
        spinnerHotelType = findViewById(R.id.spinnerHotelType);
        imageViewHotel   = findViewById(R.id.imageViewHotel);

        setContentView(R.layout.add_hotel);
//        onClickAddLocationBtn();
        innitSpinner();
        innitSpinnerDistrict();
        innitSpinnerProvince();
        onClickBackAddHotelArr();
        onClickBackAddHotelBtn();
        onClickAddImageHotelBtn();
    }

    public  void  saveDataHt(View v){
        editTextHotelName = findViewById(R.id.editTextHotelName);
        editTextHotelPrice  = findViewById(R.id.editTextHotelPrice);
        textHotelDescription = findViewById(R.id.editTextHotelDesc);
        spinnerHotelDistrict = findViewById(R.id.spinnerHotelDistrict);
        spinnerHotelProvince = findViewById(R.id.spinnerHotelProvince);
        spinnerHotelType = findViewById(R.id.spinnerHotelType);
        imageViewHotel   = findViewById(R.id.imageViewHotel);

        name =  editTextHotelName.getText().toString();
        price =  editTextHotelPrice.getText().toString();
        desc =  textHotelDescription.getText().toString();
        district =  spinnerHotelDistrict.getSelectedItem().toString();
        province =  spinnerHotelProvince.getSelectedItem().toString();
        type =  spinnerHotelType.getSelectedItem().toString();


        if(!name.isEmpty()&&! desc.isEmpty()&&!district.isEmpty()&&!province.isEmpty()&&!type.isEmpty()&&!imageName.isEmpty() ){
            RequestBody namePart = RequestBody.create(MediaType.parse("text/plain"), name);
            RequestBody descPart = RequestBody.create(MediaType.parse("text/plain"), desc);
            RequestBody pricePart = RequestBody.create(MediaType.parse("text/plain"), price);
            RequestBody provincePart = RequestBody.create(MediaType.parse("text/plain"), province);
            RequestBody districtPart = RequestBody.create(MediaType.parse("text/plain"), district);
            RequestBody typePart = RequestBody.create(MediaType.parse("text/plain"), type);
            String imageRealUri = RealPathUtil.getRealPath(this , imageUri);
            File avatarFile = new File(imageRealUri);
            RequestBody avatarRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), avatarFile);
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", avatarFile.getName(), avatarRequestBody);

            RestApiService apiService = RetrofitInstance.getApiService();
            Call<Hotel.Data> call = apiService.createHotel(namePart,descPart, provincePart, districtPart,typePart,pricePart ,imagePart);

            call.enqueue(new Callback<Hotel.Data>() {
                @Override
                public void onResponse(Call<Hotel.Data> call, Response<Hotel.Data> response) {
                    if ( response.body() != null) {
                        Toast.makeText(AddHotel.this, "Thêm khách sạn thành công", Toast.LENGTH_LONG).show();
                        closeActivity();
                    } else {
                        Toast.makeText(AddHotel.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Hotel.Data> call, Throwable t) {
                    // Handle failure
                    Toast.makeText(AddHotel.this, "Vui lòng điền đầy dủ thông tin shfgdhs", Toast.LENGTH_LONG).show();

                }
            });

        }else {
            Toast.makeText(AddHotel.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
        }
    }

    public void innitSpinner() {
        spinnerHotelType = findViewById(R.id.spinnerHotelType);
        List<String> spinnerData = new ArrayList<>();
        spinnerData.add("Vila");
        spinnerData.add("Biệt thự");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHotelType.setAdapter(adapter);

    }
    public void innitSpinnerProvince() {
        spinnerHotelProvince = findViewById(R.id.spinnerHotelProvince);
        List<String> provinceList = Arrays.asList(
                "Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Quảng Ninh", "Thái Bình",
                "Bắc Ninh", "Hải Dương", "Hà Nam", "Nam Định", "Ninh Bình", "Thanh Hóa",
                "Nghệ An", "Hà Tĩnh", "Quảng Bình", "Quảng Trị", "Thừa Thiên Huế", "Quảng Nam",
                "Quảng Ngãi", "Bình Định", "Phú Yên", "Khánh Hòa", "Ninh Thuận", "Bình Thuận",
                "Kon Tum", "Gia Lai", "Đắk Lắk", "Đắk Nông", "Lâm Đồng", "Bà Rịa - Vũng Tàu",
                "Bình Phước", "Bình Dương", "Đồng Nai", "Tây Ninh", "Long An", "Tiền Giang",
                "Bến Tre", "Trà Vinh", "Vĩnh Long", "Đồng Tháp", "An Giang", "Kiên Giang",
                "Cần Thơ", "Hậu Giang", "Sóc Trăng", "Bạc Liêu", "Cà Mau", "Lào Cai",
                "Yên Bái", "Lạng Sơn", "Quảng Ninh", "Bắc Giang", "Phú Thọ", "Vĩnh Phúc",
                "Bắc Ninh", "Hải Dương", "Hưng Yên", "Thái Bình", "Hà Nam", "Nam Định",
                "Ninh Bình", "Thanh Hóa"
                // Thêm các tỉnh thành khác tương ứng với 64 tỉnh thành Việt Nam
        );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinceList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHotelProvince.setAdapter(adapter);

    }


    public void innitSpinnerDistrict() {
        spinnerHotelDistrict = findViewById(R.id.spinnerHotelDistrict);
        List<String> districts = Arrays.asList(
                "Ba Đình", "Hoàn Kiếm", "Hai Bà Trưng", "Đống Đa", "Tây Hồ",
                "Cầu Giấy", "Thanh Xuân", "Hoàng Mai", "Long Biên", "Bắc Từ Liêm",
                "Nam Từ Liêm", "Hà Đông", "Thanh Trì", "Sơn Tây", "Ba Vì",
                "Phúc Thọ", "Thạch Thất", "Quốc Oai", "Chương Mỹ", "Đan Phượng",
                "Hoài Đức", "Thanh Oai", "Mỹ Đức", "Ứng Hòa", "Thường Tín", "Phú Xuyên",
                "Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7",
                "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Bình Tân",
                "Bình Thạnh", "Gò Vấp", "Phú Nhuận", "Tân Bình", "Tân Phú", "Thủ Đức",
                "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn", "Nhà Bè"
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHotelDistrict.setAdapter(adapter);

    }

    private void onClickBackAddHotelArr() {
        hotelArrowBack = findViewById(R.id.hotelArrowBack);
        hotelArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
    }
    private void onClickBackAddHotelBtn() {
        backAddHotel = findViewById(R.id.backAddHotel);
        backAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeActivity();
            }
        });
    }

    private void onClickAddImageHotelBtn() {
        buttonAddImageHotel = findViewById(R.id.buttonAddImageHotel);
        buttonAddImageHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        });
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        String[] projection = {MediaStore.Images.Media.DISPLAY_NAME};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            fileName = cursor.getString(columnIndex);
            cursor.close();
        }
        return fileName;
    }


    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            imageUri =selectedImageUri;
            imageName = getFileNameFromUri(selectedImageUri);
            imageViewHotel   = findViewById(R.id.imageViewHotel );
            imageViewHotel.setImageURI(selectedImageUri);
        }
    }

    private  void closeActivity (){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}