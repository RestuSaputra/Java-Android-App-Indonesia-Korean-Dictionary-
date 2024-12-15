package com.example.myapplication.service;

import com.example.myapplication.model.Kosakata;
import com.example.myapplication.model.LawanKata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LawanKataService {

    @GET("/api/antonim")
    Call<List<LawanKata>> getAllLawanKata();

    @GET("api/antonim/{id}")
    Call<LawanKata> getLawanKataId(@Path("id") int id);

    @POST("api/antonim")
    Call<LawanKata> createLawanKata(@Body LawanKata lawanKata);

    @PUT("api/antonim/{id}")
    Call<LawanKata> updateLawanKata(@Path("id") int id, @Body LawanKata lawanKata);

    @DELETE("api/antonim/{id}")
    Call<Void> deleteLawanKata(@Path("id") int id);

}
