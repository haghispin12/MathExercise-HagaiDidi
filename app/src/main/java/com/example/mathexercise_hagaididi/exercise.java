package com.example.mathexercise_hagaididi;

import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class exercise {
    private int num1;
    private int num2;
    private int count = 0;


    public int chalnge() {
        Random r = new Random();
        if (count == 0) {
            num1 = r.nextInt(9) + 1;
            count++;
            return num1;
        } else {
            num2 = r.nextInt(90) + 10;
            count--;
            return num2;
        }
    }

    public int mulb() {
        Random r = new Random();
        if (count == 0) {
            num1 = r.nextInt(10) + 1;
            count++;
            return num1;
        } else {
            num2 = r.nextInt(10) + 1;
            count--;
            return num2;
        }
    }
    public int X20(){
        Random r = new Random();
        if (count == 0) {
            num1 = r.nextInt(9) + 1;
            count++;
            return num1;
        } else {
            num2 = r.nextInt(10) + 10;
            count--;
            return num2;
        }
    }
    public String ans(EditText answer){
        String ans=answer.getText().toString();
        if(num1*num2==Integer.valueOf(ans)){
            return "well done!!!";
        }
        return "maybe next time...:(";
    }
}
