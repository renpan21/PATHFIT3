    package com.example.pathfit3.quiz;

    import android.app.AlertDialog;
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
    //    CardView crdQuizDancePeriods, crdQuizBenefitsOfDance, crdQuizNatureOfDance;
        private CardView[] cardViews;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            ((AppCompatActivity) this).setContentView(R.layout.activity_quiz);
            crdClickListen();

            applyWindowInsets();
        }

        void crdClickListen() {
            cardViews = new CardView[]{
                    findViewById(R.id.crdQuiz_dancePeriods),
                    findViewById(R.id.crdQuiz_benefitsOfDance),
                    findViewById(R.id.crdQuiz_natureOfDance)
            };
            String[] quizTypes = {"Dance Periods", "Benefits of Dance", "Nature of Dance"};
            for (int i = 0; i < cardViews.length; i++) {
                final String quizType = quizTypes[i];
                cardViews[i].setOnClickListener(v -> showDifficultyDialog(quizType));
            }
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
            builder.setTitle("Please Select Difficulty")
                    .setItems(difficulties, (dialog, whichDifficulty) -> {
                        String selectedDifficulty = difficulties[whichDifficulty];
                        startQuizActivity(quizType, selectedDifficulty);
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .create()
                    .show();
        }

        private void startQuizActivity(String quizType, String difficulty) {
            Intent intent = new Intent(quizActivity.this, dancePeriodsQuizEasyActivity.class);


            if (quizType.equals("Dance Periods")) {
                if (difficulty.equals("Easy")) {
                    intent.setClass(quizActivity.this, dancePeriodsQuizEasyActivity.class);
                } else if (difficulty.equals("Hard")) {
                    intent.setClass(quizActivity.this, quiz_dancePeriod_hard.class);
                }
            }else if (quizType.equals("Benefits of Dance")) {
                if (difficulty.equals("Easy")) {
                    intent.setClass(quizActivity.this, benefitOfDanceQuizEz.class);
                }else if (difficulty.equals("Hard")) {
                    intent.setClass(quizActivity.this, benefitOfDanceHardQuiz.class);
                }
            }else if (quizType.equals("Nature of Dance")) {
                if (difficulty.equals("Easy")) {
                    intent.setClass(quizActivity.this, natureOfDanceQuizEz.class);
                } else if (difficulty.equals("Hard")) {
                    intent.setClass(quizActivity.this, natureOfDanceHardQuiz.class);
                }
            }else{
                new AlertDialog.Builder(this)
                        .setTitle("Invalid Selection")
                        .setMessage("Please select a valid quiz type and difficulty.")
                        .setPositiveButton("OK", null)
                        .setCancelable(false)
                        .show();
            }

            intent.putExtra("QUIZ_TYPE", quizType);
            intent.putExtra("DIFFICULTY", difficulty);
            startActivity(intent);
        }
    }
