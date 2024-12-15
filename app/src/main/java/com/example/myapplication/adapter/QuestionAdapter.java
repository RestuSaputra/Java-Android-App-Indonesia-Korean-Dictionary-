package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Question> questionList;
    private Map<Long, String> userAnswer = new HashMap<>();

    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question questions = questionList.get(position);
        String question = questions.getQuestion();
        if (question != null && !question.isEmpty()) {
            holder.bind(questions);
        } else {
            holder.questionText.setText("Apa Mau lo?");
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public Map<Long, String> getUserAnswer() {
        return userAnswer;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private TextView questionText, nomorText;
        private RadioButton optionA, optionB, optionC, optionD;
        private RadioGroup optionsGroup;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            nomorText = itemView.findViewById(R.id.nomor_text);
            questionText = itemView.findViewById(R.id.question_text);
            optionA = itemView.findViewById(R.id.option_a);
            optionB = itemView.findViewById(R.id.option_b);
            optionC = itemView.findViewById(R.id.option_c);
            optionD = itemView.findViewById(R.id.option_d);
            optionsGroup = itemView.findViewById(R.id.options_group);
        }

        public void bind(Question questions) {
            nomorText.setText(questions.getId().toString()+". ");
            questionText.setText(questions.getQuestion());
            optionA.setText(questions.getOptionA());
            optionB.setText(questions.getOptionB());
            optionC.setText(questions.getOptionC());
            optionD.setText(questions.getOptionD());

            optionsGroup.setOnCheckedChangeListener(((group, checkedId) -> {
                String selectedOption = null;
                if (checkedId == R.id.option_a) {
                    selectedOption = questions.getOptionA().toString();
                } else if (checkedId == R.id.option_b) {
                    selectedOption = questions.getOptionB().toString();
                } else if (checkedId == R.id.option_c) {
                    selectedOption = questions.getOptionC().toString();
                } else if (checkedId == R.id.option_d) {
                    selectedOption = questions.getOptionD().toString();
                }
                userAnswer.put(questions.getId(), selectedOption);

            }));
        }
    }
}
