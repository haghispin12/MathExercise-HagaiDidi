package com.example.mathexercise_hagaididi;

import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private exercise exs = new exercise();
    MutableLiveData<Integer> Vnum1 =new MutableLiveData<Integer>();
    MutableLiveData<Integer> Vnum2 =new MutableLiveData<Integer>();
    public void mulltable(){
        Vnum1.setValue(exs.mulb());
        Vnum2.setValue(exs.mulb());
    }
    public void x20 (){
        Vnum1.setValue(exs.X20());
        Vnum2.setValue(exs.X20());
    }
    public  void chalenge (){
        Vnum1.setValue(exs.chalnge());
        Vnum2.setValue(exs.chalnge());
    }
    public String answer (EditText ans){
        return exs.ans(ans);
    }
}
