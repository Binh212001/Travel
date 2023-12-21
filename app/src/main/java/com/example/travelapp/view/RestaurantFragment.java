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
import com.example.travelapp.adapter.RestaurantAdapter;
import com.example.travelapp.model.Destination;
import com.example.travelapp.model.Restaurant;
import com.example.travelapp.network.RestApiService;
import com.example.travelapp.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantFragment extends Fragment {
    RecyclerView recyclerView;
    RestaurantAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_restaurant,container,false);

        recyclerView = view.findViewById(R.id.data_restaurant);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        openViewAddRst(view);
        fetchListRst();
        return view;

    }


    public  void openViewAddRst (View view){
        Button openViewRst = view.findViewById(R.id.openAddResView);

        // Now you can work with the Button as needed
        openViewRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddRst.class);
                startActivity(intent);
            }
        });
    }


    private  void  fetchListRst(){
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<Restaurant> call = apiService.getAllRst();
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                adapter = new RestaurantAdapter(getActivity(),response.body().getData(),(OnItemClickListener) requireActivity());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
            }
        });

    }
}
