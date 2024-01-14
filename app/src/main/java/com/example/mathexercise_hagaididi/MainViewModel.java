package com.example.mathexercise_hagaididi;

import android.content.Context;
import android.net.Uri;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private exercise exs = new exercise();
    private User user;
    private int scoreType;
    private DBHelper database;
    MutableLiveData<Integer> Vnum1 =new MutableLiveData<Integer>();
    MutableLiveData<Integer> Vnum2 =new MutableLiveData<Integer>();
    MutableLiveData<Integer> Score =new MutableLiveData<Integer>();
    MutableLiveData<ArrayList<User>> users =new MutableLiveData<ArrayList<User>>();
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
    public  int getRate(){
        return user.getRate();
    }
    public String getUsername(){return user.getUserName();}
    public long getId(){
        return user.getId();
    }
    public void vinsert(Context context){
        database = new DBHelper(context);
        user.setId(database.insert(user,context));
        selectAll(context);
    }
    public void seturi(Uri uri){
        user.setUri(uri);
    }
    public void selectAll(Context context){
        database = new DBHelper(context);
       ArrayList<User> users = database.selectAll();
         this.users.setValue(users);
    }
    public boolean checkIfNotExists(String Username){
        for(int i = 0 ;i<users.getValue().size();i++){
            if(users.getValue().get(i).getUserName().equals(user.getUserName())){
                user.setId(users.getValue().get(i).getId());
                return false;
            }
        }
        return true;

    }

}
