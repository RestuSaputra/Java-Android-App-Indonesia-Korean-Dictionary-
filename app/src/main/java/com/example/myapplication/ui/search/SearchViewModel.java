package com.example.myapplication.ui.search;



import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.adapter.KosakataAdapter;
import com.example.myapplication.model.Kosakata;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.KosakataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<List<Kosakata>> listMutableLiveData = new MutableLiveData<>();
    private KosakataService kosakataService;
    private KosakataAdapter kosakataAdapter;

    public SearchViewModel(){
        kosakataService= RetrofitClient.getRetrofitInstance().create(KosakataService.class);
    }

    public LiveData<List<Kosakata>> getKosakataLiveData(){
        return listMutableLiveData;
    }

    public void searchKosakata(String kata){
        kosakataService.searchKosakata(kata).enqueue(new Callback<List<Kosakata>>() {
            @Override
            public void onResponse(Call<List<Kosakata>> call, Response<List<Kosakata>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Kosakata> kosakatas = response.body();
                    if (!kosakatas.isEmpty()){
                        listMutableLiveData.setValue(kosakatas);
                    }else {
                        Log.d("Search", "No item found: ");
                        listMutableLiveData.setValue(new ArrayList<>());
                    }

                }else {
                    Log.e("Search", "Response unsuccessfull or empty");
                }
            }

            @Override
            public void onFailure(Call<List<Kosakata>> call, Throwable t) {
                Log.e("search", "API call failed", t);
            }
        });
    }
}

