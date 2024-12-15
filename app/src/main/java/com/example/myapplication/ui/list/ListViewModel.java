package com.example.myapplication.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Kosakata;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.KosakataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    private MutableLiveData<List<Kosakata>> listMutableLiveData ;

    public LiveData<List<Kosakata>> getKosakatas(){
        if (listMutableLiveData == null){
            listMutableLiveData = new MutableLiveData<>();
            loadKosakata();
        }
        return listMutableLiveData;
    }

    private void loadKosakata() {
        KosakataService kosakataService = RetrofitClient.getRetrofitInstance().create(KosakataService.class);
        Call<List<Kosakata>> call = kosakataService.getAllKosakatas();
        call.enqueue(new Callback<List<Kosakata>>() {
            @Override
            public void onResponse(Call<List<Kosakata>> call, Response<List<Kosakata>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Kosakata>> call, Throwable t) {

            }
        });
    }
}



















