package com.example.mathexercise_hagaididi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class fragment_student extends Fragment {
    private View v;
    private com.applandeo.materialcalendarview.CalendarView cal;
    private RecyclerView rcLesson;
    private MainViewModelStudent mainViewModelStudent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_student, container, false);
        initviews();
       mainViewModelStudent = new ViewModelProvider(requireActivity()).get(MainViewModelStudent.class);
        activitis();

        return v;
    }
    public void initviews(){
        cal = v.findViewById(R.id.student_calendar);
        rcLesson = v.findViewById(R.id.lessons_students_recycle);
    }
    public void activitis(){

    }
    public void DaysWithLessons(){
        
    }
}