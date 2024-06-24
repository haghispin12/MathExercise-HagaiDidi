package com.example.mathexercise_hagaididi;

import android.graphics.Color;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarDay;

import com.applandeo.materialcalendarview.EventDay;

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
    }
    public void activitis(){
        mainViewModelStudent.LiveDates.observe(requireActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> dates) {
                Calendar c = Calendar.getInstance();
                List<CalendarDay> calendarDays = new ArrayList<>();
                for (int i =0 ; i < dates.size() ; i++){
                    c = convertStringToCalendar(dates.get(i));
                    CalendarDay calendarDay = new CalendarDay(c);
                    calendarDay.setLabelColor(R.color.red);
                    //calendarDay.setImageResource(R.drawable.sample_icon);
                    calendarDays.add(calendarDay);
                }
                cal.setCalendarDays(calendarDays);

//
//                for (int i =0 ; i < dates.size() ; i++){
//                    events.add(new EventDay(convertStringToCalendar(dates.get(i)),R.drawable.sample_icon));
//                }
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

}