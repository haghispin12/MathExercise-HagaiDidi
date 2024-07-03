package com.example.mathexercise_hagaididi;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainViewModelStudent extends ViewModel {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<ArrayList<teacher>> LiveTeachers = new MutableLiveData<ArrayList<teacher>>();
    MutableLiveData<ArrayList<lesson>> LiveLessons = new MutableLiveData<>();
    MutableLiveData<ArrayList<Boolean>> LiveIsHasTeacherBool = new MutableLiveData<>();
    private String EmailTeacher;
    private int isHasTeacherBool = -1;
    MutableLiveData< ArrayList<String>> LiveDates = new MutableLiveData<>();
    MutableLiveData<ArrayList<lesson>> LiveLessonsPerDate = new MutableLiveData<>();


    public String GetCurrentEmail() {
        return auth.getCurrentUser().getEmail();
    }
    public void GetLessonsListenerPerDate(String Date) {
        ArrayList<lesson> temp = new ArrayList<>();
        db.collection("lessons").whereEqualTo("teacherEmail", EmailTeacher).whereEqualTo("date", Date).whereEqualTo("studentEmail","").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (documentSnapshot.exists()) {
                        String Date =documentSnapshot.getString("date");
                        String hour = documentSnapshot.getString("hour");
                        String teacherEmail = documentSnapshot.getString("teacherEmail");
                        String studentEmail = documentSnapshot.getString("studentEmail");
                        lesson temL = new lesson(Date, hour, teacherEmail, studentEmail);
                        temp.add(temL);
                    }
                }
                LiveLessonsPerDate.setValue(temp);
            }
        });
    }
    public void lessonsListener(){
        ArrayList<lesson> temp = new ArrayList<>();
        db.collection("lessons").whereEqualTo("studentEmail",GetCurrentEmail()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null) {
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        if (checkDate(dc.getDocument().getString("date"))) {
                            switch (dc.getType()) {
                                case ADDED:
                                    String Date = dc.getDocument().getString("date");
                                    String hour = dc.getDocument().getString("hour");
                                    String teacherEmail = dc.getDocument().getString("teacherEmail");
                                    String studentEmail = dc.getDocument().getString("studentEmail");
                                    lesson temL = new lesson(Date, hour, teacherEmail, studentEmail);
                                    temp.add(temL);
                                    break;
                                case MODIFIED:
                                    String Date1 = dc.getDocument().getString("date");
                                    String hour1 = dc.getDocument().getString("hour");
                                    String teacherEmail1 = dc.getDocument().getString("teacherEmail");
                                    String studentEmail1 = dc.getDocument().getString("studentEmail");
                                    lesson temL1 = new lesson(Date1, hour1, teacherEmail1, studentEmail1);
                                    temp.add(temL1);
                                    break;
                                case REMOVED:
                                    String Date2 = dc.getDocument().getString("date");
                                    String hour2 = dc.getDocument().getString("hour");
                                    String teacherEmail2 = dc.getDocument().getString("teacherEmail");
                                    String studentEmail2 = dc.getDocument().getString("studentEmail");
                                    lesson temL2 = new lesson(Date2, hour2, teacherEmail2, studentEmail2);
                                    temp.add(temL2);
                                    break;
                            }
                        }
                    }
                }
                LiveLessons.setValue(temp);
            }
        });

    }

    public void getTeachers(int i) {
        if(i!=1) {
            db.collection("teachers profiles").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    ArrayList<teacher> tempTeachers = new ArrayList<teacher>();
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.exists()) {
                            String location = documentSnapshot.getString("location");
                            String phoneNumber = documentSnapshot.getString("phoneNumber");
                            String name = documentSnapshot.getString("name");
                            int price = Integer.valueOf(Math.toIntExact(documentSnapshot.getLong("price")));
                            String Email = documentSnapshot.getString("email");
                            teacher teacher = new teacher(phoneNumber, price, location, name, Email);
                            tempTeachers.add(teacher);
                        }
                    }
                    LiveTeachers.setValue(tempTeachers);
                }
            });
        }
    }

    public void IsHasATeacher() {
        db.collection("connections").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if(isHasTeacherBool!=1) {
                        if (documentSnapshot.exists()) {
                            if (documentSnapshot.getString("emailStudent").equals(GetCurrentEmail()) && documentSnapshot.getString("status").equals("Active")) {
                                isHasTeacherBool = 1;
                                EmailTeacher = documentSnapshot.getString("emailTeacher");
                                ArrayList<Boolean> temp = new ArrayList<Boolean>();
                                LiveIsHasTeacherBool.setValue(temp);
                            }
                        }
                    }
                }
                getTeachers(isHasTeacherBool);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getTeachers(isHasTeacherBool);
            }
        });
    }
    public void MakeAConnection(String EmailTeacher,String EmailStudent){
        connection connection = new connection(EmailTeacher,EmailStudent,"inActivate");
        db.collection("connections").document().set(connection).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });

    }
    public int getIsHasATeacher(){
        return isHasTeacherBool;
    }
    public void getDaysWithLessons(){
        ArrayList<String> temp= new ArrayList<String>();
        db.collection("lessons").whereEqualTo("teacherEmail",EmailTeacher).whereEqualTo("studentEmail","").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if (documentSnapshot.exists()){
                        temp.add(documentSnapshot.getString("date"));
                    }
                }
                LiveDates.setValue(temp);
            }
        });
    }
    public void changeStatus(lesson lesson) {
        CollectionReference update = db.collection("lessons");
        update.whereEqualTo("teacherEmail",EmailTeacher).whereEqualTo("date",lesson.getDate()).whereEqualTo("hour",lesson.getHour()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot dc: queryDocumentSnapshots) {
                    String dcId = dc.getId();
                    update.document(dcId).update("studentEmail",GetCurrentEmail() );
                }
            }
        });
    }
    public boolean checkDate(String Date1)  {
        Calendar currentCalendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String[] dateParts = Date1.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1;
        int year = Integer.parseInt(dateParts[2]);
        Calendar dateToCheck = Calendar.getInstance();
        dateToCheck.set(year, month, day);
        long timeToCheck = dateToCheck.getTimeInMillis();
        long currentTime = currentCalendar.getTimeInMillis();
        String sss = dateFormat.format(currentCalendar.getTime());
        boolean result = timeToCheck > currentTime;
        return result;
    }
}
