package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.QuestionAdapter;
import com.example.myapplication.model.Question;
import com.example.myapplication.model.Result;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.QuizService;
import com.example.myapplication.ui.latihan.LatihanFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {

    private RecyclerView recyclerViewQuestion;
    private QuestionAdapter questionAdapter;
    private List<Question> questionList = new ArrayList<>();
    private Long quizId;
    private int score;
    private Button submitButtonQuiz;
    private QuizService questionService;
    private ViewGroup rootView;
    private Button finishButton, retryButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        quizId = getIntent().getLongExtra("quizId", -1);
        recyclerViewQuestion = findViewById(R.id.recyclerViewQuiz);
        recyclerViewQuestion.setLayoutManager(new LinearLayoutManager(this));
        questionAdapter = new QuestionAdapter(questionList);
        recyclerViewQuestion.setAdapter(questionAdapter);
        submitButtonQuiz = findViewById(R.id.submitQuiz_button);
        submitButtonQuiz.setOnClickListener(v -> calculateScore());
        questionService = RetrofitClient.getRetrofitInstance().create(QuizService.class);

        if (quizId != -1) {
            fetchQuestions(quizId);
        } else {
            Log.e("QuizActivity", "Ini bukan ID Quiz");
        }

    }

    private void fetchQuestions(Long quizId) {
        questionService.getQuestion(quizId).enqueue(new Callback<List<Question>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                Log.d("QuestionActivity", "Raw Response: " + response.raw().toString());
                if (response.isSuccessful() && response.body() != null) {
                    questionList.addAll(response.body());
                    questionAdapter.notifyDataSetChanged();
                    Log.d("QuizListActivity", "jumlah pertanyaan yang diterima : " + questionList.size());
                } else {
                    Log.d("QuizListActivity", "Tidak Berhasil mendapatkan pertanyaan");
                    Log.e("QuestionActivity", "Response Code: " + response.code());
                    Log.e("QuestionActivity", "Response Message: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(QuestionActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void calculateScore() {
        score = 0;
        Map<Long, String> userAnswers = questionAdapter.getUserAnswer();
        Log.d("QuestionActivity", "Jawaban yang dipilih : " + questionAdapter.getUserAnswer().toString());

        for (Question question : questionList) {
            String correctOption = question.getCorrectOption();
            String userAnswer = userAnswers.get(question.getId());
            Log.d("QuestionActivity", "Question ID : " + userAnswer + " Selected Option : " + correctOption);
            if (correctOption != null && correctOption.equals(userAnswer)) {
                score++;
            } else {
                score+=0;
                Log.d("QuestionActivity", "Gak nyambung Bro");
            }
        }
        submitResult();
    }

    private void submitResult() {
        Result result = new Result();
        result.setUsername("user123");
        result.setQuizId(quizId);
        result.setScore(score);
        result.setUserId(1L);


        questionService.submitResult(result).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Result result1 = response.body();
                    finishQuiz(result1.getScore());
                    Toast.makeText(QuestionActivity.this, "Berhasil mendapatkan skor", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("QuestionActivity", "Gagal mendapatkan Skor : " + response.message());
                    Log.d("QuestionActivity", "Request URL: " + call.request().url());
                    Log.d("QuestionActivity", "Response Code: " + response.code());
                    Log.d("QuestionActivity", "Response Message: " + response.message());
                    Log.d("QuestionActivity", "Response Body: " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(QuestionActivity.this, "Tidak berhasil mendapatkan skor" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayScore(int score) {
        Dialog dialog = new Dialog(QuestionActivity.this);
        dialog.setContentView(R.layout.skoring_activity);
        dialog.setCancelable(false);

        TextView scoreTextView = dialog.findViewById(R.id.score_tv);
        finishButton = dialog.findViewById(R.id.finish_button);
        retryButton = dialog.findViewById(R.id.retry_button);

        scoreTextView.setText("" +score);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(QuestionActivity.this, LatihanFragment.class);
                startActivity(intent);
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        dialog.show();
    }
    private void finishQuiz(int score){
        displayScore(score);
    }
}



















