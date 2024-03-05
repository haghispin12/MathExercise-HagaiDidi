/*
package com.example.mathexercise_hagaididi.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.mathexercise_hagaididi.R;

public class RateActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private Button subRateBtn;
    private int rateProgres;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        seekBar = findViewById(R.id.seekbr);
        subRateBtn = findViewById(R.id.subratebtn);
        seekBar.setOnSeekBarChangeListener(this);
        subRateBtn.findViewById(R.id.subratebtn);
        activaete();
    }
        public void activaete(){
        subRateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("progress", rateProgres);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        rateProgres = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this,"seek bar new value is:"+rateProgres,Toast.LENGTH_SHORT).show();
    }
}
*/
