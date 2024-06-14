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
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainViewModelStudent extends ViewModel {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<ArrayList<teacher>> LiveTeachers = new MutableLiveData<ArrayList<teacher>>();
    MutableLiveData<ArrayList<lesson>> LiveLessons = new MutableLiveData<>();
    private String EmailTeacher;


    public String GetCurrentEmail() {
        return auth.getCurrentUser().getEmail();
    }
    public void lessonsListener(){
        ArrayList<lesson> temp = new ArrayList<>();
        db.collection("lessons").whereEqualTo("studentEmail",GetCurrentEmail()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null) {
                    for (DocumentChange dc : value.getDocumentChanges()) {
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
                LiveLessons.setValue(temp);
            }
        });

    }

    public void getTeachers() {
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

    public void IsHasATeacher() {
        db.collection("connections").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    if(documentSnapshot.exists()){
                        if(documentSnapshot.getString("emailStudent").equals(GetCurrentEmail())){
                            EmailTeacher = documentSnapshot.getString("emailTeacher");
                        }
                    }
                }
                    getTeachers();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                getTeachers();
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
}
