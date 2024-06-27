package com.example.mathexercise_hagaididi;

import android.app.Dialog;
import android.graphics.Color;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarDay;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;


public class fragment_student extends Fragment {
    private View v;
    private com.applandeo.materialcalendarview.CalendarView cal;
    private RecyclerView rcLesson;
    private MainViewModelStudent mainViewModelStudent;
    private lessonsAdapter lessonsAdapter;
    private Button daiYes;
    private Button daiNo;
    private TextView daiText;
    private Button exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_student, container, false);
        initviews();
       mainViewModelStudent = new ViewModelProvider(requireActivity()).get(MainViewModelStudent.class);
       mainViewModelStudent.getDaysWithLessons();
        activitis();

        return v;
    }
    public void initviews(){
        cal = v.findViewById(R.id.student_calendar);
        rcLesson = v.findViewById(R.id.lessons_students_recycle);
        exit = v.findViewById(R.id.frag_student_exit);
    }
    public void activitis(){
        mainViewModelStudent.LiveLessonsPerDate.observe(requireActivity(), new Observer<ArrayList<lesson>>() {
            @Override
            public void onChanged(ArrayList<lesson> lessons) {
                initLessonsAdapterPerDate(lessons);
            }
        });
        mainViewModelStudent.LiveDates.observe(requireActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> dates) {
                Calendar c = Calendar.getInstance();
                List<CalendarDay> calendarDays = new ArrayList<>();
                for (int i =0 ; i < dates.size() ; i++){
                    c = convertStringToCalendar(dates.get(i));
                    CalendarDay calendarDay = new CalendarDay(c);
                    calendarDay.setLabelColor(R.color.red);
                    calendarDays.add(calendarDay);
                }
                cal.setCalendarDays(calendarDays);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragment_student.this).commit();
            }
        });
        cal.setOnCalendarDayClickListener(new OnCalendarDayClickListener() {
            @Override
            public void onClick(@NonNull CalendarDay calendarDay) {
                Calendar calendar = calendarDay.getCalendar();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(calendar.getTime());
                mainViewModelStudent.GetLessonsListenerPerDate(formattedDate);
            }
        });
    }
    private Calendar convertStringToCalendar(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(dateString);
            if (date != null) {
                calendar.setTime(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
    public void initLessonsAdapterPerDate(ArrayList<lesson> lessons){
        lessonsAdapter = new lessonsAdapter(lessons, new lessonsAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(lesson item) {
                Dialog dialog = new Dialog(requireActivity());
                dialog.setContentView(R.layout.dialog_student);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                daiYes = dialog.findViewById(R.id.dai_yes);
                daiNo = dialog.findViewById(R.id.dai_no);
                daiText = dialog.findViewById(R.id.diaTextStudent);
                dialog.show();
                daiYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainViewModelStudent.changeStatus(item);
                        dialog.cancel();
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment_student.this).commit();
                    }
                });
                daiNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });

        rcLesson.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rcLesson.setAdapter(lessonsAdapter);
        rcLesson.setHasFixedSize(true);

    }

}