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

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    DestinationAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search,container,false);

        recyclerView = view.findViewById(R.id.data_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        searchDes();
        return view;
    }



    private  void  searchDes(){

        Bundle args = getArguments();
        String value = args.getString("keyword");
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<Destination> call = apiService.getDestinationByName(value);
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                adapter = new DestinationAdapter(getActivity(),response.body().getData(),(OnItemClickListener) requireActivity());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
            }
        });

    }

}
