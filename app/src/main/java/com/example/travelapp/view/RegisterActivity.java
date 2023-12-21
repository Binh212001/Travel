package com.example.travelapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;
import com.example.travelapp.model.Hotel;
import com.example.travelapp.model.User;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity  {

    EditText username , password , confirmPassword ,  firstName , lastName , phoneNumber;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

    }

    public  void  init(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        confirmPassword = findViewById(R.id.confirmPassword);
        phoneNumber = findViewById(R.id.phoneNumber);
        signUp = findViewById(R.id.signUp);

        register();

    }

    public  void  register(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = username.getText().toString();
                String txtPass = password.getText().toString();
                String phone  = phoneNumber.getText().toString();
                String txtFirstName = firstName.getText().toString();
                String txtLastName = lastName.getText().toString();
                String txtConfirmPass = confirmPassword.getText().toString();

                validate(txtUsername , txtPass,txtFirstName,txtLastName , phone, txtConfirmPass);
                saveData(txtUsername , txtPass,txtFirstName,txtLastName , phone);

            }
        });
    }

    public void  saveData(String txtUsername ,String txtPass,String txtFirstName,String txtLastName ,String phone){
        RestApiService apiService = RetrofitInstance.getApiService();
        User user = new User(txtUsername, txtFirstName,txtLastName,phone,txtPass);
        Call<User> call = apiService.register(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() ==400){
                    Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tai rồi", Toast.LENGTH_LONG).show();

                }else {

                Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Lỗi Server", Toast.LENGTH_LONG).show();

            }
        });

    }

    public  void validate(String txtUsername ,String txtPass,String txtFirstName,String txtLastName ,String phone,String txtConfirmPass){
            if(txtUsername.isEmpty()==true || txtPass.isEmpty()==true||txtFirstName.isEmpty()==true||txtLastName.isEmpty()==true || phone.isEmpty()==true|| txtConfirmPass.isEmpty()==true){
                Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy dủ thông tin", Toast.LENGTH_LONG).show();
            } else if (txtPass.equals(confirmPassword) ==false) {
                Toast.makeText(RegisterActivity.this, "Mật khẩu và xác nhận mật khẩu không khớp", Toast.LENGTH_LONG).show();
            }
    }




    public void onItemClickRestaurant() {
        // Handle item click, e.g., open detailed view or perform an action
        Intent intent = new Intent(this, RstDetailActivity.class);
        startActivity(intent);
    }
}