package com.example.releaseyourbrain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView Gcard, Scard, Ccard, GEcard;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //defining Cards
        Gcard = (CardView) findViewById(R.id.general);
        Scard = (CardView) findViewById(R.id.sports);
        Ccard = (CardView) findViewById(R.id.computer);
        GEcard = (CardView) findViewById(R.id.geography);
        //Add ClickListener to the cards
        Gcard.setOnClickListener(this);
        Scard.setOnClickListener(this);
        Ccard.setOnClickListener(this);
        GEcard.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.general:
                intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra("card","general");
                startActivity(intent);
                break;
            case R.id.computer:
                intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra("card","computer");
                startActivity(intent);
                break;
            case R.id.sports:
                intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra("card","sports");
                startActivity(intent);
                break;
            case R.id.geography:
                intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra("card","geography");
                startActivity(intent);
                break;
            default:
                break;

        }
    }



    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {

            new AlertDialog.Builder(this)
                    .setTitle("Do you want to exit?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setResult(RESULT_OK, new Intent().putExtra("Exit", true));
                            finish();
                        }
                    }).create().show();

        }else  {

            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in HomeActivity");
        finish();

    }

}