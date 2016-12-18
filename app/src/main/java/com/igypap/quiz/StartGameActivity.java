package com.igypap.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StartGameActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mNextButton;
    private IQuestionDatabase mQuestionDatabase = new RandomQuestionsDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        mEditText = (EditText) findViewById(R.id.name_field);
        mNextButton = (Button) findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextScreen();
            }
        });
    }

    private void openNextScreen() {
        //1. odczytac wpisany tekst
        String name = mEditText.getText().toString();
        //2. otworzyc nowe okno przekazujac wpisany teskt
        Intent nameIntent = new Intent(this, GreetingActivity.class);
        //startActivity(nameIntent); //uruchomimy nowe okno ale nie przekazemy parametru name!!!
        nameIntent.putExtra(GreetingActivity.EXTRA_NAME, name);


        //losowanie pytan
        List<Question> questions = mQuestionDatabase.getQuestions();
        Random random = new Random();
        while (questions.size() > 5) {
            //usuwa losowy element z listy

            questions.remove(random.nextInt(questions.size()));
        }
        Collections.shuffle(questions);
        nameIntent.putExtra(GreetingActivity.EXTRA_QUESTIONS, new ArrayList<>(questions));
        startActivity(nameIntent);


    }
}
