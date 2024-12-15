package com.example.myapplication.service;

import com.example.myapplication.model.Hangeul;
import com.example.myapplication.model.Kosakata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HangeulService {

    @GET("/api/hangeul")
    Call<List<Hangeul>> getAllHangeul();

    @GET("api/hangeul/{id}")
    Call<Hangeul> getHangeulById(@Path("id") int id);

    @GET("/api/hangeul/konsonantunggal")
    Call<List<Hangeul>> getAllKonsonanTunggal();

    @GET("/api/hangeul/vokaltunggal")
    Call<List<Hangeul>> getAllVokalTunggal();

    @GET("/api/hangeul/konsonanganda")
    Call<List<Hangeul>> getAllKonsonanGanda();

    @GET("/api/hangeul/vokalganda")
    Call<List<Hangeul>> getAllVokalGanda();
}
