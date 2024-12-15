package com.example.myapplication.ui.hangeul;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Hangeul;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.HangeulService;
import com.example.myapplication.view.QuestionActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HangeulViewModel extends ViewModel {

    private MutableLiveData<List<Hangeul>> konsonanTunggalItems = new MutableLiveData<>();
    private MutableLiveData<List<Hangeul>> vokalTunggalItems = new MutableLiveData<>();
    private MutableLiveData<List<Hangeul>> konsonanGandaItems= new MutableLiveData<>();
    private MutableLiveData<List<Hangeul>> vokalGandaItems = new MutableLiveData<>();
    private MutableLiveData<Hangeul> selectedHangeul = new MutableLiveData<Hangeul>();

    public HangeulViewModel(){
        loadHangeuls();
    }

    public LiveData<List<Hangeul>> getKonsonanTunggalItem(){
        return konsonanTunggalItems;
    }

    public LiveData<List<Hangeul>> getVokalTunggalItem(){
        return vokalTunggalItems;
    }

    public LiveData<List<Hangeul>> getKonsonanGandaItem(){
        return konsonanGandaItems;
    }

    public LiveData<List<Hangeul>> getVokalGandaItem(){
        return vokalGandaItems;
    }

    public MutableLiveData<Hangeul> getSelectedHangeul(){
        return selectedHangeul;
    }

    public void selectHangeul(Hangeul hangeul){
        selectedHangeul.setValue(hangeul);
    }

    private void loadHangeuls() {

        HangeulService hangeulService = RetrofitClient.getRetrofitInstance().create(HangeulService.class);

        hangeulService.getAllKonsonanTunggal().enqueue(new Callback<List<Hangeul>>() {
            @Override
            public void onResponse(Call<List<Hangeul>> call, Response<List<Hangeul>> response) {
                if (response.isSuccessful()){
                    konsonanTunggalItems.setValue(response.body());
                    Log.d(TAG, "fetchDataFromServer: Data fetched successfully");
                }
            }

            @Override
            public void onFailure(Call<List<Hangeul>> call, Throwable t) {
                Log.e(TAG, "fetchDataFromServer: Failed to fetch data");
            }
        });

        hangeulService.getAllVokalTunggal().enqueue(new Callback<List<Hangeul>>() {
            @Override
            public void onResponse(Call<List<Hangeul>> call, Response<List<Hangeul>> response) {
                if (response.isSuccessful()){
                    vokalTunggalItems.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Hangeul>> call, Throwable t) {

            }
        });

        hangeulService.getAllKonsonanGanda().enqueue(new Callback<List<Hangeul>>() {
            @Override
            public void onResponse(Call<List<Hangeul>> call, Response<List<Hangeul>> response) {
                if (response.isSuccessful()){
                    konsonanGandaItems.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Hangeul>> call, Throwable t) {

            }
        });

        hangeulService.getAllVokalGanda().enqueue(new Callback<List<Hangeul>>() {
            @Override
            public void onResponse(Call<List<Hangeul>> call, Response<List<Hangeul>> response) {
                if (response.isSuccessful()){
                    vokalGandaItems.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Hangeul>> call, Throwable t) {

            }
        });

    }

}