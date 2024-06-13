package com.example.mathexercise_hagaididi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class fragmentTeacherLessons extends Fragment {
    private Button exit;
    private Button addLesson;
    private EditText selectHour;
    private TextView selectedDate;
    private com.applandeo.materialcalendarview.CalendarView cal;
    private View v;
    private MainViewModelTeacher mainViewModelTeacher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    v=inflater.inflate(R.layout.fragment_teacher_lessons, container, false);
        mainViewModelTeacher = new ViewModelProvider(requireActivity()).get(MainViewModelTeacher.class);
        initviews();
        activitis();
    return v;
    }
    public void initviews(){
        exit = v.findViewById(R.id.exit);
        addLesson = v.findViewById(R.id.Fra_add_lesson);
        selectedDate=v.findViewById(R.id.selectedDate);
        selectHour = v.findViewById(R.id.selectHour);
        cal = v.findViewById(R.id.calendarView);
    }
    public void activitis(){
        cal.setOnCalendarDayClickListener(new OnCalendarDayClickListener() {
            @Override
            public void onClick(@NonNull CalendarDay calendarDay) {
                Calendar calendar = calendarDay.getCalendar();

                // Format the date to display in TextView
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(calendar.getTime());
                selectedDate.setText(formattedDate);
            }
        });
        addLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lesson lesson = new lesson(selectedDate.getText().toString(),selectHour.getText().toString(),mainViewModelTeacher.GetCurrentEmail(),null);
                mainViewModelTeacher.addLesson(lesson);
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragmentTeacherLessons.this).commit();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragmentTeacherLessons.this).commit();
            }
        });
    }
    }
