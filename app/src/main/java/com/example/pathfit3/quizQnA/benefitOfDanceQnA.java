package com.example.pathfit3.quizQnA;

import android.content.Context;
import com.example.pathfit3.R;

public class benefitOfDanceQnA {
    private Context context;

    public benefitOfDanceQnA(Context context) {
        this.context = context;
    }

    public String[] getQuestions() {
        return new String[] {
                context.getString(R.string.benefitDanceEz_question_1),
                context.getString(R.string.benefitDanceEz_question_2),
                context.getString(R.string.benefitDanceEz_question_3),
                context.getString(R.string.benefitDanceEz_question_4),
                context.getString(R.string.benefitDanceEz_question_5),
                context.getString(R.string.benefitDanceEz_question_6),
                context.getString(R.string.benefitDanceEz_question_7),
                context.getString(R.string.benefitDanceEz_question_8),
                context.getString(R.string.benefitDanceEz_question_9),
                context.getString(R.string.benefitDanceEz_question_10)
        };
    }

    public String[][] getChoices() {
        return new String[][] {
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_1),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_2),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_3),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_4),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_5),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_6),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_7),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_8),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_9),
                context.getResources().getStringArray(R.array.benefitDanceEz_choices_10)
        };
    }

    public String[] getCorrectAnswers() {
        return new String[] {
                context.getString(R.string.benefitDanceEz_correct_answer_1),
                context.getString(R.string.benefitDanceEz_correct_answer_2),
                context.getString(R.string.benefitDanceEz_correct_answer_3),
                context.getString(R.string.benefitDanceEz_correct_answer_4),
                context.getString(R.string.benefitDanceEz_correct_answer_5),
                context.getString(R.string.benefitDanceEz_correct_answer_6),
                context.getString(R.string.benefitDanceEz_correct_answer_7),
                context.getString(R.string.benefitDanceEz_correct_answer_8),
                context.getString(R.string.benefitDanceEz_correct_answer_9),
                context.getString(R.string.benefitDanceEz_correct_answer_10)
        };
    }
}
