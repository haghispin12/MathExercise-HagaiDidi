package com.example.mathexercise_hagaididi;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

public class customDialog extends Dialog implements  android.view.View.OnClickListener {
    MainViewModelTeacher mainViewModelTeacher;
    private Button activate,recline;
    public customDialog(@NonNull Context context) {
        super(context);
        mainViewModelTeacher = new ViewModelProvider(context).get(MainViewModelTeacher.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        activate = (Button) findViewById(R.id.dai_Activate);
        recline = (Button) findViewById(R.id.dai_recline);
        activate.setOnClickListener(this);
        recline.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dai_Activate:

                break;
            case R.id.dai_recline:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
    }
}
