package com.example.pathfit3.quizQnA;

import android.content.Context;
import com.example.pathfit3.R;

public class dancePeriodsHardQnA {
    private Context context;

    public dancePeriodsHardQnA(Context context) {
        this.context = context;
    }

    public String[] getQuestions() {
        return new String[] {
                context.getString(R.string.dancePeriodHard_question_1),
                context.getString(R.string.dancePeriodHard_question_2),
                context.getString(R.string.dancePeriodHard_question_3),
                context.getString(R.string.dancePeriodHard_question_4),
                context.getString(R.string.dancePeriodHard_question_5),
                context.getString(R.string.dancePeriodHard_question_6),
                context.getString(R.string.dancePeriodHard_question_7),
                context.getString(R.string.dancePeriodHard_question_8),
                context.getString(R.string.dancePeriodHard_question_9),
                context.getString(R.string.dancePeriodHard_question_10),
                context.getString(R.string.dancePeriodHard_question_11),
                context.getString(R.string.dancePeriodHard_question_12),
                context.getString(R.string.dancePeriodHard_question_13),
                context.getString(R.string.dancePeriodHard_question_14),
                context.getString(R.string.dancePeriodHard_question_15),
                context.getString(R.string.dancePeriodHard_question_16),
                context.getString(R.string.dancePeriodHard_question_17),
                context.getString(R.string.dancePeriodHard_question_18),
                context.getString(R.string.dancePeriodHard_question_19),
                context.getString(R.string.dancePeriodHard_question_20)
        };
    }

    public String[][] getChoices() {
        return new String[][] {
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_1),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_2),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_3),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_4),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_5),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_6),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_7),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_8),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_9),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_10),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_11),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_12),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_13),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_14),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_15),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_16),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_17),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_18),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_19),
                context.getResources().getStringArray(R.array.dancePeriodHard_choices_20)
        };
    }

    public String[] getCorrectAnswers() {
        return new String[] {
                context.getString(R.string.dancePeriodHard_correct_answer_1),
                context.getString(R.string.dancePeriodHard_correct_answer_2),
                context.getString(R.string.dancePeriodHard_correct_answer_3),
                context.getString(R.string.dancePeriodHard_correct_answer_4),
                context.getString(R.string.dancePeriodHard_correct_answer_5),
                context.getString(R.string.dancePeriodHard_correct_answer_6),
                context.getString(R.string.dancePeriodHard_correct_answer_7),
                context.getString(R.string.dancePeriodHard_correct_answer_8),
                context.getString(R.string.dancePeriodHard_correct_answer_9),
                context.getString(R.string.dancePeriodHard_correct_answer_10),
                context.getString(R.string.dancePeriodHard_correct_answer_11),
                context.getString(R.string.dancePeriodHard_correct_answer_12),
                context.getString(R.string.dancePeriodHard_correct_answer_13),
                context.getString(R.string.dancePeriodHard_correct_answer_14),
                context.getString(R.string.dancePeriodHard_correct_answer_15),
                context.getString(R.string.dancePeriodHard_correct_answer_16),
                context.getString(R.string.dancePeriodHard_correct_answer_17),
                context.getString(R.string.dancePeriodHard_correct_answer_18),
                context.getString(R.string.dancePeriodHard_correct_answer_19),
                context.getString(R.string.dancePeriodHard_correct_answer_20)
        };
    }
}
