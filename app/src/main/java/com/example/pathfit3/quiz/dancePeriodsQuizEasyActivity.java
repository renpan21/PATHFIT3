package com.example.pathfit3.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pathfit3.R;
import com.example.pathfit3.quizQnA.dancePeriodsQnAns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class dancePeriodsQuizEasyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView;
    private TextView timerTextView;
    private Button ansA, ansB, ansC, ansD;
    private Button submitBtn;

    private int score = 0;
    private int totalQuestion;
    private int currentQuestionIndex = 0;
    private String selectedAnswer = "";
    private Button selectedButton = null;

    private List<dancePeriodsQuizEasyActivity.Question> questions;
    private CountDownTimer countDownTimer;
    private final long TIMER_DURATION = 10000;
    private final long TIMER_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_periods_quiz_easy);

        questionTextView = findViewById(R.id.question);
        timerTextView = findViewById(R.id.timer);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        initializeQuestions();
        Collections.shuffle(questions);
        totalQuestion = questions.size();

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(questions.get(currentQuestionIndex).correctAnswer)) {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        } else {

            selectedAnswer = clickedButton.getText().toString();
            selectedButton = clickedButton;
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        dancePeriodsQuizEasyActivity.Question currentQuestion = questions.get(currentQuestionIndex);
        if (questionTextView != null) {
            questionTextView.setText(currentQuestion.questionText);
            ansA.setText(currentQuestion.choices[0]);
            ansB.setText(currentQuestion.choices[1]);
            ansC.setText(currentQuestion.choices[2]);
            ansD.setText(currentQuestion.choices[3]);
        }


        startTimer();
    }

    void finishQuiz() {
        // Stop the timer when the quiz ends
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        String passStatus = score > totalQuestion * 0.60 ? "Passed" : "Failed";

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        Collections.shuffle(questions);  // Shuffle questions again for the restart
        loadNewQuestion();
    }

    private static class Question {
        String questionText;
        String[] choices;
        String correctAnswer;

        public Question(String questionText, String[] choices, String correctAnswer) {
            this.questionText = questionText;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
        }
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();

        for (int i = 0; i < dancePeriodsQnAns.question.length; i++) {
            questions.add(new Question(
                    dancePeriodsQnAns.question[i],
                    dancePeriodsQnAns.choices[i],
                    dancePeriodsQnAns.correctAnswers[i]
            ));
        }
    }

    private void startTimer() {
        // Cancel timer
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(TIMER_DURATION, TIMER_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                timerTextView.setText(String.format("Time Left: %02d:%02d", seconds / 60, seconds % 60));
            }

            @Override
            public void onFinish() {

                if (selectedButton != null) {
                    selectedButton.setBackgroundColor(Color.WHITE);
                    selectedButton = null; // Clear the reference
                }
                currentQuestionIndex++;
                loadNewQuestion();  // Load the next question
            }
        }.start();
    }
}
