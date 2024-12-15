package com.example.myapplication.ui.antonim;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Kosakata;
import com.example.myapplication.model.LawanKata;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.KosakataService;
import com.example.myapplication.service.LawanKataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AntonimViewModel extends ViewModel {
    private  MutableLiveData<List<LawanKata>> listMutableLiveData;

    public LiveData<List<LawanKata>> getLawanKata(){
        if (listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            loadLawanKata();
        }
        return listMutableLiveData;
    }

    private void loadLawanKata() {

        LawanKataService lawanKataService = RetrofitClient.getRetrofitInstance().create(LawanKataService.class);
        Call<List<LawanKata>> call = lawanKataService.getAllLawanKata();
        call.enqueue(new Callback<List<LawanKata>>() {
            @Override
            public void onResponse(Call<List<LawanKata>> call, Response<List<LawanKata>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<LawanKata>> call, Throwable t) {

            }
        });

    }
}