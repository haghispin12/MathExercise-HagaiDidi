package com.example.mathexercise_hagaididi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
        activity();
        mainViewModelTeacher.isProfileExist();
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
        mainViewModelTeacher.isExist.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==1){
                    setValues(mainViewModelTeacher.getCurrentTeacher());
                    mainViewModelTeacher.isExist.setValue(0);
                }
                else if(integer==0);
                else {
                    mainViewModelTeacher.isExist.setValue(0);
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacher teacher = new teacher(phoneNumber.getText().toString(),Integer.valueOf(price.getText().toString()),location.getText().toString(),name.getText().toString(), mainViewModelTeacher.GetCurrentEmail());
                mainViewModelTeacher.editProfile(teacher);
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragmentTeacherProfile.this).commit();
            }
        });
    }
    public void setValues(teacher teacher){
        phoneNumber.setText(teacher.getPhoneNumber());
        price.setText(teacher.getPrice()+"");
        location.setText(teacher.getLocation());
        name.setText(teacher.getName());
    }

}