package com.example.mathexercise_hagaididi;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.google.firebase.auth.FirebaseAuth;

public class MainViewModelTeacher extends ViewModel {
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void editProfile(String name,String phonenumber,String location,int price){

    }
}
