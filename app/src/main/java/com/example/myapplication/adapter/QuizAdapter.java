package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Kosakata;
import com.example.myapplication.model.Quiz;
import com.example.myapplication.view.QuestionActivity;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private List<Quiz> quizList;
    private Context context;

    public QuizAdapter(List<Quiz> quizList, Context context) {
        this.quizList = quizList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        String title = quiz.getTitle();
        if (title != null && !title.isEmpty()) {
            holder.quizTitle.setText(title);
        } else {
            holder.quizTitle.setText("Untitled Quiz");
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuestionActivity.class);
            intent.putExtra("quizId", quiz.getId());
            context.startActivity(intent);
        });
    }

    public void updateQuizList(List<Quiz> quizzes) {
        this.quizList = quizzes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView quizTitle;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            quizTitle = itemView.findViewById(R.id.quiz_title);
        }
    }
}
