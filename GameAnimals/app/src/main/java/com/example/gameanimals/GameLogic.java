package com.example.gameanimals;

import java.util.Random;

public class GameLogic {
    boolean gameState;//true for started, false for ended
    int id1; //id for answer 1
    int id2;
    int id3;
    int rightAnswer; //which is the right answer
    int score;
    Random ra;
    public GameLogic() {
        gameState = false;
        id1 = 0;
        id2 = 0;
        id3 = 0;
        ra = new Random();
        score = 0;
    }
    public void resetAnswer(){
        id1 = ra.nextInt(10);
        do{
            id2 = ra.nextInt(10);
        }while(id2==id1);
        do{
            id3 = ra.nextInt(10);
        }while((id3==id2)||(id3==id1));
        rightAnswer = ra.nextInt(3)+1;
    }
    public boolean checkAnswer(int ans){
        if(ans == rightAnswer){
            score++;
            return true;
        }else{
            gameState = false;
            return false;
        }
    }
    public void restart(){
        gameState = true;
        score = 0;
        resetAnswer();
    }
    public void endGame(){
        gameState = false;
    }
    public int getAnsId(){
        int ans;
        switch(rightAnswer){
            case 1:{
                ans = id1;
                break;
            }
            case 2:{
                ans = id2;
                break;
            }
            case 3:{
                ans = id3;
                break;
            }
            default:{
                ans = -1;
            }
        }
        return ans;
    }
}
