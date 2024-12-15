package com.example.myapplication.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.KosakataAdapter;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.model.Kosakata;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private RecyclerView listRecyclerView;
    private KosakataAdapter kosakataAdapter;
    private List<Kosakata> kosakataList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listRecyclerView = view.findViewById(R.id.list_recyclerview);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kosakataAdapter = new KosakataAdapter(kosakataList);
        listRecyclerView.setAdapter(kosakataAdapter);

        ListViewModel listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.getKosakatas().observe(getViewLifecycleOwner(), new Observer<List<Kosakata>>() {
            @Override
            public void onChanged(List<Kosakata> kosakatas) {
                kosakataAdapter.updateKosakataList(kosakatas);
            }
        });

        return view;
    }
}












