package com.igypap.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartGameActivity extends AppCompatActivity {
    @BindView(R.id.name_field)
    EditText mEditText;


    private IQuestionDatabase mQuestionDatabase = new RandomQuestionsDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next_button)
    void openNextScreen() {
        //1. read user input text
        String name = mEditText.getText().toString();
        //2. otworzyc nowe okno przekazujac wpisany teskt
        Intent nameIntent = new Intent(this, GreetingActivity.class);
        //startActivity(nameIntent); //uruchomimy nowe okno ale nie przekazemy parametru name!!!
        nameIntent.putExtra(GreetingActivity.EXTRA_NAME, name);


        //generate the random questions
        List<Question> questions = mQuestionDatabase.getQuestions();
        Random random = new Random();
        while (questions.size() > 5) {
            //delete random element from the list

            questions.remove(random.nextInt(questions.size()));
        }
        Collections.shuffle(questions);
        nameIntent.putExtra(GreetingActivity.EXTRA_QUESTIONS, new ArrayList<>(questions));
        startActivity(nameIntent);


    }
}
