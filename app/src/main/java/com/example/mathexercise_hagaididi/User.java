package com.example.mathexercise_hagaididi;

public class User {
    private String UserName;
    private int score;

    public User (String UserName){
        this.UserName = UserName;
        score =0;
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

    public int getScore() {
        return score;
    }
}
