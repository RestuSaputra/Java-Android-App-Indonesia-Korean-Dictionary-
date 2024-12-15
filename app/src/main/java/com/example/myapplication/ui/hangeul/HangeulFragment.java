package com.example.myapplication.ui.hangeul;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.HangeulAdapter;
import com.example.myapplication.view.HangeulDetail;

import java.util.ArrayList;
import java.util.List;

public class HangeulFragment extends Fragment {

    private RecyclerView ktRecyclerview, vtRecyclerview, kgRecyclerview, vgRecyclerview;
    private HangeulAdapter ktAdapter, vtAdapter, kgAdapter, vgAdapter;
    private HangeulViewModel hangeulViewModel;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hangeul, container, false);

        ktRecyclerview = view.findViewById(R.id.kt_rv);
        kgRecyclerview = view.findViewById(R.id.kg_rv);
        vtRecyclerview = view.findViewById(R.id.vt_rv);
        vgRecyclerview = view.findViewById(R.id.vg_rv);

        ktRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        kgRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        vtRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        vgRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        hangeulViewModel = new ViewModelProvider(requireActivity()).get(HangeulViewModel.class);


        hangeulViewModel.getKonsonanTunggalItem().observe(getViewLifecycleOwner(), hangeulList -> {
            ktAdapter = new HangeulAdapter(getContext(), hangeulList, hangeul -> {
                hangeulViewModel.selectHangeul(hangeul);
                loadDetailFragment();
            });
            ktRecyclerview.setAdapter(ktAdapter);
        });

        hangeulViewModel.getKonsonanGandaItem().observe(getViewLifecycleOwner(), hangeulList -> {
            kgAdapter = new HangeulAdapter(getContext(), hangeulList, hangeul -> {
                hangeulViewModel.selectHangeul(hangeul);
                loadDetailFragment();
            });
            kgRecyclerview.setAdapter(kgAdapter);
        });

        hangeulViewModel.getVokalTunggalItem().observe(getViewLifecycleOwner(), hangeulList -> {
            vtAdapter = new HangeulAdapter(getContext(), hangeulList, hangeul -> {
                hangeulViewModel.selectHangeul(hangeul);
                loadDetailFragment();
            });
            vtRecyclerview.setAdapter(vtAdapter);
        });

        hangeulViewModel.getVokalGandaItem().observe(getViewLifecycleOwner(), hangeulList -> {
            vgAdapter = new HangeulAdapter(getContext(), hangeulList, hangeul -> {
                hangeulViewModel.selectHangeul(hangeul);
                loadDetailFragment();
            });
            vgRecyclerview.setAdapter(vgAdapter);
        });

        return view;
    }

    private void loadDetailFragment() {
        HangeulDetail hangeulDetail = new HangeulDetail();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_hangeulTo,hangeulDetail)
                .addToBackStack(null)
                .commit();
    }

}