package com.example.mathexercise_hagaididi;

public class User {
    private String UserName;
    private int score;
    private int rate ;

    public User (String UserName){
        this.UserName = UserName;
        score =0;
        rate =0;
    }
    public void editScore(int Type){
        switch (Type){
            case 1:
                score+=5;
                break;
            case 2:
                score+=10;
                break;
            case 3:
                score+=20;
                break;

        }
    }
    public void setRate(int Rate){
        rate= Rate;
    }

    public int getScore() {
        return score;
    }
    public String getUserName(){return UserName;}
}
