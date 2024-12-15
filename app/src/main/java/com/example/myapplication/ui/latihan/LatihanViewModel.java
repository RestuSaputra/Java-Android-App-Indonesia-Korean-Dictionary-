package com.example.myapplication.ui.latihan;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Quiz;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.QuizService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatihanViewModel extends ViewModel {

    private MutableLiveData<List<Quiz>> listMutableLiveData;



    public LiveData<List<Quiz>> getQuizzes() {
        if (listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            loadQuizess();
        }
        return listMutableLiveData;
    }

    private void loadQuizess() {
        QuizService quizService = RetrofitClient.getRetrofitInstance().create(QuizService.class);
        Call<List<Quiz>> call = quizService.getAllQuizzes();
        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                if (response.isSuccessful() && response.body() != null){
                    listMutableLiveData.setValue(response.body());
                    Log.d("Quiz", "berhasil ");
                }else {
                    Log.d("Quiz", "No item found: ");
                }
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                Log.e("Quiz", "API call failed", t);
            }
        });
    }
}