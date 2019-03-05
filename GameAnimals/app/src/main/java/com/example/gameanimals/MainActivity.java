package com.example.gameanimals;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GameLogic gameLogic;
    int time;
    Button btnStart;
    Button btn1;
    Button btn2;
    Button btn3;
    ImageView img;
    TextView text;
    Handler TimerHandler;
    Runnable myTimerRun = new Runnable() {
        @Override
        public void run() {
            time--;
            if (time >= 0) {
                btnStart.setText("" + time);
                TimerHandler.postDelayed(this, 1000);
            } else {
                gameLogic.endGame();
                img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.skull));
                btnStart.setText("restart");
                time = 3;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameLogic = new GameLogic();
        time = 3;
        btnStart = (Button) findViewById(R.id.button_Start);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        img = (ImageView) findViewById(R.id.imageView_Main);
        text = (TextView)findViewById(R.id.textView_Score);
        TimerHandler = new Handler();
    }

    public void btnStartClick(View v) {
        if (!gameLogic.gameState) {
            gameLogic.restart();
            btnStart.setText("" + time);
            TimerHandler.postDelayed(myTimerRun, 1000);
            img.setImageResource(getResource(PicList.getName(gameLogic.getAnsId())));
            setAns();
            String scores = "Score: "+gameLogic.score;
            text.setText(scores);
        }
    }
    public void btn1Click(View v){
        if(gameLogic.gameState) {
            TimerHandler.removeCallbacks(myTimerRun);
            if(gameLogic.checkAnswer(1)){
                gameLogic.resetAnswer();
                img.setImageResource(getResource(PicList.getName(gameLogic.getAnsId())));
                time = 3;
                btnStart.setText("" + time);
                setAns();
                String scores = "Score: "+gameLogic.score;
                text.setText(scores);
                TimerHandler.postDelayed(myTimerRun, 1000);
            }else{
                img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.skull));
                btnStart.setText("restart");
                time = 3;
            }
        }
    }
    public void btn2Click(View v){
        if(gameLogic.gameState) {
            TimerHandler.removeCallbacks(myTimerRun);
            if(gameLogic.checkAnswer(2)){
                gameLogic.resetAnswer();
                img.setImageResource(getResource(PicList.getName(gameLogic.getAnsId())));
                time = 3;
                btnStart.setText("" + time);
                setAns();
                String scores = "Score: "+gameLogic.score;
                text.setText(scores);
                TimerHandler.postDelayed(myTimerRun, 1000);
            }else{
                img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.skull));
                btnStart.setText("restart");
                time = 3;
            }
        }
    }
    public void btn3Click(View v){
        if(gameLogic.gameState) {
            TimerHandler.removeCallbacks(myTimerRun);
            if(gameLogic.checkAnswer(3)){
                gameLogic.resetAnswer();
                img.setImageResource(getResource(PicList.getName(gameLogic.getAnsId())));
                time = 3;
                btnStart.setText("" + time);
                setAns();
                String scores = "Score: "+gameLogic.score;
                text.setText(scores);
                TimerHandler.postDelayed(myTimerRun, 1000);
            }else{
                img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.skull));
                btnStart.setText("restart");
                time = 3;
            }
        }
    }
    public int getResource(String imageName) {
        Context ctx = getBaseContext();
        int resId = getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
        return resId;
    }
    public void setAns(){
        btn1.setText(PicList.getName(gameLogic.id1));
        btn2.setText(PicList.getName(gameLogic.id2));
        btn3.setText(PicList.getName(gameLogic.id3));
    }
}