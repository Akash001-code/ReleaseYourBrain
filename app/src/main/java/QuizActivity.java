package com.example.releaseyourbrain;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button next;

    private TextView txques;
    private TextView txscore;
    private TextView txtimer;
    private TextView txquescount;

    private ArrayList<Questions> questionlist;
    private int questionCounter;
    private int questionTotalCount;
    private Questions currentQuestions;
    private boolean answerd;

    private Handler handler = new Handler();
    private int score = 0;
    public  int correctans = 0,wrongans = 0;
    public String a;

    private long backPressedTime;



    


    private MediaPlayer mediaPlayer;

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeleftinMillis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        a = getIntent().getExtras().getString("card");
        setupUI();
        fetchDB();
        Log.i("BUGBUG","onCreate() in QuizActivity");

    }

    private void setupUI(){
        txques = findViewById(R.id.questions_name);
        txquescount = findViewById(R.id.questioncount);
        txscore = findViewById(R.id.scoreid);
        txtimer = findViewById(R.id.timerid);

        next = findViewById(R.id.button);
        rbGroup = findViewById(R.id.rbgroup);
        rb1= findViewById(R.id.radioButton1);
        rb2= findViewById(R.id.radioButton2);
        rb3= findViewById(R.id.radioButton3);
        rb4= findViewById(R.id.radioButton4);

    }

    private void fetchDB()
    {
        Quizdbhelper dbhelper = new Quizdbhelper(this);
        questionlist = dbhelper.getAllQuestionswithcategory(a);
        startQuiz();
    }

    private void startQuiz() {

        questionTotalCount = questionlist.size();
        Collections.shuffle(questionlist);
        showQuestions();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {

                switch (checkedID){

                    case R.id.radioButton1:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                    break;

                    case R.id.radioButton2:
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.answer_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                    break;

                    case R.id.radioButton3:
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                    break;

                    case R.id.radioButton4:
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
                    break;


                }
            }
        });





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answerd){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()|| rb4.isChecked()){
                        quizOperations();
                    }
                    else
                    {
                        Toast.makeText(QuizActivity.this,"Please select any option",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    private void quizOperations(){
         answerd = true;
         countDownTimer.cancel();
         RadioButton rbselected = findViewById(rbGroup.getCheckedRadioButtonId());
         int answer_no = rbGroup.indexOfChild(rbselected)+1;
         checkSolutions(answer_no,rbselected);
    }




    private void checkSolutions(int answer_no, RadioButton rbselected) {

        switch (currentQuestions.getAnswer()){

            case 1:
                if (currentQuestions.getAnswer() == answer_no){
                    rb1.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));

                    score = score + 10;
                    txscore.setText("Score: " + String.valueOf(score));
                    correctans++;

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                else
                {
                    rb1.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));
                    wrongans++;
                    changetoIncorrectColor(rbselected);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                break;

            case 2:
                if (currentQuestions.getAnswer() == answer_no){
                    rb2.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));
                    score = score + 10;
                    txscore.setText("Score: " + String.valueOf(score));
                    correctans++;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                else
                {
                    rb2.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));
                    wrongans++;
                    changetoIncorrectColor(rbselected);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                break;

            case 3:
                if (currentQuestions.getAnswer() == answer_no){
                    rb3.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));
                    score = score + 10;
                    txscore.setText("Score: " + String.valueOf(score));
                    correctans++;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                else
                {
                    rb3.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));
                    wrongans++;
                    changetoIncorrectColor(rbselected);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                break;

            case 4:
                if (currentQuestions.getAnswer() == answer_no){
                    rb4.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));
                    correctans++;
                    score = score + 10;
                    txscore.setText("Score: " + String.valueOf(score));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                else
                {
                    rb4.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_correct));
                    wrongans++;
                    changetoIncorrectColor(rbselected);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestions();
                        }
                    },1000);

                }
                break;

        }//end of switch

        if(questionCounter == questionTotalCount)
        {
            next.setText("FINISH");
        }
    }



    private void changetoIncorrectColor(RadioButton rbselected)
    {
        rbselected.setBackground(ContextCompat.getDrawable(this,R.drawable.answer_wrong));
    }







    private void showQuestions()
    {
        rbGroup.clearCheck();

        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.radio_button_bg));




        if(questionCounter < questionTotalCount){

            currentQuestions = questionlist.get(questionCounter);
            txques.setText(currentQuestions.getQname());
            rb1.setText(currentQuestions.getOption1());
            rb2.setText(currentQuestions.getOption2());
            rb3.setText(currentQuestions.getOption3());
            rb4.setText(currentQuestions.getOption4());

            questionCounter++;
            answerd = false;

            next.setText("Confirm");
            txquescount.setText("Questions: " + questionCounter + "/" + questionTotalCount);

            timeleftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();


        }
        else
        {
            Toast.makeText(this, "Quiz Finshed", Toast.LENGTH_SHORT).show();

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            next.setClickable(false);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finalResult(a);

                }
            }, 2000);


        }
    }

    private void startCountDown(){

        countDownTimer = new CountDownTimer(timeleftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeleftinMillis = 0;
                updateCountDownText();

            }
        }.start();

    }



    private void updateCountDownText(){

        int minutes = (int) (timeleftinMillis/1000) /60;
        int seconds = (int) (timeleftinMillis/1000) % 60;

        //  String timeFormatted = String.format(Locale.getDefault(),"02d:%02d",minutes,seconds);

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);
        txtimer.setText(timeFormatted);


        if (timeleftinMillis < 10000){

            txtimer.setTextColor(Color.RED);
            int tick = R.raw.tick;
            playmusic(tick);


        }else {
            txtimer.setTextColor(Color.BLACK);
        }

        if (timeleftinMillis == 0 ){

            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    showQuestions();

                }
            },1000);



        }
    }

    private void playmusic(int audiofile)
    {

        mediaPlayer = MediaPlayer.create(this,audiofile);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("BUGBUG","onRestart() in QuizActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in QuizActivity");
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("BUGBUG","onPause() in QuizActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BUGBUG","onResume() in QuizActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("BUGBUG","onStart() in QuizActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
        Log.i("BUGBUG","onDestroy() in QuizActivity");


    }



    private void finalResult(String categoryname){

        Intent resultData = new Intent(QuizActivity.this,FinalActivity.class);
        resultData.putExtra("category",categoryname);
        resultData.putExtra("a",correctans);
        resultData.putExtra("b",score);
        resultData.putExtra("c",wrongans);
        startActivity(resultData);

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){

            Intent intent = new Intent(QuizActivity.this,HomeActivity.class);
            startActivity(intent);

        }else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }



}







