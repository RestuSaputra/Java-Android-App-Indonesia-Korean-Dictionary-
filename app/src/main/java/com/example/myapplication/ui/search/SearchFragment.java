package com.example.myapplication.ui.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.myapplication.databinding.FragmentSearchBinding;
import com.example.myapplication.model.Kosakata;
import com.example.myapplication.service.KosakataService;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView searchRecyclerView;
    private KosakataAdapter kosakataAdapter;
    private EditText searchEditText;
    private Button searchButton;
    private List<Kosakata> kosakataList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        searchRecyclerView = view.findViewById(R.id.search_recycleview);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kosakataAdapter = new KosakataAdapter(kosakataList);
        searchRecyclerView.setAdapter(kosakataAdapter);

        searchEditText = view.findViewById(R.id.et_search);
        searchButton = view.findViewById(R.id.bt_search);

        searchViewModel.getKosakataLiveData().observe(getViewLifecycleOwner(), new Observer<List<Kosakata>>() {
            @Override
            public void onChanged(List<Kosakata> kosakatas) {
                if (kosakatas != null && !kosakatas.isEmpty()){
                    kosakataAdapter.updateKosakataList(kosakatas);
                    Toast.makeText(getContext(),"berhasil bro", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "No Kosakata is found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchButton.setOnClickListener(v -> {
            String kata = searchEditText.getText().toString();
            if (!kata.isEmpty()){
                searchViewModel.searchKosakata(kata);
            }else {
                Toast.makeText(getContext(), "Please enter a search vocabulary", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

