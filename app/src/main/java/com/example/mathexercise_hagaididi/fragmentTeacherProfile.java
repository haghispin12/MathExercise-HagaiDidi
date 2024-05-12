package com.example.mathexercise_hagaididi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class fragmentTeacherProfile extends Fragment {
    private Button confirm;
    private EditText phoneNumber;
    private EditText price;
    private EditText location;
    private EditText name;
    private View v;
    private MainViewModelTeacher mainViewModelTeacher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_teacher_profile, container, false);
        mainViewModelTeacher = new ViewModelProvider(requireActivity()).get(MainViewModelTeacher.class);
        initviews();
        return  v;
    }
    public void initviews(){
        confirm = v.findViewById(R.id.button_confirm);
        phoneNumber = v.findViewById(R.id.editText_phonenumber);
        price = v.findViewById(R.id.editText_price);
        location = v.findViewById(R.id.edittext_location);
        name = v.findViewById(R.id.edittext_teachersname);
    }
    public  void activity(){

    }

}