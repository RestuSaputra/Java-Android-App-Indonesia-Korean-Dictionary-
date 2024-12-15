package com.example.myapplication.ui.latihan;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.QuizAdapter;
import com.example.myapplication.databinding.FragmentLatihanBinding;
import com.example.myapplication.model.Quiz;

import java.util.ArrayList;
import java.util.List;


public class LatihanFragment extends Fragment {

    private FragmentLatihanBinding binding;
    private RecyclerView quizRecyclerview;
    private QuizAdapter quizAdapter;
    private List<Quiz> quizList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_latihan, container,false);
        LatihanViewModel latihanViewModel =new ViewModelProvider(this).get(LatihanViewModel.class);

        quizRecyclerview = view.findViewById(R.id.latihan_list_recyclerview);
        quizRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        quizAdapter = new QuizAdapter(quizList, getContext());
        quizRecyclerview.setAdapter(quizAdapter);

        latihanViewModel.getQuizzes().observe(getViewLifecycleOwner(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                quizAdapter.updateQuizList(quizzes);
            }
        });
        return view;
    }

}