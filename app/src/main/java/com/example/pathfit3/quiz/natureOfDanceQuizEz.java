package com.example.pathfit3.quiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pathfit3.R;
import com.example.pathfit3.quizQnA.natureOfDanceQuizQnA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class natureOfDanceQuizEz extends AppCompatActivity implements View.OnClickListener {
    private TextView questionTxt;
    private TextView timerTxt;
    private TextView totalQuestionsTxt;
    private Button ansA, ansB, ansC, ansD;
    private Button submitBtn;

    private int score = 0;
    private int totalQuestions;
    private int currQuestionIndex = 0;
    private String selectedAnswer = "";
    private Button selectedButton = null;

    private List<Question> questions;
    private CountDownTimer countDownTimer;
    private final long TIMER_DURATION = 61000;
    private final long TIMER_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nature_of_dance_quiz_ez);

        questionTxt = findViewById(R.id.question);
        timerTxt = findViewById(R.id.timer);
        totalQuestionsTxt = findViewById(R.id.total_question);
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


        natureOfDanceQuizQnA quizData = new natureOfDanceQuizQnA(this);
        String[] questionsArray = quizData.getQuestions();
        String[][] choicesArray = quizData.getChoices();
        String[] correctAnswersArray = quizData.getCorrectAnswers();

        initializeQuestions(questionsArray, choicesArray, correctAnswersArray);
        Collections.shuffle(questions);
        totalQuestions = questions.size();

        loadNewQuestion();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedAnswer.equals(questions.get(currQuestionIndex).correctAnswer)) {
                score++;
            }
            selectedAnswer = "";
            currQuestionIndex++;
            loadNewQuestion();
        } else {
            selectedAnswer = clickedButton.getText().toString();
            selectedButton = clickedButton;
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion() {
        if (currQuestionIndex == totalQuestions) {
            finishQuiz();
            return;
        }

        Question currentQuestion = questions.get(currQuestionIndex);
        if (questionTxt != null) {
            questionTxt.setText(currentQuestion.questionText);
            ansA.setText(currentQuestion.choices[0]);
            ansB.setText(currentQuestion.choices[1]);
            ansC.setText(currentQuestion.choices[2]);
            ansD.setText(currentQuestion.choices[3]);
        }

        updateQuestionIndex();
        startTimer();
    }

    void updateQuestionIndex() {
        totalQuestionsTxt.setText("Question " + (currQuestionIndex + 1) + " of " + totalQuestions);
    }

    void finishQuiz() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        String passStatus = score > totalQuestions * 0.60 ? getString(R.string.dialog_title_passed) : getString(R.string.dialog_title_failed);

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage(getString(R.string.dialog_message, score, totalQuestions))
                .setPositiveButton(R.string.dialog_positive_button, (dialogInterface, i) -> {
                    Intent intent = new Intent(this, natureOfDanceQuizQnA.class);
                    startActivity(intent);
                    finish();
                })
                .setNeutralButton(R.string.dialog_neutral_button, (dialogInterface, i) -> restartQuiz())
                .setNegativeButton(R.string.dialog_negative_button, (dialogInterface, i) -> finish())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        currQuestionIndex = 0;
        Collections.shuffle(questions);
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

    private void initializeQuestions(String[] questionsArray, String[][] choicesArray, String[] correctAnswersArray) {
        questions = new ArrayList<>();

        for (int i = 0; i < questionsArray.length; i++) {
            questions.add(new Question(
                    questionsArray[i],
                    choicesArray[i],
                    correctAnswersArray[i]
            ));
        }
    }

    private void startTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(TIMER_DURATION, TIMER_INTERVAL) {
            @Override
            public void onTick(long secUntilFinished) {
                long seconds = secUntilFinished / 1000;
                timerTxt.setText(String.format("Time Left: %02d:%02d", seconds / 60, seconds % 60));
            }

            @Override
            public void onFinish() {
                if (selectedButton != null) {
                    selectedButton.setBackgroundColor(Color.WHITE);
                    selectedButton = null;
                }
                currQuestionIndex++;
                loadNewQuestion();
            }
        }.start();
    }
}
