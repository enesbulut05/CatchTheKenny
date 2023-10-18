package com.enesbulut.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView bestText;
    int storedScore=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bestText = findViewById(R.id.textView2);

        BestScore.loadBestScore(getApplicationContext());
        storedScore = BestScore.getBestScore();
        bestText.setText("Best Score: " + storedScore);

    }


    public void playButton(View view){

        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

}