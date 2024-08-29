package com.example.pathfit3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class quizActivity extends AppCompatActivity {
    CardView crdQuiz_dancePeriods, crdQuiz_benefitsOfDance, crdQuiz_natureOfDance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        CardId();
        cardClick();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    void CardId() {
        crdQuiz_dancePeriods = findViewById(R.id.crdQuiz_dancePeriods);
        crdQuiz_benefitsOfDance = findViewById(R.id.crdQuiz_benefitsOfDance);
        crdQuiz_natureOfDance = findViewById(R.id.crdQuiz_natureOfDance);
    }

    void cardClick() {
        crdQuiz_dancePeriods.setOnClickListener(v -> showDifficultyDialog("Dance Periods"));
        crdQuiz_benefitsOfDance.setOnClickListener(v -> showDifficultyDialog("Benefits of Dance"));
        crdQuiz_natureOfDance.setOnClickListener(v -> showDifficultyDialog("Nature of Dance"));
    }

    private void showDifficultyDialog(String quizType) {
        final String[] difficulties = {"Easy", "Medium", "Hard"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Difficulty")
                .setItems(difficulties, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichDifficulty) {
                        String selectedDifficulty = difficulties[whichDifficulty];
                        startQuizActivity(quizType, selectedDifficulty);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private void startQuizActivity(String quizType, String difficulty) {
        Intent intent = new Intent(quizActivity.this, quizActivity.class); // Default activity

        // Determine the correct activity based on quizType
        if (quizType.equals("Dance Periods")) {
            if (difficulty.equals("Easy")) {
                intent.setClass(quizActivity.this, dancePeriodsQuizEasyActivity.class);
//            } else if (difficulty.equals("Medium")) {
//                intent.setClass(quizActivity.this, QuizQuestionsDancePeriodsMediumActivity.class);
//            } else if (difficulty.equals("Hard")) {
//                intent.setClass(quizActivity.this, QuizQuestionsDancePeriodsHardActivity.class);
//            }
//        } else if (quizType.equals("Benefits of Dance")) {
//            if (difficulty.equals("Easy")) {
//                intent.setClass(quizActivity.this, QuizQuestionsBenefitsOfDanceEasyActivity.class);
//            } else if (difficulty.equals("Medium")) {
//                intent.setClass(quizActivity.this, QuizQuestionsBenefitsOfDanceMediumActivity.class);
//            } else if (difficulty.equals("Hard")) {
//                intent.setClass(quizActivity.this, QuizQuestionsBenefitsOfDanceHardActivity.class);
//            }
//        } else if (quizType.equals("Nature of Dance")) {
//            if (difficulty.equals("Easy")) {
//                intent.setClass(quizActivity.this, QuizQuestionsNatureOfDanceEasyActivity.class);
//            } else if (difficulty.equals("Medium")) {
//                intent.setClass(quizActivity.this, QuizQuestionsNatureOfDanceMediumActivity.class);
//            } else if (difficulty.equals("Hard")) {
//                intent.setClass(quizActivity.this, QuizQuestionsNatureOfDanceHardActivity.class);
//            }
//        } else {
//            throw new IllegalArgumentException("Unexpected quiz type: " + quizType);
//        }

                intent.putExtra("QUIZ_TYPE", quizType);
                intent.putExtra("DIFFICULTY", difficulty);
                startActivity(intent);
            }
        }
    }
}