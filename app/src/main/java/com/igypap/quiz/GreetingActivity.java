package com.igypap.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {
    private TextView mGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        mGreeting = (TextView) findViewById(R.id.greeting);

        //1. odczytanie parametru name
        String name = getIntent().getStringExtra("name");
        //2. wyswietlenie go na kontrolce text view
        mGreeting.setText(name);


    }
}
