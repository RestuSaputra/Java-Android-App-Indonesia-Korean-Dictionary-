package com.example.myapplication.ui.antonim;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LawanKataAdapter;
import com.example.myapplication.model.LawanKata;

import java.util.ArrayList;
import java.util.List;


public class AntonimFragment extends Fragment {

    private RecyclerView antonimRecycler;
    private LawanKataAdapter lawanKataAdapter;
    private List<LawanKata> lawanKataList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_antonim, container, false);

        antonimRecycler = view.findViewById(R.id.antonim_recyclerview);
        antonimRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        lawanKataAdapter = new LawanKataAdapter(lawanKataList);
        antonimRecycler.setAdapter(lawanKataAdapter);

        AntonimViewModel antonimViewModel = new ViewModelProvider(this).get(AntonimViewModel.class);
        antonimViewModel.getLawanKata().observe(getViewLifecycleOwner(), new Observer<List<LawanKata>>() {
            @Override
            public void onChanged(List<LawanKata> lawanKatas) {
                lawanKataAdapter.lawanKataUpdate(lawanKatas);
            }
        });

        return view;
    }

}