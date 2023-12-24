package com.example.mathexercise_hagaididi;

import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private exercise exs = new exercise();
    private User user;
    private int scoreType;
    MutableLiveData<Integer> Vnum1 =new MutableLiveData<Integer>();
    MutableLiveData<Integer> Vnum2 =new MutableLiveData<Integer>();
    MutableLiveData<Integer> Score =new MutableLiveData<Integer>();
    public void mulltable(int Type){
        scoreType=Type;
        Vnum1.setValue(exs.mulb());
        Vnum2.setValue(exs.mulb());
    }
    public void x20 (int Type){
        scoreType=Type;
        Vnum1.setValue(exs.X20());
        Vnum2.setValue(exs.X20());
    }
    public  void chalenge (int Type){
        scoreType=Type;
        Vnum1.setValue(exs.chalnge());
        Vnum2.setValue(exs.chalnge());
    }
    public String answer (EditText ans){
        String answer = exs.ans(ans);
        if(answer.equals("well done!!!"))
        {
            user.editScore(scoreType);
            Score.setValue(user.getScore());
        }
        return answer;
    }
    public void SetUserDetails(String userName){
        user=new User(userName);
    }
    public void setuserRate(int rate){
        user.setRate(rate);
    }
    public int getScore(){return user.getScore();}
    public String getUsername(){return user.getUserName();}
}
