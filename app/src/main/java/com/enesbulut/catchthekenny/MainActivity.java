package com.enesbulut.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    ImageView kenny;
    Random random;
    int score;
    int newBestScore;
    SharedPreferences sharedPreferences;
    String oldBestScore;

    BestScore bestScoreClass = new BestScore();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            timeText = findViewById(R.id.time);
            scoreText = findViewById(R.id.score);
            kenny = findViewById(R.id.imageView);

            score = 0;
            random = new Random();

            Intent intent = getIntent();
            oldBestScore = intent.getStringExtra("bestScore");


            new CountDownTimer(10000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                    //kenynin yerini degistir.

                    int layoutWidth = getWindow().getDecorView().getWidth();
                    int layoutHeight = getWindow().getDecorView().getHeight();
                    int kennyWidth = kenny.getWidth();
                    int kennyHeight = kenny.getHeight();
                    int maxX = Math.max(layoutWidth - kennyWidth, 1); // minX değeri en az 1 olmalı
                    int maxY = Math.max(layoutHeight - kennyHeight, 1); // minY değeri en az 1 olmalı
                    int newX = random.nextInt(maxX);
                    int newY = random.nextInt(maxY);

                    kenny.setX(newX);
                    kenny.setY(newY);


                    int sayac = 0;

                    if (sayac == 0) {
                        timeText.setText("Time: " + millisUntilFinished / 1000);
                        sayac++;
                    } else {
                        sayac = 0;
                    }

                }

                @Override
                public void onFinish() {
                    Boolean record = false;
                    timeText.setText("Time's End");
                    if (score > BestScore.getBestScore()) {
                        BestScore.setBestScore(score);
                        BestScore.saveBestScore(getApplicationContext());

                        Toast.makeText(getApplicationContext(),"Best Score: "+BestScore.getBestScore(),Toast.LENGTH_LONG).show();
                        record = true;

                    }

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Game Over");
                    alert.setMessage("");
                    if (record) {
                        alert.setMessage("Congratulations. New record: " + score + "\nDo you want to start a new game?");
                    } else {
                        alert.setMessage("Score: " + score + "\n Do you want to start a new game?");
                    }
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(intent);
                        }
                    });
                    alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alertDialog = alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();


                }
            }.start();




    }



    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }

    public void click(View view) {
        score++;
        scoreText.setText("Score: " + score);
    }

}