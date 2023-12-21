package com.example.travelapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelapp.R;
import com.example.travelapp.adapter.DestinationAdapter;
import com.example.travelapp.adapter.HoltelAdapter;
import com.example.travelapp.adapter.OnItemClickListener;
import com.example.travelapp.model.Destination;
import com.example.travelapp.model.Hotel;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelFragment extends Fragment {

    RecyclerView recyclerView;
    HoltelAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_hotel,container,false);

        recyclerView = view.findViewById(R.id.data_hotel);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        openViewAddHotel(view);
        fetchListHotel();
        return view;
    }


    public  void  openViewAddHotel(View view){
        Button openViewHotel = view.findViewById(R.id.openViewHotel);

        // Now you can work with the Button as needed
        openViewHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddHotel.class);
                startActivity(intent);
            }
        });
    }

    private  void  fetchListHotel(){
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<Hotel> call = apiService.getAllHotel();
        call.enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                adapter = new HoltelAdapter(getActivity(),response.body().getData(),(OnItemClickListener) requireActivity());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
//                System.out.println("sdhhgfdhs");
            }
        });

    }

}
