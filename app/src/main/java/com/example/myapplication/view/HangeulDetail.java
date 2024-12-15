package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.hangeul.HangeulViewModel;


public class HangeulDetail extends Fragment {

    private TextView textAksara, textBaca, textKeterangan, textPensatu, textPendua;
    private HangeulViewModel hangeulViewModel;

    public HangeulDetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hangeul_detail, container, false);

        textAksara = view.findViewById(R.id.txt_aksara);
        textBaca = view.findViewById(R.id.txt_carabaca);
        textKeterangan = view.findViewById(R.id.txt_keterangan);
        textPensatu = view.findViewById(R.id.txt_pensatu);
        textPendua = view.findViewById(R.id.txt_pendua);

        hangeulViewModel = new ViewModelProvider(requireActivity()).get(HangeulViewModel.class);
        hangeulViewModel.getSelectedHangeul().observe(getViewLifecycleOwner(), hangeulList -> {
            textAksara.setText(hangeulList.getAksara());
            textBaca.setText(hangeulList.getCaraBaca());
            textKeterangan.setText(hangeulList.getKeterangan());
            textPensatu.setText(hangeulList.getPenjelasanSatu());
            textPendua.setText(hangeulList.getPenjelasanDua());
        });
        return view;
    }
}