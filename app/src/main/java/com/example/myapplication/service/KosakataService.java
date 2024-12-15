package com.example.myapplication.service;

import com.example.myapplication.model.Kosakata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KosakataService {

    @GET("/api/kata")
    Call<List<Kosakata>> getAllKosakatas();

    @GET("api/kata/{id}")
    Call<Kosakata> getKosakatabyId(@Path("id") int id);

    @POST("api/kata")
    Call<Kosakata> createKosakata(@Body Kosakata kosakata);

    @PUT("api/kata/{id}")
    Call<Kosakata> updateKosakata(@Path("id") int id, @Body Kosakata kosakata);

    @DELETE("api/kata/{id}")
    Call<Void> deleteKosakata(@Path("id") int id);

    @GET("api/kata/search")
    Call<List<Kosakata>> searchKosakata(@Query("kata") String kata);

}
