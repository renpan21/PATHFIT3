package com.example.pathfit3.quiz;

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
import com.example.pathfit3.R;

public class quizActivity extends AppCompatActivity {
    CardView crdQuizDancePeriods, crdQuizBenefitsOfDance, crdQuizNatureOfDance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        initializeCardViews();
        setupCardClickListeners();
        applyWindowInsets();
    }

    void initializeCardViews() {
        crdQuizDancePeriods = findViewById(R.id.crdQuiz_dancePeriods);
        crdQuizBenefitsOfDance = findViewById(R.id.crdQuiz_benefitsOfDance);
        crdQuizNatureOfDance = findViewById(R.id.crdQuiz_natureOfDance);
    }

    void setupCardClickListeners() {
        crdQuizDancePeriods.setOnClickListener(v -> showDifficultyDialog("Dance Periods"));
        crdQuizBenefitsOfDance.setOnClickListener(v -> showDifficultyDialog("Benefits of Dance"));
        crdQuizNatureOfDance.setOnClickListener(v -> showDifficultyDialog("Nature of Dance"));
    }

    private void applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDifficultyDialog(String quizType) {
        final String[] difficulties = {"Easy", "Hard"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Difficulty")
                .setItems(difficulties, (dialog, whichDifficulty) -> {
                    String selectedDifficulty = difficulties[whichDifficulty];
                    startQuizActivity(quizType, selectedDifficulty);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void startQuizActivity(String quizType, String difficulty) {
        Intent intent = new Intent(quizActivity.this, dancePeriodsQuizEasyActivity.class); // Default activity

        // Set appropriate activity based on quizType and difficulty
        if (quizType.equals("Dance Periods")) {
            if (difficulty.equals("Easy")) {
                intent.setClass(quizActivity.this, dancePeriodsQuizEasyActivity.class);
            } else if (difficulty.equals("Hard")) {
                intent.setClass(quizActivity.this, quiz_dancePeriod_hard.class);
            }
        }

        intent.putExtra("QUIZ_TYPE", quizType);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }
}
