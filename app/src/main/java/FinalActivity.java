package com.example.releaseyourbrain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class FinalActivity extends AppCompatActivity {

    private TextView txcorrect;
    private TextView txscore;
    private TextView txwrong;
    private TextView txcongrats;
    private TextView txwp;

    private ProgressBar c;
    private ProgressBar s;
    private ProgressBar w;

    private Button play;
    private Button quit;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);


        txcorrect = findViewById(R.id.correctvalue);
        txwrong = findViewById(R.id.wrongvalue);
        txscore = findViewById(R.id.scorevalue);
        txcongrats = findViewById(R.id.congrats);
        txwp = findViewById(R.id.wp);
        c = findViewById(R.id.pbcorrect);
        s = findViewById(R.id.pbscore);
        w = findViewById(R.id.pbwrong);
        play = findViewById(R.id.playagain);
        quit = findViewById(R.id.exit);

        int correctanswer = getIntent().getExtras().getInt("a");
        int score = getIntent().getExtras().getInt("b");
        int wronganswer = getIntent().getExtras().getInt("c");
        final String category = getIntent().getExtras().getString("category");

        if (correctanswer <= 4) {
            txcongrats.setText("Play better next time");
            txcongrats.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
            txwp.setText("Try Again");
        } else if (correctanswer <= 7) {
            txcongrats.setText("Good!!");
            txcongrats.setPadding(550, 20, 0, 0);
            txwp.setText("Score More..");
        }


        txcorrect.setText(String.valueOf(correctanswer));
        txwrong.setText(String.valueOf(wronganswer));
        txscore.setText(String.valueOf(score));


        int i = correctanswer * 10;
        int j = wronganswer * 10;


        c.setProgress(i);
        s.setProgress(score);
        w.setProgress(j);
        c.setMax(100);
        s.setMax(100);
        w.setMax(100);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FinalActivity.this, QuizActivity.class);
                intent.putExtra("card", category);
                startActivity(intent);

            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FinalActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            Intent intent = new Intent(FinalActivity.this, HomeActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in FinishActivity");
        finish();

    }
}







