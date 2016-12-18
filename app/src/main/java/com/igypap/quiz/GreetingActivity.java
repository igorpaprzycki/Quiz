package com.igypap.quiz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class GreetingActivity extends AppCompatActivity {
    private TextView mQuestion;
    private RadioGroup mAnswers;
    private RadioButton mAnswer1;
    private RadioButton mAnswer2;
    private RadioButton mAnswer3;
    private Button mBackButton;
    private Button mNextButton;
    private RadioButton[] mRadioButtons;


    private List<Question> mQuestions;

    private int[] mChoices;
    private int mCurrentQuestion = 0;
    private String mName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        mQuestion = (TextView) findViewById(R.id.question);

        //1. odczytanie parametru name i listy pytan
        mName = getIntent().getStringExtra("name");
        mQuestions = (List<Question>) getIntent().getSerializableExtra("questions");
        mChoices = new int[mQuestions.size()];
        //2. wyswietlenie go na kontrolce text view
        mQuestion = (TextView) findViewById(R.id.question);
        mAnswers = (RadioGroup) findViewById(R.id.answer_choice);
        mAnswer1 = (RadioButton) findViewById(R.id.answer_1);
        mAnswer2 = (RadioButton) findViewById(R.id.answer_2);
        mAnswer3 = (RadioButton) findViewById(R.id.answer_3);
        mBackButton = (Button) findViewById(R.id.back);
        mNextButton = (Button) findViewById(R.id.next);
        mRadioButtons = new RadioButton[]{mAnswer1, mAnswer2, mAnswer3};

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
            }
        });
        refreshView();

    }

    //save actual state of the Activity before ex. rotating screen
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mChoices[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        outState.putInt("currentQuestion", mCurrentQuestion);
        outState.putIntArray("choices", mChoices);
    }

    //restore saved Activity state after ex. rotating screen
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentQuestion = savedInstanceState.getInt("currentQuestion", 0);
        mChoices = savedInstanceState.getIntArray("choices");
        refreshView();
    }

    private void onNextClick() {
        mChoices[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        if (mCurrentQuestion + 1 == mQuestions.size()) {
            countResult();
            return;
        }
        //save the current answer

        mCurrentQuestion++;
        refreshView();
    }

    private void onBackClick() {
        if (mCurrentQuestion - 1 < 0) {
            // TODO: 17.12.16
            return;
        }
        //save current answer
        mChoices[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        mCurrentQuestion--;
        refreshView();
    }

    private void refreshView() {
        Question question = mQuestions.get(mCurrentQuestion);
        mQuestion.setText(question.getQuestion());  //set the question text
        //set the answers in the radio buttons
        int index = 0;

        for (RadioButton rb : mRadioButtons) {
            //zamiast :
            //mAnswer1.setText(question.getAnswers().get(0));
            // mAnswer2.setText(question.getAnswers().get(1));
            // mAnswer3.setText(question.getAnswers().get(2));
            rb.setText(question.getAnswers().get(index++));
        }
        //set the buttons visibility
        mBackButton.setVisibility(mCurrentQuestion == 0 ? View.GONE : View.VISIBLE);
        mNextButton.setText(mCurrentQuestion == mQuestions.size() - 1 ? "Zakończ" : "Dalej");
        //clear the radio buttons when switching questions
        mAnswers.clearCheck();
        if (mChoices[mCurrentQuestion] > 0) {
            mAnswers.check(mChoices[mCurrentQuestion]);

        }


    }

    private void countResult() {
        int correctAnswers = 0;
        int questionsCount = mQuestions.size();

        for (int i = 0; i < questionsCount; i++) {
            int correctAnswerIndex = mQuestions.get(i).getCorrectAnswer();
            int choiceRadioButtonId = mChoices[i];
            int choiceIndex = -1;
            for (int j = 0; j < mRadioButtons.length; j++) {
                if (mRadioButtons[j].getId() == choiceRadioButtonId) {
                    choiceIndex = j;
                    break;
                }
            }
            if (correctAnswerIndex == choiceIndex) {
                correctAnswers++;
            }
        }
        //show the result to the user
//        Toast.makeText(this,
//                String.format("Wynik: %d/%d", correctAnswers, questionsCount),
//                Toast.LENGTH_SHORT).show();

//wyswietlenie okna dialogowego z podsumowaniem. Takie okno niestety przy obrocie znika:
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Wynik quizu")
                .setMessage(String.format("Witaj %s ! Twój wynik to %d/%d !",
                        mName, correctAnswers,questionsCount))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create();
        dialog.show();



    }
}
