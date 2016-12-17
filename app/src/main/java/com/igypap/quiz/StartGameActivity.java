package com.igypap.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartGameActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mNextButton;

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
        nameIntent.putExtra("name", name);
        startActivity(nameIntent);


    }
}
