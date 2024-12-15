package com.example.myapplication.service;

import com.example.myapplication.model.Question;
import com.example.myapplication.model.Quiz;
import com.example.myapplication.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuizService {

    @GET("/api/quizzes")
    Call<List<Quiz>> getAllQuizzes();

    @GET("/api/quizzes/{quizId}/question")
    Call<List<Question>> getQuestion(@Path("quizId") Long quizId);

    @POST("/api/results")
    Call<Result> submitResult(@Body Result result);

    @GET("results/user/{userId}")
    Call<List<Result>> getResultsByUserId(@Path("userId") Long userId);

    @GET("results/quiz/{quizId}")
    Call<List<Result>> getResultsByQuizId(@Path("quizId") Long quizId);

}
